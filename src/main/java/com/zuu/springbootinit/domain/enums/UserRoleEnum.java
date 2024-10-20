package com.zuu.springbootinit.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/19 19:02
 */
@Getter
@AllArgsConstructor
public enum UserRoleEnum {
    BAN(-1,"被封号"),
    USER(0,"普通用户"),
    ADMIN(1,"管理员")
    ;
    public final Integer role;
    public final String desc;

    private final static Map<Integer,UserRoleEnum> cache;
    static {
        //Function.identity获得自己
        cache = Arrays.stream(UserRoleEnum.values()).collect(Collectors.toMap(UserRoleEnum::getRole, Function.identity()));
    }

    public static UserRoleEnum getEnumByRole(Integer role){
        return cache.get(role);
    }
}
