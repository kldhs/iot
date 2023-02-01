package com.iot.common.entity.company;


import com.iot.common.entity.base.BaseEntity;
import com.iot.common.data.constant.ModelConstants;
import com.iot.common.data.enums.CompanyStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;


@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Entity
@Table(name = ModelConstants.SYS_COMPANY_TABLE,schema = ModelConstants.SCHEMA_PERMISSION)
@Inheritance()
public class CompanyEntity extends BaseEntity {

    @Column(nullable = false,columnDefinition = "varchar(50)")
    private String companyCode;

    @Column(nullable = false,columnDefinition = "varchar(200)")
    private String companyName;

    @Column(columnDefinition = "varchar(1000)")
    private String description;

    /**
     * 1：正常，2冻结
     */
    @Column(columnDefinition = "int2 default 1")
    private Integer status = CompanyStatusEnum.NORMAL.getCode();

}
