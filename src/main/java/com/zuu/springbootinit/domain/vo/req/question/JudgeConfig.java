package com.zuu.springbootinit.domain.vo.req.question;

import lombok.Data;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/19 17:07
 */
@Data
public class JudgeConfig {
    /**
     * 时间限制(ms)
     */
    private Long timeLimit;
    /**
     * 内存限制(KB)
     */
    private Long memoryLimit;
}
