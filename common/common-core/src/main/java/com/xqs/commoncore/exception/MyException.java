package com.xqs.commoncore.exception;

import com.xqs.commoncore.enums.ResultEnum;
import lombok.Getter;

@Getter
public class MyException extends RuntimeException{

    private Integer code = ResultEnum.OTHER.getCode();

    public MyException() {
        super();
    }

    public MyException(String msg) {
        super(msg);
    }

    public MyException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

}
