package com.iot.common.data.model.bo.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Schema(title = "分页和搜索")
@NoArgsConstructor
public class PageAndSearch {

    @Schema(title = "搜索关键词")
    private String searchText;
    @Min(value = 0, message = "页码最小值为0")
    @NotNull(message = "页码不能为空")
    private Integer pageNumber;
    @Min(value = 1, message = "分页最小值为1")
    @Max(value = 100, message = "分页最大值为100")
    @NotNull(message = "分页不能为空")
    private Integer pageSize;

    public PageAndSearch(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    @JsonIgnore
    public Integer getOffset() {
        return pageNumber * pageSize;
    }

    @JsonIgnore
    public void initPage(PageAndSearch pageAndSearch) {
        this.setSearchText(pageAndSearch.getSearchText());
        this.setPageSize(pageAndSearch.getPageSize());
        this.setPageNumber(pageAndSearch.getPageNumber());
    }
}
