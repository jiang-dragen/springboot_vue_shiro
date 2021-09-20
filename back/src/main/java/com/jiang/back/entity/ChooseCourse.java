package com.jiang.back.entity;

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
public class ChooseCourse{

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer courseId;

    private Double usualGrade;

    private Double endGrade;

    private Double totalGrade;

    private Integer chooseStatus;

    private String isPass;


}
