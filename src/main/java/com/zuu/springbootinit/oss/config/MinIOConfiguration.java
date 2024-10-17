package com.zuu.springbootinit.oss.config;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/17 17:00
 */

import com.zuu.springbootinit.oss.domain.entity.OssProperties;
import com.zuu.springbootinit.oss.template.MinIOTemplate;
import io.minio.MinioClient;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description  @ConditionalOnExpression("${oss.enabled}")：只有在配置文件中oss.enabled属性为true时，才会激活这个配置类。
 * @Description  @ConditionalOnProperty(value = "oss.type", havingValue = "minio")：只有在配置文件中oss.type的值为minio时，才会激活这个配置类。
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnExpression("${oss.enabled}")
@ConditionalOnProperty(value = "oss.type", havingValue = "minio")
public class MinIOConfiguration {


    @Bean
    @SneakyThrows
    @ConditionalOnMissingBean(MinioClient.class)
    public MinioClient minioClient(OssProperties ossProperties) {
        return MinioClient.builder()
                .endpoint(ossProperties.getEndpoint())
                .credentials(ossProperties.getAccessKey(), ossProperties.getSecretKey())
                .build();
    }

    @Bean
    @ConditionalOnBean({MinioClient.class})
    @ConditionalOnMissingBean(MinIOTemplate.class)
    public MinIOTemplate minioTemplate(MinioClient minioClient, OssProperties ossProperties) {
        return new MinIOTemplate(minioClient, ossProperties);
    }
}
