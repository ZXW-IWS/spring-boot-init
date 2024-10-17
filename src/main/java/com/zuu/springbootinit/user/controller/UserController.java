package com.zuu.springbootinit.user.controller;

import com.zuu.springbootinit.common.domain.vo.resp.ApiResult;
import com.zuu.springbootinit.common.domain.vo.resp.BaseResponse;
import com.zuu.springbootinit.user.domain.vo.req.UserLoginReq;
import com.zuu.springbootinit.user.domain.vo.req.UserRegisterReq;
import com.zuu.springbootinit.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/17 18:36
 */
@RestController
@RequestMapping("/user")
@Tag(name = "用户相关接口")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/public/register")
    @Operation(summary = "用户注册接口")
    public BaseResponse<Long> register(@RequestBody UserRegisterReq userRegisterReq) {
        String username = userRegisterReq.getUsername();
        String userPassword = userRegisterReq.getUserPassword();
        String checkPassword = userRegisterReq.getCheckPassword();
        Long uid = userService.userRegister(username, userPassword, checkPassword);
        return ApiResult.success(uid);
    }

    @PostMapping("/public/login")
    @Operation(summary = "用户登录接口")
    public BaseResponse<String> login(@RequestBody UserLoginReq userLoginReq) {
        String username = userLoginReq.getUsername();
        String userPassword = userLoginReq.getUserPassword();
        String token = userService.userLogin(username, userPassword);
        return ApiResult.success(token);
    }
}
