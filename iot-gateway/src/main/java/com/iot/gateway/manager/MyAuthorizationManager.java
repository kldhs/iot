package com.iot.gateway.manager;

import com.iot.common.data.constant.CommonResult;
import com.iot.common.data.model.bo.gateway.UserInfo;
import com.iot.common.data.model.dto.security.SysPermissionDto;
import com.iot.common.feignapi.security.SecurityApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * 自定义授权管理器，检查用户权限
 */
@Component
public class MyAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private static final Logger log = LoggerFactory.getLogger(MyAuthorizationManager.class);

    @Lazy
    @Resource
    private SecurityApiService securityApiService;

    private final PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext auth) {
        var req = auth.getExchange().getRequest();
        URI uri = req.getURI();
        String targetURI = uri.getPath();
        String method = req.getMethodValue().toUpperCase();
        log.info("request targetURI={}, method={}", targetURI, method);
        return authentication.filter(Authentication::isAuthenticated)
                .publishOn(Schedulers.boundedElastic())
                .flatMapIterable(it -> {
                    CommonResult<List<SysPermissionDto>> allPermissions = securityApiService.getAllPermissions();
                    if (allPermissions.isSuccess()){
                        List<SysPermissionDto> allPermission = allPermissions.getData();
                        boolean exist = allPermission.stream().filter(permissionDto -> StringUtils.hasText(permissionDto.getUri()))
                                .anyMatch(permission -> pathMatcher.match(permission.getUri(), targetURI));
                        if (!exist){
                            return List.of(new SysPermissionDto(targetURI,method));
                        }
                    }
                    var userInfo = (UserInfo)it.getPrincipal();
                    var permissionsByRole = securityApiService.getPermissionsByRole(userInfo.roleId());
                    if (!permissionsByRole.isSuccess()){
                        return List.of();
                    }
                    return permissionsByRole.getData();
                })
                .filter(it -> StringUtils.hasText(it.getUri()) && StringUtils.hasText(it.getMethod()))
                .any(permission -> pathMatcher.match(permission.getUri(), targetURI) && permission.getMethod().contains(method))
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }
}
