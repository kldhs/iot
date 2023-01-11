package com.iot.common.feignapi.security.fallback;

import com.iot.common.data.constant.CommonResult;
import com.iot.common.data.enums.ResultEnum;
import com.iot.common.data.model.dto.security.SysPermissionDto;
import com.iot.common.data.model.entity.security.UserRoleInfo;
import com.iot.common.data.model.vo.security.DataPermissionInfoVo;
import com.iot.common.feignapi.security.SecurityApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wzh
 */
@Slf4j
@Component
public class SecurityApiServiceFallback implements FallbackFactory<SecurityApiService> {

    @Override
    public SecurityApiService create(Throwable cause) {
        return new SecurityApiService() {
            @Override
            public CommonResult<DataPermissionInfoVo> getDataPermissions(String userName) {
                log.error(">>feign: getDataPermissions userName={} failed", userName);
                log.error("", cause);
                return CommonResult.failed(ResultEnum.CLIENT_ERROR);
            }

            @Override
            public CommonResult<DataPermissionInfoVo> getDataPermissions(Long roleId) {
                log.error(">>feign: getDataPermissions roleId={} failed", roleId);
                log.error("", cause);
                return CommonResult.failed(ResultEnum.CLIENT_ERROR);
            }

            @Override
            public CommonResult<Object> addDataPermission(Long roleId, Long dataId,Integer dataType, String companyCode, String username) {
                log.error(">>feign: addProductPermission username={} dataId={},dataType={}, companyCode={}, username={} failed", roleId, dataId,dataType, companyCode, username);
                log.error("", cause);
                return CommonResult.failed(ResultEnum.CLIENT_ERROR);
            }

            @Override
            public CommonResult<Object> deleteDataPermission(Long roleId, Long dataId,Integer dataType,String userName,String companyCode) {
                log.error(">>feign: deleteProductPermission roleId={} dataType={} dataId={} failed", roleId,dataType,dataId);
                log.error("", cause);
                return CommonResult.failed(ResultEnum.CLIENT_ERROR);
            }

            @Override
            public CommonResult<UserRoleInfo> getUserRoleInfoByUserName(String userName) {
                log.error(">>feign: getUserRoleInfoByUserName username={} failed", userName);
                log.error("", cause);
                return CommonResult.failed(ResultEnum.CLIENT_ERROR);
            }

            @Override
            public CommonResult<Object> updateTaskAndStrategy(String companyCode,String operateUserName) {
                log.error(">>feign: updateTaskAndStrategy companyCode={} operateUserName={} failed", companyCode,operateUserName);
                log.error("", cause);
                return CommonResult.failed(ResultEnum.CLIENT_ERROR);
            }

            @Override
            public CommonResult<List<SysPermissionDto>> getPermissionsByRole(Long roleId) {
                log.error(">>feign: getPermissionsByRole roleId={} failed", roleId);
                log.error("", cause);
                return CommonResult.failed(ResultEnum.CLIENT_ERROR);
            }

            @Override
            public CommonResult<List<SysPermissionDto>> getAllPermissions() {
                log.error(">>feign: getAllPermissions failed");
                log.error("", cause);
                return CommonResult.failed(ResultEnum.CLIENT_ERROR);
            }
        };
    }
}
