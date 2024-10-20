package com.zuu.springbootinit.domain.dto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/17 17:37
 */
@Data
public class UserDto {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 用户性别
     */
    private String gender;

    /**
     * 用户头像的url地址
     */
    private String avatarUrl;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户个人简介
     */
    private String userProfile;

    /**
     * 用户状态 0-正常
     */
    private Integer userStatus;

    /**
     * 用户权限 0-普通用户 1-管理员
     */
    private Integer userRole;
}
