package com.iot.common.data.exception;

import com.iot.common.data.constant.IResult;

import java.io.Serial;

public class AuthFailedException extends BaseException {
    @Serial
    private static final long serialVersionUID = -4625947318784998075L;

    public AuthFailedException(String message) {
        super(message);
    }

    public AuthFailedException(IResult iResult) {
        super(iResult);
    }
}
