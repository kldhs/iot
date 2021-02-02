package com.iot.common.entity.permission;

import com.iot.common.data.constant.ModelConstants;
import com.iot.common.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = ModelConstants.SYS_ROLE_PERMISSION_TABLE,schema = ModelConstants.SCHEMA_PERMISSION)
public class RolePermissionEntity extends BaseEntity {


    @Column(columnDefinition = "int8",nullable = false)
    private Long roleId;

    @Column(columnDefinition = "int8",nullable = false)
    private Long permissionId;

}
