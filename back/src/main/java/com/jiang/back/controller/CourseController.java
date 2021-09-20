package com.jiang.back.controller;


import com.alibaba.fastjson.JSON;
import com.jiang.back.Response.Result;
import com.jiang.back.entity.ChooseCourse;
import com.jiang.back.service.ICourseService;
import com.jiang.back.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiang
 * @since 2021-09-15
 */
@RestController
@RequestMapping("/course")
public class CourseController{

    @Autowired
    private ICourseService iCourseService;

    @RequestMapping(value = "/showThis" , method = RequestMethod.GET)
    @ApiOperation("获取可选课程列表")
    @RequiresPermissions("course:choose")
    public String showThis(Integer userId , String pageNum , String pageSize){
        Integer first = StringUtil.changeString(pageNum);
        Integer second = StringUtil.changeString(pageSize);
        Map<String, Object> map = iCourseService.getThisCourses(userId, first, second);
        if ((long)map.get("total") == 0){
            return JSON.toJSONString(new Result().setCode(200).setMessage("无可选课程，或者不在选课时间"));
        }else {
            return JSON.toJSONString(new Result().setCode(200).setData(map));
        }
    }
    
    @RequestMapping(value = "/choose" , method = RequestMethod.POST)
    @ApiOperation("选课")
    public String chooseCourse(@RequestBody ChooseCourse chooseCourse){
        if (iCourseService.isContradict(chooseCourse.getUserId() , chooseCourse.getCourseId())){
            return JSON.toJSONString(new Result().setCode(500).setMessage("该课程与已选课程时间相冲突"));
        }
        int result = iCourseService.chooseCourse(chooseCourse.getUserId(), chooseCourse.getCourseId());
        if (result == 1){
            return JSON.toJSONString(new Result().setCode(200).setMessage("选课成功"));
        }else {
            return JSON.toJSONString(new Result().setCode(500).setMessage("服务器异常，请重试"));
        }
    }

}
