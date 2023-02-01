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
@Table(name = ModelConstants.TABLE_COMPANY_BRAND)
public class CompanyBrandEntity extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String brandCode;

    @Column(length = 200, nullable = false)
    private String brandName;

    @Column(length = 150, nullable = false)
    private String companyCode;

    @Column(length = 1000)
    private String remark;
}
