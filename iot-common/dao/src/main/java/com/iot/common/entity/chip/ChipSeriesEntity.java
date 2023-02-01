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
@Table(name = ModelConstants.CHIP_SERIES_TABLE)
@TypeDef(name = "json", typeClass = JsonType.class)
public class ChipSeriesEntity extends BaseEntity {

    @Column(columnDefinition = "int8",nullable = false)
    private Long chipManufacturerId;

    @Column(nullable = false,length = 200)
    private String seriesName;

}
