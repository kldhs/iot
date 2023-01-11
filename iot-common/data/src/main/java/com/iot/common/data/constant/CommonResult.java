package com.iot.common.data.constant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iot.common.data.enums.ResultEnum;
import com.iot.common.data.exception.BaseException;
import lombok.Data;

@Data
public class CommonResult<T> {

    private Integer code;

    private String msg;

    private T data;

    public CommonResult() {
    }

    public CommonResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> CommonResult<T> success() {
        return new CommonResult<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), null);
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), data);
    }

    public static <T> CommonResult<T> failed(ResultEnum resultEnum) {
        return new CommonResult<>(resultEnum.getCode(), resultEnum.getMsg(), null);
    }

    public static <T> CommonResult<T> failed(BaseException e) {
        return new CommonResult<>(e.getCode(), e.getMessage(), null);
    }

    public static <T> CommonResult<T> failed(ResultEnum resultEnum, T data) {
        return new CommonResult<>(resultEnum.getCode(), resultEnum.getMsg(), data);
    }

    public static <T> CommonResult<T> failed(BaseException e, T data) {
        return new CommonResult<>(e.getCode(), e.getMessage(), data);
    }

    public static <T> CommonResult<T> failed() {
        return failed(ResultEnum.UNKONW_ERROR);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return code.equals(ResultEnum.SUCCESS.getCode());
    }
}
