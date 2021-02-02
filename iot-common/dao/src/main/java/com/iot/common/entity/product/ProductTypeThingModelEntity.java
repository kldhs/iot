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
@Table(name = ModelConstants.PRODUCT_TYPE_THING_MODEL)
public class ProductTypeThingModelEntity extends BaseEntity {

    @Column(nullable = false)
    private Long productTypeId;

    @Column(length = 150, nullable = false)
    private String productTypeName;

    @Column(nullable = false)
    private Long thingModelId;

}
