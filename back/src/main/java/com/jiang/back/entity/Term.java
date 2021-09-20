package com.jiang.back.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiang
 * @since 2021-09-15
 */
@Data
@EqualsAndHashCode
public class Term {

    private static final long serialVersionUID = 1L;

    @TableId(value = "term_id", type = IdType.AUTO)
    private Integer termId;

    private String termName;

    private Integer termStatus;


}
