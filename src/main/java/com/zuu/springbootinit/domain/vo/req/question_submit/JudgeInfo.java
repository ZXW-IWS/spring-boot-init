package com.zuu.springbootinit.domain.vo.req.question_submit;

import lombok.Data;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/20 12:42
 */
@Data
public class JudgeInfo {
    /**
     * 判题结果的信息
     * @see com.zuu.springbootinit.domain.enums.JudgeInfoMsgEnum
     */
    private String message;
    /**
     * 内存消耗
     */
    private Integer memory;
    /**
     * 时间消耗
     */
    private Integer time;
}
