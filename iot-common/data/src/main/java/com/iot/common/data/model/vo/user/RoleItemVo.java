package com.iot.common.data.model.vo.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@NoArgsConstructor
public class RoleItemVo {
    @Schema(title = "角色id")
    private Long id;
    @Schema(title = "企业id")
    private Long companyId;
    @Schema(title = "企业名称")
    private String companyName;
    @Schema(title = "角色等级")
    private Integer roleLevel;
    @Schema(title = "角色名称")
    private String roleName;
    @Schema(title = "创建时间")
    private OffsetDateTime createTime;
    @Schema(hidden = true)
    @JsonIgnore
    private LocalDateTime createTimeDb;
    @Schema(title = "描述")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public LocalDateTime getCreateTimeDb() {
        return createTimeDb;
    }

    public void setCreateTimeDb(LocalDateTime createTimeDb) {
        this.createTimeDb = createTimeDb;
        this.createTime = OffsetDateTime.of(createTimeDb, OffsetDateTime.now().getOffset());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public RoleItemVo(Long id, Long companyId, String companyName, Integer roleLevel, String roleName, LocalDateTime createTimeDb, String description) {
        this.id = id;
        this.companyId = companyId;
        this.companyName = companyName;
        this.roleLevel = roleLevel;
        this.roleName = roleName;
        this.createTimeDb = createTimeDb;
        this.description = description;
        this.createTime = OffsetDateTime.of(createTimeDb, OffsetDateTime.now().getOffset());
    }
}
