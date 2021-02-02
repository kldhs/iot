package com.iot.common.data.model.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@Schema(name = "UserDetailVo", title = "用户详细信息")
@NoArgsConstructor
public class UserDetailVo {

    @Schema(title = "用户id")
    private Long id;

    @Schema(title = "用户名")
    private String username;

    @Schema(title = "手机号")
    private String phone;

    @Schema(title = "创建时间")
    private OffsetDateTime createTime;

    @Schema(title = "企业id")
    private Long companyId;

    @Schema(title = "企业编号")
    private String companyCode;


    @Schema(title = "企业name")
    private String companyName;

    @Schema(title = "部门名称")
    private String departmentName;

    @Schema(title = "职位名称")
    private String positionName;

    @Schema(title = "姓名")
    private String name;

    @Schema(title = "状态，1正常，2禁用")
    private Integer status;

    @Schema(title = "邮箱")
    private String email;

    @Schema(title = "角色id")
    private Long roleId;

    @Schema(title = "角色等级 1, 一级角色  2:二级角色 ")
    private Integer roleLevel;

    @Schema(title = "角色name")
    private String roleName;

    public UserDetailVo(Long id, String username, String phone, LocalDateTime createTime, Long companyId, String companyCode, String companyName, String departmentName, String positionName, String name, Integer status, String email, Long roleId, Integer roleLevel, String roleName) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.createTime = OffsetDateTime.of(createTime, OffsetDateTime.now().getOffset());
        this.companyId = companyId;
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.departmentName = departmentName;
        this.positionName = positionName;
        this.name = name;
        this.status = status;
        this.email = email;
        this.roleId = roleId;
        this.roleLevel = roleLevel;
        this.roleName = roleName;
    }
}
