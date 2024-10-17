package com.zuu.springbootinit.user.domain.vo.req;

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
