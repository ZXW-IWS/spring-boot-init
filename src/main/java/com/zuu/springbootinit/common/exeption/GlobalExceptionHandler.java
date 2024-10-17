package com.zuu.springbootinit.common.exeption;

import com.zuu.springbootinit.common.domain.enums.ErrorEnum;
import com.zuu.springbootinit.common.domain.vo.resp.BaseResponse;
import com.zuu.springbootinit.common.domain.vo.resp.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/17 14:15
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 参数校验错误
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<?> methodArgumentNotValidException(MethodArgumentNotValidException e){
        StringBuffer errorMsgBuffer = new StringBuffer();
        e.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> errorMsgBuffer.append(fieldError.getField())
                        .append(fieldError.getDefaultMessage())
                        .append(","));
        String errorMsg = errorMsgBuffer.substring(0,errorMsgBuffer.length()-1);
        log.error("methodArgumentNotValidException occurred! reason is:[{}]",errorMsg);
        return ApiResult.error(ErrorEnum.PARAM_ERROR, errorMsg);
    }

    @ExceptionHandler(Throwable.class)
    public BaseResponse<?> throwable(Throwable e){
        log.error("System error occurred! reason is:[{}]",e.getMessage(),e);
        return ApiResult.error(ErrorEnum.SYSTEM_ERROR);
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessException(BusinessException e){
        log.info("Business exception occurred! reason is:[{}:{}]",e.getMessage(),e.getDescription());
        return ApiResult.error(e.getCode(),e.getMessage(),e.getDescription());
    }

}
