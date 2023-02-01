package com.iot.common.entity.user;

import com.iot.common.data.constant.ModelConstants;
import com.iot.common.data.constant.SysUserStatusEnum;
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
@Table(name = ModelConstants.SYS_USER_TABLE,schema = ModelConstants.SCHEMA_PERMISSION)
public class UserEntity extends BaseEntity {

    @Column(columnDefinition = "varchar(150)",nullable = false)
    private String username;

    @Column(columnDefinition = "varchar(150)",nullable = false)
    private String password;

    @Column(columnDefinition = "int8",nullable = false)
    private Long companyId;

    @Column(columnDefinition = "varchar(150)",nullable = false)
    private String companyCode;

    @Column(columnDefinition = "varchar(200)")
    private String name;

    /**
     * 账户状态
     */
    @Column(columnDefinition = "smallint",nullable = false)
    private Integer status = SysUserStatusEnum.NORMAL.getCode();

    @Column(columnDefinition = "varchar(200)",nullable = false)
    private String email;

    @Column(columnDefinition = "varchar(11)")
    private String phone;

    /**
     * 职位
     */
    @Column(columnDefinition = "varchar(200)")
    private String positionName;

    /**departmentName
     * 部门
     */
    @Column(columnDefinition = "varchar(200)")
    private String departmentName;
}
