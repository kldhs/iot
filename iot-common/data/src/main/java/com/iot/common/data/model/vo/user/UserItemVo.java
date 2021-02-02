package com.iot.common.data.model.vo.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * @author wzh
 * @date 2022/7/21 14:38
 */
@NoArgsConstructor
public class UserItemVo {

    @Schema(title = "用户id")
    private Long id;

    @Schema(title = "角色名称")
    private String roleName;

    @Schema(title = "角色等级")
    private Integer roleLevel;

    @Schema(title = "企业code")
    private String companyCode;

    @Schema(title = "企业名称")
    private String companyName;

    @Schema(title = "邮箱")
    private String email;

    @Schema(title = "状态1正常，2禁用")
    private Integer status;

    @Schema(title = "最近登录时间")
    private OffsetDateTime lastLoginTime;

    @Schema(hidden = true)
    @JsonIgnore
    private LocalDateTime lastLoginTimeDb;

    @Schema(title = "创建时间")
    private OffsetDateTime createTime;

    @Schema(hidden = true)
    @JsonIgnore
    private LocalDateTime createTimeDb;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public OffsetDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(OffsetDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public LocalDateTime getLastLoginTimeDb() {
        return lastLoginTimeDb;
    }

    public void setLastLoginTimeDb(LocalDateTime lastLoginTimeDb) {
        this.lastLoginTimeDb = lastLoginTimeDb;
        this.lastLoginTime = OffsetDateTime.of(lastLoginTimeDb, OffsetDateTime.now().getOffset());
    }

    public LocalDateTime getCreateTimeDb() {
        return createTimeDb;
    }

    public void setCreateTimeDb(LocalDateTime createTimeDb) {
        this.createTimeDb = createTimeDb;
        this.createTime = OffsetDateTime.of(createTimeDb, OffsetDateTime.now().getOffset());
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public UserItemVo(Long id, String roleName, Integer roleLevel, String companyCode, String companyName, String email, Integer status, LocalDateTime lastLoginTimeDb, LocalDateTime createTimeDb) {
        this.id = id;
        this.roleName = roleName;
        this.roleLevel = roleLevel;
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.email = email;
        this.status = status;
        this.lastLoginTimeDb = lastLoginTimeDb;
        this.createTimeDb = createTimeDb;
        this.lastLoginTime = Objects.nonNull(lastLoginTimeDb)?OffsetDateTime.of(lastLoginTimeDb, OffsetDateTime.now().getOffset()):null;
        this.createTime = OffsetDateTime.of(createTimeDb, OffsetDateTime.now().getOffset());
    }
}
