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
@Table(name = ModelConstants.PERMISSION_DATA_TASK_TABLE,schema = ModelConstants.SCHEMA_PERMISSION)
public class PermissionDataTaskEntity extends BaseEntity {

    @Column(columnDefinition = "int8",nullable = false)
    private Long roleId;

    @Column(columnDefinition = "int8",nullable = false)
    private Long taskId;

    @Column(columnDefinition = "int8",nullable = false)
    private Long strategyId;


}
