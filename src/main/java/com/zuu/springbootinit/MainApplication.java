package com.zuu.springbootinit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/17 12:35
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zuu.springbootinit.user.mapper")
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class);
    }
}
