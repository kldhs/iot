package com.iot.common.entity.permission;

import com.iot.common.data.constant.ModelConstants;
import com.iot.common.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Entity
@Table(name = ModelConstants.SYS_PERMISSION_TABLE,schema = ModelConstants.SCHEMA_PERMISSION)
public class PermissionEntity extends BaseEntity {

    @Column(columnDefinition = "varchar(200)")
    private String pkey;

    @Column(columnDefinition = "varchar(150)",nullable = false)
    private String permissionName;

    @Column(columnDefinition = "varchar(200)",nullable = false)
    private String permissionKey;

    @Column(columnDefinition = "int2",nullable = false)
    private Integer permissionType;

    @Column(columnDefinition = "varchar(200)")
    private String uri;

    @Column(columnDefinition = "varchar(20)")
    private String method;

    @Column(columnDefinition = "varchar(200)")
    private String icon;

    @Column(columnDefinition = "int2",nullable = false)
    private Integer displayOrder;

    private Boolean available;

    private Boolean isAdmin;

    private Boolean isPublic = Boolean.FALSE;

    private Boolean visible;

}
