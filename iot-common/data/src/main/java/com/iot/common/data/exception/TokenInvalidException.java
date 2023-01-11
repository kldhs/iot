package com.iot.common.data.exception;

import com.abupdate.ucc.common.data.result.IResult;

public class TokenInvalidException extends BaseException {

    public TokenInvalidException(String message) {
        super(message);
    }

    public TokenInvalidException(IResult iResult) {
        super(iResult);
    }
}
