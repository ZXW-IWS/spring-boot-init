package com.zuu.springbootinit.common.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/17 17:16
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.data.redis")
public class RedissonConfig {
    private String host;
    private String port;
    private String password;
    private int database;
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        // 单点模式
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port)
                .setPassword(password)
                .setDatabase(database);
        // 集群模式
    /*config.useClusterServers()
            .addNodeAddress("redis://127.0.0.1:7000")
            .addNodeAddress("redis://127.0.0.1:7001")
            .addNodeAddress("redis://127.0.0.1:7002")
            .addNodeAddress("redis://127.0.0.1:7003")
            .addNodeAddress("redis://127.0.0.1:7004")
            .addNodeAddress("redis://127.0.0.1:7005");*/

        return Redisson.create(config);
    }
}
