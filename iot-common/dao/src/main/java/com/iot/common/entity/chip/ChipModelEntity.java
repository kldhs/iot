package com.iot.common.entity.chip;


import com.iot.common.data.constant.ModelConstants;
import com.iot.common.entity.base.BaseEntity;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Entity
@Table(name = ModelConstants.CHIP_MODEL_TABLE)
@TypeDef(name = "json", typeClass = JsonType.class)
public class ChipModelEntity extends BaseEntity {

    @Column(columnDefinition = "int8",nullable = false)
    private Long chipManufacturerId;

    @Column(columnDefinition = "int8",nullable = false)
    private Long chipSeriesId;

    @Column(nullable = false,length = 100)
    private String operationSystem;

    @Column(nullable = false,length = 200)
    private String chipModelName;

}
