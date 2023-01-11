package com.iot.common.data.model.vo.security;

import lombok.Data;

import java.util.List;

/**
 * content: 用户数据权限
 */
@Data
public class DataPermissionInfoVo {
    /**
     * roleId
     */
    private Long roleId;
    /**
     * companyCode
     */
    private String companyCode;
    /**
     * companyId
     */
    private Long companyId;

    /**
     * 是否有权限限制
     */
    private Boolean isLimit;

    /**
     * 可以访问的 产品ID 列表
     */
    private List<Long> accessProductIdList;

    /**
     * 可以访问的 零件ID 列表
     */
    private List<Long> accessPartIdList;

    /**
     * 可以访问的 策略ID 列表
     */
    private List<Long> accessStrategyIdList;

    /**
     * 可以访问的 任务ID 列表
     */
    private List<Long> accessTaskIdList;
}
