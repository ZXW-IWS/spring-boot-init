package com.zuu.springbootinit.domain.vo.req.question;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/19 18:42
 */
@Data
public class QuestionDeleteReq implements Serializable {
    private Long questionId;
}
