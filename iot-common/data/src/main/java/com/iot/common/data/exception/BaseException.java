package com.iot.common.data.exception;



import com.iot.common.data.constant.IResult;
import com.iot.common.data.enums.ResultEnum;

import java.io.Serial;

/**
 * 自定义基础异常类
 */
public class BaseException extends RuntimeException implements IResult {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String msg;

    public BaseException(String message) {
        super(message);
        this.code = ResultEnum.UNKONW_ERROR.getCode();
    }

    public BaseException(IResult iResult) {
        super(iResult.getMsg());
        this.code = iResult.getCode();
    }

    public BaseException(IResult iResult, String msg) {
        super(msg);
        this.code = iResult.getCode();
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.code = ResultEnum.UNKONW_ERROR.getCode();
    }

    public BaseException(IResult iResult, Throwable cause) {
        super(iResult.getMsg(), cause);
        this.code = iResult.getCode();
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
