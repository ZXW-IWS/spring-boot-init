package com.zuu.springbootinit.domain.vo.req.user;

import lombok.Data;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/17 18:47
 */
@Data
public class UserLoginReq {
    private String username;
    private String userPassword;
}
