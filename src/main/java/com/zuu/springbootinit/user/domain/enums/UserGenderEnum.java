package com.zuu.springbootinit.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/17 18:10
 */
@AllArgsConstructor
@Getter
public enum UserGenderEnum {
    SECRET("保密"),
    MAN("男"),
    WOMAN("nv")
    ;
    private final String gender;
}
