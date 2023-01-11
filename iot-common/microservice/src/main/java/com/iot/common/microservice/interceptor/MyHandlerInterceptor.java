package com.iot.common.microservice.interceptor;


import com.iot.common.data.constant.RequestHeaderConstant;
import com.iot.common.data.model.dto.security.UserInfoDto;
import com.iot.common.microservice.util.UserInfoContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyHandlerInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(MyHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var userId = request.getHeader(RequestHeaderConstant.HEADER_USERID);
        var username = request.getHeader(RequestHeaderConstant.HEADER_USERNAME);
        var companyCode = request.getHeader(RequestHeaderConstant.HEADER_COMPANY_CODE);
        var companyId = request.getHeader(RequestHeaderConstant.HEADER_COMPANY_ID);
        var admin = request.getHeader(RequestHeaderConstant.HEADER_ROLE_ADMIN);
        var roleLevel = request.getHeader(RequestHeaderConstant.HEADER_ROLE_LEVEL);
        log.debug("userId={},username={},companyCode={},companyId={},admin={},roleLevel={}", userId, username, companyCode, companyId,admin,roleLevel);
        var userInfoDto = new UserInfoDto();
        userInfoDto.setRequest(request);
        if (!StringUtils.hasText(userId)){
            UserInfoContext.initUserInfo(userInfoDto);
            log.trace("gateway add request header failed");
            return true;
        }
        userInfoDto.setUsername(username);
        userInfoDto.setUserId(Long.valueOf(userId));
        userInfoDto.setCompanyId(Long.valueOf(companyId));
        userInfoDto.setCompanyCode(companyCode);
        userInfoDto.setAdmin(Boolean.valueOf(admin));
        userInfoDto.setRoleLevel(Integer.valueOf(roleLevel));

        var header = request.getHeader("Authorization");
        if (StringUtils.hasText(header)) {
            userInfoDto.setToken(header.replace("Bearer", "").trim());
        }
        UserInfoContext.initUserInfo(userInfoDto);
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserInfoContext.remove();
    }
}
