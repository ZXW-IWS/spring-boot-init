package com.zuu.springbootinit.common.exeption;

import com.zuu.springbootinit.common.domain.enums.ErrorEnum;
import lombok.Data;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/17 13:41
 */
@Data
public class BusinessException extends RuntimeException{
    private Integer code;
    private String message;
    private String description;

    public BusinessException(int code,String description,String message){
        this.code = code;
        this.description = description;
        this.message = message;
    }
    public BusinessException(ErrorEnum errorEnum){
        this(errorEnum.getErrorCode(), errorEnum.getDescription(), errorEnum.getErrorMessage());
    }

    public BusinessException(ErrorEnum errorEnum,String description){
        this(errorEnum.getErrorCode(),description,errorEnum.getErrorMessage());
    }

}
