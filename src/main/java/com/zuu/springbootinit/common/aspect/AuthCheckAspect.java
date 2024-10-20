package com.zuu.springbootinit.common.aspect;

import com.zuu.springbootinit.common.annotation.AuthCheck;
import com.zuu.springbootinit.common.domain.enums.ErrorEnum;
import com.zuu.springbootinit.common.exeption.BusinessException;
import com.zuu.springbootinit.common.utils.RequestHolder;
import com.zuu.springbootinit.domain.dto.UserDto;
import com.zuu.springbootinit.domain.enums.UserRoleEnum;
import com.zuu.springbootinit.service.UserService;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/19 19:25
 */
@Component
@Aspect
public class AuthCheckAspect {
    @Resource
    UserService userService;
    @Around("@annotation(com.zuu.springbootinit.common.annotation.AuthCheck)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        AuthCheck authCheck = method.getAnnotation(AuthCheck.class);
        Long uid = RequestHolder.get();
        UserDto user = userService.getUserById(uid);
        int minRole = authCheck.minRole();
        UserRoleEnum roleEnum = UserRoleEnum.getEnumByRole(minRole);
        if(Objects.isNull(roleEnum)){
            throw new BusinessException(ErrorEnum.NO_AUTH);
        }
        if(user.getUserRole() < roleEnum.role){
            throw new BusinessException(ErrorEnum.NO_AUTH);
        }
        return joinPoint.proceed();
    }
}
