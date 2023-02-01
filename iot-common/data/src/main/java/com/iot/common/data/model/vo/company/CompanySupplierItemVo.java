package com.iot.common.data.model.vo.company;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class CompanySupplierItemVo {
    private Long id;
    private String supplierCode;
    private String supplierName;
    private String remark;
    private String createUname;
    private OffsetDateTime createTime;
    @JsonIgnore
    private LocalDateTime createTimeLocal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public LocalDateTime getCreateTimeLocal() {
        return createTimeLocal;
    }

    public void setCreateTimeLocal(LocalDateTime createTimeLocal) {
        this.createTimeLocal = createTimeLocal;
        this.createTime = OffsetDateTime.of(createTimeLocal, OffsetDateTime.now().getOffset());
    }


}
