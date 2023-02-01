package com.iot.common.data.model.bo.page;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Objects;

/**
 * 基础分页
 *
 * @param <T> vo
 */
public class PageData<T> {

    private final List<T> list;

    private final Integer totalPages;

    private final Long totalElements;

    private final Boolean hasNext;

    public PageData() {
        this(List.of(), 0, 0L, false);
    }

    @JsonCreator
    public PageData(@JsonProperty("list") List<T> list,
                    @JsonProperty("totalPages") Integer totalPages,
                    @JsonProperty("totalElements") Long totalElements,
                    @JsonProperty("hasNext") Boolean hasNext) {
        this.list = list;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.hasNext = hasNext;
    }

    public static <T> PageData<T> empty() {
        return new PageData<>();
    }

    public static <T> PageData<T> of(Page<T> page) {
        return new PageData<>(page.getContent(), page.getTotalPages(), page.getTotalElements(), page.hasNext());
    }

    public static <T, R> PageData<T> of(Page<R> page, List<T> list) {
        return new PageData<>(list, page.getTotalPages(), page.getTotalElements(), page.hasNext());
    }

    public static <T, R> PageData<T> of(Page<R> page, Class<T> cls) {
        if (page.getContent().isEmpty()) return new PageData<>();
        var list = page.getContent().stream().map(it -> {
            try {
                var obj = cls.getConstructor().newInstance();
                BeanUtils.copyProperties(it, obj);
                return obj;
            } catch (Exception e) {
                throw new IllegalArgumentException("page ele create instance error");
            }

        }).toList();
        return of(page, list);

    }

    public static <T> PageData<T> of(List<T> list, Integer totalPages, Long totalSize, Integer page) {
        return new PageData<>(list, totalPages, totalSize, page < totalPages);
    }

    public static <T, E> PageData<E> of(PageData<T> pageData, List<E> list) {
        return new PageData<>(list, pageData.getTotalPages(), pageData.getTotalElements(), pageData.getHasNext());
    }

    public List<T> getList() {
        return list;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageData<?> pageData = (PageData<?>) o;
        return Objects.equals(list, pageData.list) && Objects.equals(totalPages, pageData.totalPages) && Objects.equals(totalElements, pageData.totalElements) && Objects.equals(hasNext, pageData.hasNext);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list, totalPages, totalElements, hasNext);
    }

    @Override
    public String toString() {
        return "PageData{" +
                "list=" + list +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", hasNext=" + hasNext +
                '}';
    }
}
