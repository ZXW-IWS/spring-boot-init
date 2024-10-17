package com.zuu.springbootinit.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zuu.springbootinit.common.constant.RedisConstant;
import com.zuu.springbootinit.common.domain.enums.ErrorEnum;
import com.zuu.springbootinit.common.exeption.BusinessException;
import com.zuu.springbootinit.user.domain.dto.UserDto;
import com.zuu.springbootinit.user.domain.enums.UserGenderEnum;
import com.zuu.springbootinit.user.domain.po.User;
import com.zuu.springbootinit.user.mapper.UserMapper;
import com.zuu.springbootinit.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/17 17:42
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    public static final int MIN_USERNAME_LEN = 4;
    public static final int MIN_PWD_LEN = 8;
    public static final String PWD_SALT = "shit";
    public static final String DEFAULT_AVATAR_URL = "http://inews.gtimg.com/newsapp_match/0/15103659087/0";

    private final StringRedisTemplate stringRedisTemplate;
    /**
     * 用户注册功能
     *
     * @param username   账号
     * @param userPassword  密码
     * @param checkPassword 校验密码
     * @return 新注册账户的id
     */
    @Override
    public Long userRegister(String username, String userPassword, String checkPassword) {
        //1.账户，密码不能为空
        if(StrUtil.hasBlank(username,userPassword,checkPassword))
            throw new BusinessException(ErrorEnum.PARAM_ERROR,"账号、密码为空");
        //2.账户不小于四位
        if(username.length() < MIN_USERNAME_LEN)
            throw new BusinessException(ErrorEnum.PARAM_ERROR,"账号小于4个字符");
        //3.密码不小于八位
        if(userPassword.length() < MIN_PWD_LEN || checkPassword.length() < MIN_PWD_LEN)
            throw new BusinessException(ErrorEnum.PARAM_ERROR,"密码小于8位");
        //4.账号格式校验（不含有特殊字符）
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(username);
        if(m.find())
            throw new BusinessException(ErrorEnum.PARAM_ERROR,"账号中含有特殊字符");
        //5.密码与校验密码是否相同校验
        if(!userPassword.equals(checkPassword))
            throw new BusinessException(ErrorEnum.PARAM_ERROR,"两次输入的密码不一致");
        //6.账号是否重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        long count = this.count(queryWrapper);
        if(count > 0)
            throw new BusinessException(ErrorEnum.PARAM_ERROR,"当前账号已被注册");
        //7.密码加密
        String encryptPassword = DigestUtil.md5Hex(PWD_SALT + userPassword);
        //9.插入数据库
        User user = new User();
        user.setUsername(username);
        user.setUserPassword(encryptPassword);
        user.setAvatarUrl(DEFAULT_AVATAR_URL);
        user.setGender(UserGenderEnum.SECRET.getGender());
        this.save(user);
        return user.getId();
    }

    /**
     * 用户登录功能
     *
     * @param username  用户账号
     * @param userPassword 用户密码
     * @return 用户的信息
     */
    @Override
    public String userLogin(String username, String userPassword) {
        //1. 先进行数据的合法性检验（是否为空、长度是否满足要求）
        if(StrUtil.hasBlank(username,userPassword))
            throw new BusinessException(ErrorEnum.PARAM_ERROR,"账号、密码为空");
        //账户不小于四位
        if(username.length() < MIN_USERNAME_LEN)
            throw new BusinessException(ErrorEnum.PARAM_ERROR,"账号小于4个字符");
        //密码不小于八位
        if(userPassword.length() < MIN_PWD_LEN)
            throw new BusinessException(ErrorEnum.PARAM_ERROR,"密码小于8位");
        //账号格式校验（不含有特殊字符）
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(username);
        if(m.find())
            throw new BusinessException(ErrorEnum.PARAM_ERROR,"账号中含有特殊字符");

        String encryptPassword = DigestUtil.md5Hex(PWD_SALT + userPassword);
        QueryWrapper<User> confirmQueryWrapper = new QueryWrapper<>();
        confirmQueryWrapper.eq("username",username);
        confirmQueryWrapper.eq("user_password",encryptPassword);
        User user = this.getOne(confirmQueryWrapper);
        //密码错误
        if(user == null)
            throw new BusinessException(ErrorEnum.NULL_ERROR,"账号不存在或密码错误");

        //3. 生成token并将其存储到redis中
        String token = IdUtil.simpleUUID();

        stringRedisTemplate.opsForValue().set(RedisConstant.TOKEN_PREFIX + token
                , user.getId().toString(),
                RedisConstant.TOKEN_TTL_MINUTES,
                TimeUnit.MINUTES);

        //5. 返回token信息
        return token;
    }

    /**
     * 根据token获取用户id
     *
     * @param token token信息
     * @return 根据token查询到的用户id
     */
    @Override
    public Long getIdByToken(String token) {
        String idStr = stringRedisTemplate.opsForValue().get(RedisConstant.TOKEN_PREFIX + token);
        if(StrUtil.isBlank(idStr))
            throw new BusinessException(ErrorEnum.NO_LOGIN);

        return Long.parseLong(idStr);
    }
}
