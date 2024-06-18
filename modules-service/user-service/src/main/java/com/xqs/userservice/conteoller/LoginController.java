package com.xqs.userservice.conteoller;

import com.xqs.commoncore.base.response.BaseResponseDTO;
import com.xqs.commoncore.exception.MyException;
import com.xqs.commoncore.util.RedisUtil;
import com.xqs.userservice.request.LoginRequestDTO;
import com.xqs.userservice.response.LoginResponseDTO;
import com.xqs.userservice.service.LoginService;
import com.xqs.userservice.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @Resource
    private UserService userService;

    @RequestMapping("/register")
    public BaseResponseDTO<LoginResponseDTO> register(@Validated @RequestBody LoginRequestDTO request) throws MyException, InterruptedException {
        return BaseResponseDTO.success(loginService.register(request));
    }

    @RequestMapping("/login")
    public BaseResponseDTO<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) throws MyException {
        return BaseResponseDTO.success(loginService.login(request));
    }


}
