package com.zuu.springbootinit.common.utils;

import com.zuu.springbootinit.common.domain.dto.RequestInfo;
/**
 * @Author zuu
 * @Description 拦截器检验用户信息后收集的用户信息
 * @Date 2024/10/17 13:38
 */
public class RequestHolder {
    private static final ThreadLocal<RequestInfo> threadLocal = new ThreadLocal<>();

    public static void set(RequestInfo requestInfo){
        threadLocal.set(requestInfo);
    }

    public static RequestInfo get(){
        return threadLocal.get();
    }

    public static void remove(){
        threadLocal.remove();
    }
}