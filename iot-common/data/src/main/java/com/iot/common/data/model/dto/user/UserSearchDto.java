package com.iot.common.data.model.dto.user;

import com.iot.common.data.model.bo.page.PageAndSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "账户查询dto")
public class UserSearchDto extends PageAndSearch {

    /**
     * 角色id
     */
    @Schema(title ="角色id",nullable = true)
    private Long roleId;

    /**
     * 状态
     */
    @Schema(title ="状态",nullable = true,description = "1正常，2禁用")
    private Integer status;

    /**
     * 角色等级
     */
    @Schema(title ="角色等级",nullable = true,description = "管理端可用条件：1一级角色，2二级角色")
    private Integer roleLevel;

}
