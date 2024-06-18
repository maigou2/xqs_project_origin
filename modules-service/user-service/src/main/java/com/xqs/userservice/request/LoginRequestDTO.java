package com.xqs.userservice.request;

import com.xqs.commoncore.base.request.BaseRequestDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginRequestDTO extends BaseRequestDTO {
    @ApiModelProperty("邮箱地址")
    private String email;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("密码-确认")
    private String passwordSec;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("用户类型 1:前端用户,2商户用户,3后台管理员 逗号隔开")
    private String userType;
}
