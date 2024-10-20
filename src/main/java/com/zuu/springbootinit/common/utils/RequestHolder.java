package com.zuu.springbootinit.common.utils;

/**
 * @Author zuu
 * @Description 拦截器检验用户信息后收集的用户信息
 * @Date 2024/10/17 13:38
 */
public class RequestHolder {
    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void set(Long uid){
        threadLocal.set(uid);
    }

    public static Long get(){
        return threadLocal.get();
    }

    public static void remove(){
        threadLocal.remove();
    }
}