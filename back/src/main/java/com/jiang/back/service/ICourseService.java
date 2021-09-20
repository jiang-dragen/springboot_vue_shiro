package com.jiang.back.service;

import com.jiang.back.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.jws.Oneway;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiang
 * @since 2021-09-15
 */
public interface ICourseService extends IService<Course> {

    Map<String , Object> getThisCourses(Integer userId , Integer pageNum , Integer pageSize);

    boolean isContradict(Integer userId , Integer courseId);

    int chooseCourse(Integer userId , Integer courseId);

}
