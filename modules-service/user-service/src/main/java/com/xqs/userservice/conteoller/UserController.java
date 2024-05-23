package com.xqs.userservice.conteoller;

import com.xqs.userapi.info.UserInfo;
import com.xqs.userservice.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/getAllUserList")
    public List<UserInfo> getAllUserList() {
        return userService.getAllUserList();
    }

}
