package com.xqs.userapi.info;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_user")
public class UserInfo {
    @TableField(value = "ID")
    private Integer id;
    @TableField(value = "CODE")
    private String code;
    @TableField(value = "USER_NAME")
    private String userName;
    @TableField(value = "USER_EMAIL")
    private String userEmail;
    @TableField(value = "USER_PASSWORD")
    private String userPassword;
    @TableField(value = "USER_ADDR")
    private String userAddr;
    @TableField(value = "USER_AGE")
    private Integer userAge;
    @TableField(value = "USER_SXE")
    private String userSxe;
    @TableField(value = "GMT_CREATE")
    private Date gmtCreate;
    @TableField(value = "GMT_MODIFY")
    private Date gmtModify;

}
