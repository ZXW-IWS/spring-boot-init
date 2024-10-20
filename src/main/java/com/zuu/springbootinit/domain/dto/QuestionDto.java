package com.zuu.springbootinit.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zuu.springbootinit.domain.vo.req.question.JudgeConfig;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/19 22:47
 */
@Data
public class QuestionDto implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
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
     * 题目提交数
     */
    private Integer submitCount;

    /**
     * 标签列表
     */
    private List<String> tagList;
    /**
     * 题目通过数
     */
    private Integer acceptCount;

    /**
     * 题目难度 0-简单 1-中等 2-困难
     */
    private Integer difficult;


    /**
     * 判题配置（JSON),时间空间...
     */
    private JudgeConfig judgeConfig;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人信息
     */
    private UserDto createUser;

    private static final long serialVersionUID = 1L;
}
