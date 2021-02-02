package com.iot.common.data.model.dto.user;

import com.iot.common.data.model.bo.page.PageAndSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RoleSearchDto extends PageAndSearch {

    /**
     * 模糊查询条件
     */
    @Schema(title ="客户端：角色名称，管理端：企业和角色名称",nullable = true)
    private String searchText;

    /**
     * 角色等级
     */
    @Schema(title ="角色等级",nullable = true,description = "管理员端查询使用")
    private Integer roleLevel;


    /**
     * 企业code
     */
    @Schema(title ="企业编号",nullable = true,description = "用户端查询本企业下")
    private String companyCode;

}
