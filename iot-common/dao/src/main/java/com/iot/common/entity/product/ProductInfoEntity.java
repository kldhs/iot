package com.iot.common.entity.product;

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

/**
 * 产品信息
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@TypeDef(name = "json", typeClass = JsonType.class)
@Entity
@Table(name = ModelConstants.TABLE_PRODUCT_INFO)
public class ProductInfoEntity extends BaseEntity {

    /**
     * 产品key
     */
    @Column(length = 20, nullable = false)
    private String productKey;

    /**产品密码*/
    @Column(nullable = false)
    private String productSecret;

    /**产品名称*/
    @Column(length = 50, nullable = false)
    private String productName;

    /**
     * 企业id
     */
    @Column(nullable = false)
    private Long companyId;

    /**
     * 产品类型id
     */
    @Column(nullable = false)
    private Long productTypeId;

    /**
     * 产品类型名称
     */
    @Column(length = 150, nullable = false)
    private String productTypeName;

    /**
     * 联网方式
     * 1.Wi-Fi, 2.蜂窝, 3.以太网, 4.其他
     */
    @Column(nullable = false, columnDefinition = "int2")
    private Integer netType;


    /**
     * 加密方式
     * 1.设备密钥, 2.证书加密
     */
    @Column(nullable = false, columnDefinition = "int2")
    private Integer encryptType;

    /**
     * 品牌id
     */
    @Column(nullable = false)
    private Long brandId;

    /**
     * 品牌编码
     */
    @Column(nullable = false)
    private String brandCode;

    /**
     * 销售区域
     */
    @Column(length = 50, nullable = false)
    private String saleArea;

    /**
     * 描述
     */
    @Column(length = 50)
    private String description;


    @Column(length = 50)
    private String fileId;

}
