package com.jiang.back.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import com.jiang.back.Response.Result;
import com.jiang.back.entity.User;
import com.jiang.back.service.IUserService;
import com.jiang.back.token.JwtToken;
import com.jiang.back.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @CrossOrigin // 解决前后端交互跨域问题
    @RequiresPermissions("course:choose")
    @GetMapping("/test")
    public String getTest(){
        return "你好";

    }

    @CrossOrigin // 解决前后端交互跨域问题
    @GetMapping("/test2")
    public String getTest2(){
        return "我好";
    }

    @CrossOrigin // 解决前后端交互跨域问题
    @ApiOperation("登录")
    @PostMapping("/login")
    public String testLogin(@RequestBody User user){
        if (user.getUsername() == null || user.getPassword() == null){
            return JSON.toJSONString(new Result().setCode(500).setMessage("帐号或密码错误"));
        }
        Subject subject = SecurityUtils.getSubject();
        String jwt = JwtUtil.createJWT(user.getUsername(), "back", "user", 1000 * 60 * 30);
        JwtToken jwtToken = new JwtToken(jwt , user.getPassword());
        try {
            subject.login(jwtToken);
        }catch (UnknownAccountException e){
            return JSON.toJSONString(new Result().setCode(401).setMessage("账号信息不存在"));
        }catch (IncorrectCredentialsException e){
            return JSON.toJSONString(new Result().setCode(401).setMessage("密码错误"));
        }
        User backUser = userService.getUserByUsername(user.getUsername());
        backUser.setPassword(null);//安全问题：返回给前端空密码
        backUser.setSalt(null);
        HashMap<String, Object> map= new HashMap<>();
        map.put("user" , backUser);
        map.put("token" , jwt);
        return JSON.toJSONString(new Result().setCode(200).setMessage("登录成功").setData(map));
    }

    @RequestMapping(path = "/getMenuList" , method = RequestMethod.GET)
    @ApiOperation("获取主界面侧边导航按钮")
    public String getMenuList(String username){
        if (StringUtil.isEmpty(username)){
            return JSON.toJSONString(new Result().setCode(401).setMessage("未登录"));
        }
        List<Map<String , Object>> list = userService.getMenuList(username);
        return JSON.toJSONString(new Result().setCode(200).setData(list));
    }
}
