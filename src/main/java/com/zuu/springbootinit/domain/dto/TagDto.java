package com.zuu.springbootinit.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/19 22:49
 */
@Data
public class TagDto implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 标签名
     */
    private String name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
