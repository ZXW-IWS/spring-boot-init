package com.zuu.springbootinit.common.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author zuu
 * @Description error返回枚举
 * @Date 2024/10/17 13:14
 */
@Getter
@AllArgsConstructor
public enum ErrorEnum {
    SUCCESS(0,"ok",""),
    PARAM_ERROR(40001,"请求参数错误",""),
    NULL_ERROR(40002,"请求数据为空",""),
    NO_LOGIN(40003,"未登录",""),
    NO_AUTH(40004,"无权限",""),
    SYSTEM_ERROR(40010,"服务器内部错误","");
    ;
    private final Integer errorCode;
    private final String errorMessage;
    private final String description;
}
