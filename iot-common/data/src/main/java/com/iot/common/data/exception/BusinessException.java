package com.iot.common.data.exception;



import com.iot.common.data.constant.CommonResult;
import com.iot.common.data.constant.IResult;

import java.io.Serial;

public class BusinessException extends BaseException {

    @Serial
    private static final long serialVersionUID = -706771652755761338L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(IResult iResult) {
        super(iResult);
    }

    public BusinessException(IResult iResult, String msg) {
        super(iResult, msg);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonResult<Object> getResultCode() {
        return new CommonResult<>(getCode(), getMsg(), null);
    }
}
