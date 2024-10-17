package com.zuu.springbootinit.common.domain.vo.resp;

import com.zuu.springbootinit.common.domain.enums.ErrorEnum;

/**
 * @Author zuu
 * @Description 返回结果类
 * @Date 2024/10/17 13:09
 */
public class ApiResult {
    /**
     * 返回成功信息
     */
    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<T>(0,data,"ok");
    }
    /**
     * 返回错误信息
     */
    public static <T> BaseResponse<T> error(ErrorEnum errorEnum){
        return new BaseResponse<T>(errorEnum);
    }
    /**
     * 返回错误信息
     */
    public static <T> BaseResponse<T> error(ErrorEnum errorEnum,String description){
        return new BaseResponse<T>(errorEnum,description);
    }

    public static <T> BaseResponse<T> error(Integer errorCode,String message,String description){
        return new BaseResponse<T>(errorCode,null,message,description);
    }
}
