package com.xqs.userservice.conteoller;

import com.xqs.commoncore.base.response.BaseResponseDTO;
import com.xqs.commoncore.util.RedisUtil;
import com.xqs.userservice.request.redis.RedisTestRequestDTO;
import com.xqs.userservice.service.RedisTestService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private RedisTestService redisTestService;

    /**
     * 测试redis数据类型操作.
     */
    @RequestMapping("testRedisPushList")
    public BaseResponseDTO testRedisPushList(@RequestBody RedisTestRequestDTO request) throws InterruptedException {
        redisTestService.testRedisPushList(request);
        return BaseResponseDTO.success();
    }

    /**
     * 测试redis数据类型操作.
     */
    @RequestMapping("testRedisPopList")
    public BaseResponseDTO testRedisPopList(@RequestBody RedisTestRequestDTO request) throws InterruptedException {
        redisTestService.testRedisPopList(request);
        return BaseResponseDTO.success();
    }


    /**
     * 测试redis数据类型操作.
     */
    @RequestMapping("testAddRedisMaxCount")
    public BaseResponseDTO testAddRedisMaxCount(@RequestBody RedisTestRequestDTO request) throws InterruptedException {
        redisTestService.testAddRedisMaxCount(request);
        return BaseResponseDTO.success();
    }



    /**
     * 测试redis数据类型操作.
     */
    @RequestMapping("buySomething")
    public BaseResponseDTO<String> testAddRedisAddOrSub(@RequestBody RedisTestRequestDTO request) throws InterruptedException {

        return BaseResponseDTO.success(redisTestService.testAddRedisAddOrSub(request));
    }

}
