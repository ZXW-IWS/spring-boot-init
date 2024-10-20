package com.zuu.springbootinit.domain.vo.req.question;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/19 17:01
 */
@Data
public class QuestionAddReq implements Serializable {
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

    /**
     * 题目答案
     */
    private String answer;

    /**
     * 判题配置（JSON),时间空间...
     */
    private JudgeConfig judgeConfig;

    /**
     * 判题用例(JSON)
     */
    private List<JudgeCase> judgeCase;

    @Serial
    private static final long serialVersionUID = 1L;
}
