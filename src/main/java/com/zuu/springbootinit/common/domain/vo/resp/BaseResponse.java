package com.zuu.springbootinit.common.domain.vo.resp;

import com.zuu.springbootinit.common.domain.enums.ErrorEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author zuu
 * @Description 项目基础返回
 * @Date 2024/10/17 13:08
 */
@Data
public class BaseResponse<T> implements Serializable {
    private Integer code;
    private T data;
    private String message;
    private String description;

    public BaseResponse(Integer code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }
    public BaseResponse(Integer code, T data, String message) {
        this(code,data,message,"");
    }
    public BaseResponse(Integer code,T data){
        this(code,data,"","");
    }
    public BaseResponse(ErrorEnum errorEnum){
        this(errorEnum.getErrorCode(),null, errorEnum.getErrorMessage(), errorEnum.getDescription());
    }
    public BaseResponse(ErrorEnum errorEnum,String description){
        this(errorEnum.getErrorCode(),null, errorEnum.getErrorMessage(), description);
    }


}
