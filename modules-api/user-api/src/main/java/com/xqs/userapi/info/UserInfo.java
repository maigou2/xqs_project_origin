package com.xqs.userapi.info;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_user")
public class UserInfo implements Serializable {
    @TableField(value = "ID")
    private Long id;
    @TableField(value = "CODE")
    private String code;
    @TableField(value = "USER_NAME")
    private String userName;
    @TableField(value = "USER_EMAIL")
    private String userEmail;
    @JsonIgnore
    @TableField(value = "USER_PASSWORD")
    private String userPassword;
    @TableField(value = "USER_PHONE")
    private String userPhone;
    @TableField(value = "USER_ADDR")
    private String userAddr;
    @TableField(value = "USER_AGE")
    private Integer userAge;
    @TableField(value = "USER_SXE")
    private String userSxe;
    @TableField(value = "USER_TYPE")
    private String userType;
    @TableField(value = "STATUS_TYPE")
    private Integer statusType;
    @TableField(value = "GMT_CREATE")
    private Date gmtCreate;
    @TableField(value = "GMT_MODIFY")
    private Date gmtModify;

}
