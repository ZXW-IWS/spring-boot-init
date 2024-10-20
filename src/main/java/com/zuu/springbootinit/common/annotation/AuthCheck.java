package com.zuu.springbootinit.common.annotation;

import java.lang.annotation.*;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/19 19:20
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {
    /**
     * 需要拥有的最低权限
     */
     int minRole();
}
