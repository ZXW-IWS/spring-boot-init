package com.zuu.springbootinit.domain.vo.req.question_submit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zuu.springbootinit.common.domain.vo.req.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/20 12:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionSubmitPageReq extends PageRequest {

    /**
     * 编程语言
     */
    private String language;

    /**
     * 提交状态
     */
    private Integer status;

    /**
     * 题目 id
     */
    private Long questionId;


    /**
     * 用户 id
     */
    private Long userId;
}
