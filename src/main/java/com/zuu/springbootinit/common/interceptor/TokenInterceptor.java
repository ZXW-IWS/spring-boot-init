package com.zuu.springbootinit.common.interceptor;

import com.zuu.springbootinit.common.domain.enums.ErrorEnum;
import com.zuu.springbootinit.common.exeption.BusinessException;
import com.zuu.springbootinit.common.utils.RequestHolder;
import com.zuu.springbootinit.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/17 17:18
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String ID_KEY = "id";

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //拦截器取到请求先进行判断，如果是OPTIONS请求，则放行
        if ("OPTIONS".equals(request.getMethod().toUpperCase())) {
            return true;
        }

        boolean isPublicUri = isPublicUri(request);
        String token = getToken(request);
        if (isPublicUri) {
            return true;
        }
        if (Objects.nonNull(token)) {
            Long uid = userService.getIdByToken(token);
            if (Objects.isNull(uid)) {
                //返回未登录
                throw new BusinessException(ErrorEnum.NO_LOGIN);
            }
            //用户已登录
            RequestHolder.set(uid);
        } else {
            //返回未登录
            throw new BusinessException(ErrorEnum.NO_LOGIN);
        }

        return true;
    }

    private boolean isPublicUri(HttpServletRequest request) {
        //用户未登录，判断是否访问公共接口
        //公共接口判断规则：请求路径第二个是public,如：/user/public/login
        String requestURI = request.getRequestURI();
        String[] split = requestURI.split("/");
        //uri的开头有一个"/"，因此split数组的0位置上有一个空字符串
        return split.length > 3 && "public".equals(split[3]);
    }

    private String getToken(HttpServletRequest request) {
        return request.getHeader(HEADER_AUTHORIZATION);
    }
}
