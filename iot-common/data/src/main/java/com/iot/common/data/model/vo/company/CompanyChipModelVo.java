package com.iot.common.data.model.vo.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Schema(name = "CompanyChipModelVo", description = "企业可用芯片")
@NoArgsConstructor
public class CompanyChipModelVo {

    @Schema(hidden = true)
    private Long id;

    @Schema(title = "芯片型号名称")
    private String chipModelName;

    @Schema(title = "芯片系列id")
    private Long chipSeriesId;

    @Schema(title = "芯片类型名称")
    private String seriesName;

    @Schema(title = "芯片厂商名")
    private String manufacturerName;

    @Schema(title = "操作系统")
    private Integer systemType;

    @Schema(title = "创建人")
    private String createUname;

    @Schema(title = "创建时间")
    private OffsetDateTime createTime;

    @JsonIgnore
    @Schema(hidden = true)
    private LocalDateTime createTimeDb;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChipModelName() {
        return chipModelName;
    }

    public void setChipModelName(String chipModelName) {
        this.chipModelName = chipModelName;
    }

    public Long getChipSeriesId() {
        return chipSeriesId;
    }

    public void setChipSeriesId(Long chipSeriesId) {
        this.chipSeriesId = chipSeriesId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    public String getCreateUname() {
        return createUname;
    }

    public void setCreateUname(String createUname) {
        this.createUname = createUname;
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getCreateTimeDb() {
        return createTimeDb;
    }

    public void setCreateTimeDb(LocalDateTime createTimeDb) {
        this.createTimeDb = createTimeDb;
        this.createTime = OffsetDateTime.of(createTimeDb, OffsetDateTime.now().getOffset());
    }

    public CompanyChipModelVo(Long id, String chipModelName, Long chipSeriesId, String seriesName, String manufacturerName, Integer systemType, String createUname, LocalDateTime createTimeDb) {
        this.id = id;
        this.chipModelName = chipModelName;
        this.chipSeriesId = chipSeriesId;
        this.seriesName = seriesName;
        this.manufacturerName = manufacturerName;
        this.systemType = systemType;
        this.createUname = createUname;
        this.createTimeDb = createTimeDb;
        this.createTime = OffsetDateTime.of(createTimeDb, OffsetDateTime.now().getOffset());
    }
}
