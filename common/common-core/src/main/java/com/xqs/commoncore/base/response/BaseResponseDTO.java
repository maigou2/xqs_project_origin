package com.xqs.commoncore.base.response;

import java.io.Serializable;

import com.xqs.commoncore.enums.ResultEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 迪迦.
 * @Date 2023/8/8 15:03
 */
@Data
public class BaseResponseDTO<T> implements Serializable {

    @ApiModelProperty("状态码")
    private Integer code;

    @ApiModelProperty("返回信息")
    private String message;

    private Boolean success = true;

    private String errMessage = "";

    private String errCause = "";

    private T data;

    public static <T> BaseResponseDTO<T> success(T data) {
        BaseResponseDTO<T> resultData = new BaseResponseDTO<>();
        resultData.setCode(ResultEnum.SUCCESS.getCode());
        resultData.setMessage(ResultEnum.SUCCESS.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> BaseResponseDTO<T> success(String msg) {
        BaseResponseDTO<T> resultData = new BaseResponseDTO<>();
        resultData.setCode(ResultEnum.SUCCESS.getCode());
        resultData.setMessage(msg);
        resultData.setData((T) msg);
        return resultData;
    }

    public static <T> BaseResponseDTO<T> success() {
        BaseResponseDTO<T> resultData = new BaseResponseDTO<>();
        resultData.setCode(ResultEnum.SUCCESS.getCode());
        resultData.setMessage(ResultEnum.SUCCESS.getMessage());
        return resultData;
    }

    public static <T> BaseResponseDTO<T> fail() {
        BaseResponseDTO<T> resultData = new BaseResponseDTO<>();
        resultData.setCode(ResultEnum.FAIL.getCode());
        resultData.setMessage(ResultEnum.FAIL.getMessage());
        resultData.setSuccess(false);
        return resultData;
    }

    public static <T> BaseResponseDTO<T> fail(Integer code, String msg) {
        BaseResponseDTO<T> resultData = new BaseResponseDTO<>();
        resultData.setCode(code);
        resultData.setMessage(msg);
        resultData.setSuccess(false);
        return resultData;
    }

    public static <T> BaseResponseDTO<T> fail(String msg) {
        BaseResponseDTO<T> resultData = new BaseResponseDTO<>();
        resultData.setCode(ResultEnum.FAIL.getCode());
        resultData.setMessage(msg);
        resultData.setSuccess(false);
        return resultData;
    }

    public static <T> BaseResponseDTO<T> fail(String errMessage, String errCause) {
        BaseResponseDTO<T> resultData = new BaseResponseDTO<>();
        resultData.setCode(ResultEnum.FAIL.getCode());
        resultData.setMessage(ResultEnum.FAIL.getMessage());
        resultData.setSuccess(false);
        resultData.setErrMessage(errMessage);
        resultData.setErrCause(errCause);
        return resultData;
    }

}
