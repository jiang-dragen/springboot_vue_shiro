package com.jiang.back;

import com.jiang.back.entity.Permission;
import com.jiang.back.mapper.CourseMapper;
import com.jiang.back.mapper.UserMapper;
import com.jiang.back.service.ICourseService;
import com.jiang.back.service.IPermissionService;
import com.jiang.back.service.IRoleService;
import com.jiang.back.service.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BackApplication.class)
class BackApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IPermissionService iPermissionService;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ICourseService iCourseService;

    @Test
    void contextLoads() {

    }

    @Test
    void testUserMapper(){
        System.out.println(userMapper.selectById(1));
        System.out.println(courseMapper.getChooseResult(1));
    }

    @Test
    void testService(){
        List<Map<String , Object>> list = iUserService.getMenuList("20201001");
        System.out.println(list.get(1).get("subMenu"));
        System.out.println(list.get(1).get("menu"));
        System.out.println(iCourseService.getThisCourses(1,1,1));
    }

}
