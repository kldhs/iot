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
@Table(name = ModelConstants.PERMISSION_DATA_ROLE_TABLE,schema = ModelConstants.SCHEMA_PERMISSION)
public class PermissionDataRoleEntity extends BaseEntity {

    @Column(length = 150)
    private String companyCode;

    @Column(columnDefinition = "int8")
    private Long roleId;

    @Column(columnDefinition = "int8")
    private Long dataId;

    /**
     * 1:产品，2：零件
     */
    @Column(columnDefinition = "smallint")
    private Integer dataType;



}
