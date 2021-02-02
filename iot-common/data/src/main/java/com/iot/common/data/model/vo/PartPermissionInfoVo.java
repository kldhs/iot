package com.iot.common.data.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartPermissionInfoVo {

    @Schema(title = "权限配置id",description = "数据权限主键")
    private Long permissionDataInfoId;

    @Schema(title = "零件id")
    private Long id;

    @Schema(title = "零件名称")
    private String partName;

    @Schema(title = "零件类型")
    private String partTypeCode;

    @Schema(title = "零件编号")
    private String partNumber;

    @Schema(title = "芯片系列名称")
    private String seriesName;

    @Schema(title = "系统类型",description = "1:Linux,2:Android,3:RTOS,4:NonOS")
    private Integer systemType;

    @Schema(title = "系统类型描述")
    private String systemTypeDesc;

    @Schema(title = "芯片名称")
    private String chipModelName;

    @Schema(title = "芯片厂商名称")
    private String manufacturerName;

    @Schema(title = "供应商id")
    private Long supplierId;

    @Schema(title = "供应商编码")
    private String supplierCode;

    @Schema(title = "供应商名称")
    private String supplierName;

    @Schema(title = "升级能力")
    private Integer partInstallTypeCode;

    @Schema(title = "备注")
    private String remark;



}