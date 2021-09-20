package com.jiang.back.mapper;

import com.jiang.back.entity.ChooseCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jiang
 * @since 2021-09-15
 */
@Mapper
public interface ChooseCourseMapper extends BaseMapper<ChooseCourse> {

    int insertChooseCourse(Integer userId , Integer courseId);
}
