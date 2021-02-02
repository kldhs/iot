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
@Table(name = ModelConstants.PERMISSION_DATA_WHITELIST_ENTITY,schema = ModelConstants.SCHEMA_PERMISSION)
public class PermissionDataWhiteListEntity extends BaseEntity {

    @Column(columnDefinition = "int8",nullable = false)
    private Long roleId;

    @Column(length = 150, nullable = false)
    private String companyCode;

}
