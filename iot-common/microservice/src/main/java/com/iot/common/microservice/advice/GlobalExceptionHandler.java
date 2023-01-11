package com.iot.common.microservice.advice;

import com.iot.common.data.constant.CommonResult;
import com.iot.common.data.enums.ResultEnum;
import com.iot.common.data.exception.AuthFailedException;
import com.iot.common.data.exception.BusinessException;
import com.iot.common.data.exception.NotImplementedException;
import com.iot.common.data.exception.ResourceNotFountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(Exception.class)
    //指定了此异常返回的状态码
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult<Object> handleException(Exception e, HttpServletRequest request) {
        log.warn(" request path:{}", request.getRequestURI());
        log.warn(">>>>>>>>>>>>>>>>>>>>>>>>系统未知异常<<<<<<<<<<<<<<<<<<<<<<<<<<");
        log.warn(e.getMessage(), e);
        return CommonResult.failed(ResultEnum.UNKONW_ERROR);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResult<Object> handleNotFoundError(NoHandlerFoundException ex) {
        log.warn("URL NOT FOUNT: {}", ex.getMessage());
        return CommonResult.failed(ResultEnum.NOT_FOUND);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, MethodArgumentTypeMismatchException.class})
    public CommonResult<Object> handleKnownException(Exception e, HttpServletResponse response) {
        if (e instanceof HttpRequestMethodNotSupportedException) {
            response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
            return CommonResult.failed(ResultEnum.METHOD_NOT_ALLOWED);
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return CommonResult.failed(ResultEnum.FAILED);
        }
        return CommonResult.failed(ResultEnum.UNKONW_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public CommonResult<Object> handleBusinessException(BusinessException e) {
        log.info(">>>>  系统业务错误 --> {} : {}", e.getMessage(), e.getCode());
        log.error("", e);
        return new CommonResult<>(e.getCode(), e.getMessage(), null);
    }

    @ExceptionHandler(ResourceNotFountException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResult<Object> handleNotFoundException(ResourceNotFountException e) {
        log.info(">>>>  资源未找到 --> {}:{}", e.getMessage(), e.getCode());
        return new CommonResult<>(e.getCode(), e.getMessage(), null);
    }

    @ExceptionHandler(NotImplementedException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public CommonResult<Object> handleNotImplementException(NotImplementedException e, HttpServletResponse response) {
        log.info(">>>>  请求接口未实现  --> {}:{}", e.getMessage(), e.getCode());

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new CommonResult<>(e.getCode(), e.getMessage(), null);
    }

    @ExceptionHandler(AuthFailedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResult<Object> handleAuthFailedException(AuthFailedException e, HttpServletResponse response) {
        log.info(">>>> 签名无效 --> {}:{}", e.getMessage(), e.getCode());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return new CommonResult<>(e.getCode(), e.getMessage(), null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public CommonResult<Object> handleParameterException(MissingServletRequestParameterException e) {
        log.info(">>>>  系统业务异常 --> {}", e.getMessage());
        return new CommonResult<>(ResultEnum.PARAMETER_ERROR.getCode(), e.getMessage(), null);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        log.info(">>>>  系统业务异常 --> {}", e.getMessage());
        return new CommonResult<>(ResultEnum.PARAMETER_ERROR.getCode(), ResultEnum.PARAMETER_ERROR.getMsg(), null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public CommonResult<Object> bindException(BindException e) {
        log.info(">>>  参数绑定异常 --> {}", e.getMessage());
        StringJoiner joiner = new StringJoiner(";");
        for (var err : e.getAllErrors()) {
            if (err instanceof FieldError fieldError) {
                joiner.add(fieldError.getField() + fieldError.getDefaultMessage());
            } else {
                joiner.add(err.getObjectName() + err.getDefaultMessage());
            }
        }
        return new CommonResult<>(ResultEnum.PARAMETER_ERROR.getCode(), joiner.toString(), null);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public CommonResult<Object> handlerParamValid(Exception e, HttpServletRequest request) {
        if (e instanceof MethodArgumentNotValidException br) {
            List<FieldError> fes = br.getFieldErrors();
            StringJoiner joiner = new StringJoiner(";");
            for (FieldError fe : fes) {
                joiner.add(fe.getDefaultMessage());
            }
            log.info(">>>> 参数校验异常 ---> {}", joiner);
            return new CommonResult<>(ResultEnum.PARAMETER_ERROR.getCode(), joiner.toString(), null);
        } else if (e instanceof ConstraintViolationException cve) {
            StringJoiner joiner = new StringJoiner(",");
            Set<ConstraintViolation<?>> items = cve.getConstraintViolations();
            items.forEach(it -> joiner.add(it.getMessage()));
            log.info(">>>> 参数校验异常 ---> {}", e.getMessage());
            return new CommonResult<>(ResultEnum.PARAMETER_ERROR.getCode(), joiner.toString(), null);
        }
        return handleException(e, request);
    }
}
