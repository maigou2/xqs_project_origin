package com.xqs.commoncore.exception;

import com.xqs.commoncore.base.response.BaseResponseDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MyException.class)
    public BaseResponseDTO handleBizException(MyException e) {
        return BaseResponseDTO.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public BaseResponseDTO handleBizException(Exception e) {
        return BaseResponseDTO.fail(e.getMessage());
    }

}
