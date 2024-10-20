package com.zuu.springbootinit.domain.dto;

import com.zuu.springbootinit.domain.vo.req.question_submit.JudgeInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/20 12:26
 */
@Data
public class QuestionSubmitDto implements Serializable {
    /**
     * 主键id
     */
    private Long id;


    /**
     * 语言
     */
    private String language;

    /**
     * 代码
     */
    private String code;

    /**
     * 状态 0-待判题 1-判题中 2-成功 3-失败
     */
    private Integer status;

    /**
     * 判题信息JSON(时间、空间)
     */
    private JudgeInfo judgeInfo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 题目信息
     */
    private QuestionDto questionInfo;
    /**
     * 提交用户信息
     */
    private UserDto userInfo;
    private static final long serialVersionUID = 1L;
}
