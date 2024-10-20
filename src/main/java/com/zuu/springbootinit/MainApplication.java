package com.zuu.springbootinit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/17 12:35
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zuu.springbootinit.**.mapper")
@EnableAspectJAutoProxy
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class);
    }
}
