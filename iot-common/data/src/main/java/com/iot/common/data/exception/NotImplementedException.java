package com.iot.common.data.exception;

import com.iot.common.data.constant.IResult;

/**
 * 接口或方法未实现异常
 */
public class NotImplementedException extends BusinessException {

    public NotImplementedException(IResult iResult) {
        super(iResult);
    }
}
