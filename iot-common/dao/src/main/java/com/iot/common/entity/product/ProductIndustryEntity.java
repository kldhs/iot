package com.iot.common.entity.product;


import com.iot.common.data.constant.ModelConstants;
import com.iot.common.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 产品行业类型
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = ModelConstants.TABLE_PRODUCT_INDUSTRY)
public class ProductIndustryEntity extends BaseEntity {

    /**
     * 行业类型名称
     */
    @Column(length = 200, nullable = false)
    private String industryName;

    /**
     * 备注
     */
    @Column(columnDefinition = "text")
    private String remark;
}
