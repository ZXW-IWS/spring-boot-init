package com.zuu.springbootinit.user.domain.po;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/17 17:29
 */

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
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
     * 用户密码
     */
    private String userPassword;

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
    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    private String userProfile;

    /**
     * 用户状态 0-正常
     */
    private Integer userStatus;

    /**
     * 用户权限 0-普通用户 1-管理员
     */
    private Integer userRole;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     *  0-未删除
     */
    @TableLogic
    private Integer isDelete;


}