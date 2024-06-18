package com.xqs.userservice.task;

import com.xqs.commoncore.util.RedisUtil;
import com.xqs.userapi.info.UserInfo;
import com.xqs.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Component
@Slf4j
public class UserInfoTask {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private UserService userService;

    @Resource
    private ExecutorService executorService;

//    @Scheduled(cron = "0/1 * * * * *")
    public void pushUserInfoDB() {
        List<UserInfo> userInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 50; j++) {
                    Object o = redisUtil.opsForList().rightPop("userInfosList");
                    if (ObjectUtils.isEmpty(o)) {
                        break;
                    }
                    userInfos.add((UserInfo) o);
                }
                if (CollectionUtils.isEmpty(userInfos)) {
                    return;
                }
                userService.addAllUsers(userInfos);
            });
        }

    }
}
