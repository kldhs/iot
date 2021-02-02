package com.iot.common.data.model.dto.user;

import com.iot.common.data.model.bo.page.PageAndSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "管理端-账户查询dto")
public class AdminUserSearchDto extends PageAndSearch {

    /**
     * 状态
     */
    @Schema(title ="状态",nullable = true,description = "1正常，2禁用")
    private Integer status;

    /**
     * 角色等级
     */
    @Schema(title ="角色等级",nullable = true,description = "1一级角色，2二级角色")
    private Integer roleLevel;

}
