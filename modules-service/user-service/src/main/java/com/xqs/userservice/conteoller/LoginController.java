package com.xqs.userservice.conteoller;

import com.xqs.commoncore.base.response.BaseResponseDTO;
import com.xqs.commoncore.util.RedisUtil;
import com.xqs.userapi.info.UserInfo;
import com.xqs.userservice.request.LoginRequestDTO;
import com.xqs.userservice.response.LoginResponseDTO;
import com.xqs.userservice.service.UserService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private RedisUtil redisUtil;

    private final String redisUserKey = "userList";

    @RequestMapping("/register")
    public BaseResponseDTO<LoginResponseDTO> register(@RequestBody LoginRequestDTO request) {
        return BaseResponseDTO.success(userService.register(request));
    }

    @GetMapping("/getRedisValueByName")
    public BaseResponseDTO<String> getAllRedisValue(String key, String name) {
        if (!redisUtil.hasKey(key)) {
            if (redisUtil.hasKey(key + "_java")) {
                return BaseResponseDTO.success(key + "_java" + ":" + redisUtil.get(key + "_java").toString());
            }
            redisUtil.set(key + "_java", name);
            return BaseResponseDTO.success("没有数据");
        }
        Object o = redisUtil.get(key);
        if (ObjectUtils.isEmpty(o)) {
            redisUtil.del(key);
            return BaseResponseDTO.success("有数据但数据为null");
        }
        return BaseResponseDTO.success(o.toString());
    }

    @GetMapping("/updateRedisUserInfos")
    public BaseResponseDTO<String> updateRedisUserInfos() {
        List<UserInfo> allUserList = userService.getAllUserList();
        redisUtil.set(redisUserKey, allUserList);
        return BaseResponseDTO.success();
    }

    @GetMapping("/getAllRedisUserInfos")
    public BaseResponseDTO<List<UserInfo>> getAllRedisUserInfos() {
        List<UserInfo> allUserList = new ArrayList<>();
        if (redisUtil.hasKey(redisUserKey)) {
            allUserList = (List<UserInfo>) redisUtil.get(redisUserKey);
        }

        return BaseResponseDTO.success(allUserList);
    }


    @GetMapping("/delAllRedisUserInfos")
    public BaseResponseDTO<Boolean> delAllRedisUserInfos(String key) {
        return BaseResponseDTO.success(redisUtil.del(key));
    }

}
