package com.jiang.back.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiang.back.entity.Course;
import com.jiang.back.entity.School;
import com.jiang.back.entity.User;
import com.jiang.back.mapper.ChooseCourseMapper;
import com.jiang.back.mapper.CourseMapper;
import com.jiang.back.mapper.SchoolMapper;
import com.jiang.back.mapper.UserMapper;
import com.jiang.back.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiang
 * @since 2021-09-15
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ChooseCourseMapper chooseCourseMapper;

    @Override
    public Map<String, Object> getThisCourses(Integer userId, Integer pageNum, Integer pageSize) {
        Integer first = pageNum == null ? 1 : pageNum;
        Integer second = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(first, second);
        List<Course> courses = courseMapper.selectThisCourses(userId);
        PageInfo pageInfo = new PageInfo(courses);
        Map<String, Object> map = new HashMap<>();
        map.put("total" , pageInfo.getTotal());
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Course course : courses) {
            Map<String, Object> map1 = new HashMap<>();
            School school = schoolMapper.selectById(course.getSchoolId());
            User teacher = userMapper.selectById(course.getTeacherId());
            map1.put("course" , course);
            map1.put("school" , school);
            map1.put("teacher" , teacher);
            mapList.add(map1);
        }
        map.put("list" , mapList);
        return map;
    }

    @Override
    public boolean isContradict(Integer userId, Integer courseId) {
        List<Course> courses = courseMapper.getChooseResult(userId);
        Course course = courseMapper.selectById(courseId);
        for (Course course1 : courses) {
            if (course.getCourseTime().equals(course1.getCourseTime())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int chooseCourse(Integer userId, Integer courseId) {
        return chooseCourseMapper.insertChooseCourse(userId , courseId);
    }

}
