package com.zuu.springbootinit.domain.vo.req.user;

import lombok.Data;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/17 18:46
 */
@Data
public class UserRegisterReq {
    private String username;
    private String userPassword;
    private String checkPassword;
}
