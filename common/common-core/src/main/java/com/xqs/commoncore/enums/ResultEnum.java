package com.xqs.commoncore.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 迪迦.
 * @Date 2023/8/8 15:12
 */
public enum ResultEnum {
  SUCCESS(200,"成功"),
  FAIL(201,"失败"),
  NOT_LOGIN(40001,"未登录"),
  OTHER(50000,"未登录"),

  ;

  public Integer getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  private Integer code;
  private String message;

  ResultEnum(Integer code, String message) {
    this.code = code;
    this.message = message;
  }


}

