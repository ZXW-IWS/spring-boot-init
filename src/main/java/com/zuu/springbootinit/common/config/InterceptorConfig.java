package com.zuu.springbootinit.common.config;

import com.zuu.springbootinit.common.interceptor.TokenInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/17 17:18
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Resource
    private TokenInterceptor tokenInterceptor;

    //拦截器不进行拦截的路径
    private final static List<String> ignorePaths = new ArrayList<>();

    static {
        // swagger
        ignorePaths.add("/swagger-resources/**");
        ignorePaths.add("/doc.html");
        ignorePaths.add("/v3/**");
        ignorePaths.add("/webjars/**");
        ignorePaths.add("/springdoc/**");
        ignorePaths.add("/static/**");
        ignorePaths.add("/templates/**");
        ignorePaths.add("/error");
        ignorePaths.add("/cipher/check");
        ignorePaths.add("/manager/login");
        ignorePaths.add("/swagger-ui.html");

        //wx
        ignorePaths.add("/wx/portal/**");
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //先拦截认证，再拦截授权
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns(ignorePaths);
    }

    /*
     * 静态资源配置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    // 配置全局的CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 允许所有路径
                .allowedOriginPatterns("*")  // 允许所有来源
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 允许的请求方法
                .allowedHeaders("*")  // 允许所有头部
                .allowCredentials(true)  // 允许凭据
                .maxAge(3600);  // 预检请求的缓存时间
    }

}
