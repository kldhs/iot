package com.iot.common.data.model.vo.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Schema(name = "CompanyVo", description = "企业")
@NoArgsConstructor
@AllArgsConstructor
public class CompanyVo {
    @Schema(title ="企业id")
    private Long id;

    @Schema(title ="企业编号")
    private String companyCode;

    @Schema(title ="企业名称")
    private String companyName;

    @Schema(title ="添加人")
    private String createUname;

    @Schema(title ="添加时间")
    private OffsetDateTime createTime;

    @Schema(hidden = true)
    @JsonIgnore
    private LocalDateTime createTimeDb;

    @Schema(title ="状态",description = "1正常，2冻结")
    private Integer status;

    @Schema(title ="企业描述")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCreateUname() {
        return createUname;
    }

    public void setCreateUname(String createUname) {
        this.createUname = createUname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
