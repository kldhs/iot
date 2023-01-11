package com.iot.common.feignapi.security.controller;

import com.iot.common.data.constant.CommonResult;
import com.iot.common.data.model.dto.security.PermissionDataBaseDto;
import com.iot.common.data.model.vo.security.DataPermissionInfoVo;
import com.iot.common.feignapi.security.SecurityApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * - 数据权限初始化相关信息
 */
@Slf4j
public class CommonSecurityController {

	@Resource
	private SecurityApiService securityApiService;

	/**
	 * 初始化 权限信息到 DTO
	 */
	protected void initDataPermissionInfo(PermissionDataBaseDto permissionDataBaseDto){
		if (StringUtils.hasText(permissionDataBaseDto.getUserName())){
			CommonResult<DataPermissionInfoVo> dataPermissions = securityApiService.getDataPermissions(permissionDataBaseDto.getUserName());
			if (dataPermissions.isSuccess()){
				permissionDataBaseDto.initPermission(dataPermissions.getData());
				return;
			}
		}
		permissionDataBaseDto.initFailed();
		log.warn("initDataPermissionInfo ->security rest api data-permissions failed, userName = {}",permissionDataBaseDto.getUserName());
	}
}
