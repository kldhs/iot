package com.iot.common.entity.product;

import com.fasterxml.jackson.databind.JsonNode;
import com.iot.common.data.constant.ModelConstants;
import com.iot.common.entity.base.BaseEntity;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@TypeDef(name = "json", typeClass = JsonType.class)
@Entity
@Table(name = ModelConstants.PRODUCT_THING_MODEL)
public class ThingModelEntity extends BaseEntity {

    @Column(nullable = false)
    private Long productId;

    @Column(columnDefinition = "varchar(50) not null default ''")
    private String productKey;

    @Column(nullable = false)
    private Integer funcType;

    /**
     * 功能名称
     */
    @Column(length = 150, nullable = false)
    private String funcName;

    @Column(length = 50, nullable = false)
    private String funcId;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private JsonNode define;

    @Column(columnDefinition = "text")
    private String description;

    /**
     * 是否是标准功能
     */
    @Column(columnDefinition = "bool not null default false")
    private Boolean isStandard = Boolean.FALSE;

    /**
     * 模型有效数据开始时间
     */
    private Long dataTs;

    /**
     * 用于执行条件
     */
    @Column(nullable = false)
    private Boolean useTrigger = Boolean.FALSE;

    /**
     * 用于执行动作
     */
    @Column(nullable = false)
    private Boolean useExecutor = Boolean.FALSE;
}
