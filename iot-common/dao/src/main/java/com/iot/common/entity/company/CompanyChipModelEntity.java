package com.iot.common.entity.company;

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
@Table(name = ModelConstants.COMPANY_CHIP_MODEL_TABLE)
public class CompanyChipModelEntity extends BaseEntity {

    @Column(columnDefinition = "varchar(150)",nullable = false)
    private String companyCode;

    @Column(columnDefinition = "int8",nullable = false)
    private Long chipModelId;

    @Column(columnDefinition = "int2",nullable = false)
    private Integer systemType;

}
