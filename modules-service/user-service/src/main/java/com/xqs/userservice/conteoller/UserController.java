package com.xqs.userservice.conteoller;

import com.xqs.commoncore.base.response.BaseResponseDTO;
import com.xqs.commoncore.exception.MyException;
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
    public BaseResponseDTO<List<UserInfo>> getAllUserList() throws MyException {
        if (1 == 1) {
            throw new MyException(".....");
        }
        return BaseResponseDTO.success();
    }

}
