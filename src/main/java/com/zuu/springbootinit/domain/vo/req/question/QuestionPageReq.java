package com.zuu.springbootinit.domain.vo.req.question;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zuu.springbootinit.common.domain.vo.req.PageRequest;
import lombok.Data;

import java.util.List;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/20 10:54
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionPageReq extends PageRequest {
    /**
     * 题目id
     */
    private Long id;
    /**
     * 题目标题
     */
    private String title;

    /**
     * 题目描述
     */
    private String description;

    /**
     * 题目标签
     */
    private List<String> tagList;
    /**
     * 题目难度 0-简单 1-中等 2-困难
     */
    private Integer difficult;
}
