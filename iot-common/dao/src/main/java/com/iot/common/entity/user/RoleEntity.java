package com.iot.common.entity.user;

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
@Table(name = ModelConstants.SYS_ROLE_TABLE,schema = ModelConstants.SCHEMA_PERMISSION)
public class RoleEntity extends BaseEntity {

    /**
     * 角色名称
     */
    @Column(columnDefinition = "varchar(200)",nullable = false)
    private String roleName;

    /**
     * 角色等级1、2
     */
    @Column(columnDefinition = "smallint",nullable = false)
    private Integer roleLevel;

    /**
     * 公司id
     */
    @Column(columnDefinition = "int8",nullable = false)
    private Long companyId;

    /**
     * 公司编号
     */
    @Column(columnDefinition = "varchar(150)",nullable = false)
    private String companyCode;

    /**
     * 描述
     */
    @Column(columnDefinition = "varchar(1000)")
    private String description;

    /**
     * 是否是管理员角色 默认都是客户端
     */
    private Boolean admin = Boolean.FALSE;

}