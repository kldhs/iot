package com.iot.common.entity.product;

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
@Table(name = ModelConstants.TABLE_PRODUCT_TYPE)
public class ProductTypeEntity extends BaseEntity {

    @Column(nullable = false)
    private Long industryId;

    @Column(length = 200, nullable = false)
    private String productTypeName;

    private String industryName;

    @Column(length = 1000)
    private String remark;

}
