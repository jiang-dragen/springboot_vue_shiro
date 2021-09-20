package com.jiang.back.mapper;

import com.jiang.back.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jiang
 * @since 2021-09-15
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    List<Course> selectThisCourses(Integer userId);

    List<Course> getChooseResult(Integer userId);
}
