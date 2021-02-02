package com.iot.common.data.model.dto.permission;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iot.common.data.model.bo.page.PageAndSearch;
import com.iot.common.data.model.vo.security.DataPermissionInfoVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PermissionDataBaseDto extends PageAndSearch {

    @Schema(hidden = true)
    private String userName;

    @Schema(hidden = true)
    private Long roleId;

    @Schema(hidden = true)
    private String companyCode;

    @Schema(hidden = true)
    private Long companyId;

    /**
     * 是否有权限限制。
     */
    @Schema(hidden = true)
    private Boolean isLimit;

    /**
     * 可以访问的 产品ID 列表
     */
    @Schema(hidden = true)
    private List<Long> accessProductIdList;

    /**
     * 可以访问的 零件ID 列表
     */
    @Schema(hidden = true)
    private List<Long> accessPartIdList;

    /**
     * 可以访问的 策略ID 列表
     */
    @Schema(hidden = true)
    private List<Long> accessStrategyIdList;

    /**
     * 可以访问的 任务ID 列表
     */
    @Schema(hidden = true)
    private List<Long> accessTaskIdList;


    @JsonIgnore
    public void initPermission(DataPermissionInfoVo data){
        this.accessPartIdList = data.getAccessPartIdList();
        this.accessProductIdList = data.getAccessProductIdList();
        this.accessStrategyIdList = data.getAccessStrategyIdList();
        this.accessTaskIdList = data.getAccessTaskIdList();
        this.roleId = data.getRoleId();
        this.isLimit = data.getIsLimit();
    }

    @JsonIgnore
    public void initFailed(){
        this.accessPartIdList = List.of();
        this.accessProductIdList = List.of();
        this.accessStrategyIdList = List.of();
        this.accessTaskIdList = List.of();
        this.isLimit = true;
    }


}
