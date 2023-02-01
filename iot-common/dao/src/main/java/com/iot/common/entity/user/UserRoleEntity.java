package com.iot.common.entity.user;

import com.iot.common.data.constant.ModelConstants;
import com.iot.common.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = ModelConstants.SYS_USER_ROLE_TABLE,schema = ModelConstants.SCHEMA_PERMISSION)
public class UserRoleEntity  extends BaseEntity {

    @Column(columnDefinition = "int8",nullable = false)
    private Long userId;

    @Column(columnDefinition = "int8",nullable = false)
    private Long roleId;
}
