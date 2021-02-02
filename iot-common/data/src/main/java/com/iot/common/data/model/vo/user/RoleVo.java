package com.iot.common.data.model.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Schema(name = "RoleVo", description = "角色")
@NoArgsConstructor
public class RoleVo {

    @Schema(hidden = true)
    private Long id;

    @Schema(title = "角色")
    private String roleName;

    @Schema(title = "角色等级")
    private Integer roleLevel;

    @Schema(title = "企业code")
    private String companyCode;

    @Schema(title = "企业id")
    private Long companyId;

    @Schema(title = "企业名称")
    private String companyName;

    @Schema(title = "创建时间")
    private OffsetDateTime createTime;

    private Boolean authLimited = Boolean.TRUE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setCreateTime(OffsetDateTime createTime) {
        this.createTime = createTime;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public Boolean getAuthLimited() {
        return authLimited;
    }

    public void setAuthLimited(Boolean authLimited) {
        this.authLimited = authLimited;
    }

    public RoleVo(Long id, String roleName, Integer roleLevel, String companyCode, Long companyId, String companyName, LocalDateTime createTime) {
        this.id = id;
        this.roleName = roleName;
        this.roleLevel = roleLevel;
        this.companyCode = companyCode;
        this.companyId = companyId;
        this.companyName = companyName;
        this.createTime = OffsetDateTime.of(createTime, OffsetDateTime.now().getOffset());
    }
}
