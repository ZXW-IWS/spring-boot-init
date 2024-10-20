package com.zuu.springbootinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zuu.springbootinit.domain.dto.UserDto;
import com.zuu.springbootinit.domain.po.User;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/17 17:33
 */
public interface UserService extends IService<User> {
    /**
     * 用户注册功能
     *
     * @param username   账号
     * @param userPassword  密码
     * @param checkPassword 校验密码
     * @return 新注册账户的id
     */
    Long userRegister(String username, String userPassword,
                      String checkPassword);

    /**
     * 用户登录功能
     *
     * @param username  用户账号
     * @param userPassword 用户密码
     * @return token
     */
    String userLogin(String username, String userPassword);

    /**
     * 根据token获取用户id
     *
     * @param token token信息
     * @return 根据token查询到的用户id
     */
    Long getIdByToken(String token);

    /**
     * 获取用户信息
     * @param uid
     * @return
     */
    UserDto getUserById(Long uid);

    /**
     * 判断用户是否是管理员
     * @param uid
     * @return
     */
    boolean isAdmin(Long uid);
}
