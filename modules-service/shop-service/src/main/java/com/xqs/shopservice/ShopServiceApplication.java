package com.xqs.shopservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.xqs.shopservice.mapper")
@SpringBootApplication
@ComponentScan(basePackages = {"com.xqs.commoncore", "com.xqs.shopservice"})
public class ShopServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopServiceApplication.class, args);
    }

}
