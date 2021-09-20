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
public class Course{

    private static final long serialVersionUID = 1L;

    @TableId(value = "course_id", type = IdType.AUTO)
    private Integer courseId;

    private String courseCode;

    private String courseName;

    private String courseType;

    private Integer schoolId;

    private Integer teacherId;

    private Integer courseStatus;

    private Integer courseTermId;

    private String courseTerm;

    private String courseTime;

    private String coursePlace;

    private Integer courseClass;

    private Integer courseCredit;

    private Double usualWeight;

    private Double endWeight;


}
