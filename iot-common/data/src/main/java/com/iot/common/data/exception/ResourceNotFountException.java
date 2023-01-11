package com.iot.common.data.exception;

import com.abupdate.ucc.common.data.result.IResult;

import java.io.Serial;

public class ResourceNotFountException extends BusinessException {
    @Serial
    private static final long serialVersionUID = -7476378431311018624L;

    public ResourceNotFountException(IResult iResult) {
        super(iResult);
    }
}
