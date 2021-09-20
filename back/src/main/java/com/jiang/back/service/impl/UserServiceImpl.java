package com.jiang.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiang.back.entity.Permission;
import com.jiang.back.entity.User;
import com.jiang.back.mapper.PermissionMapper;
import com.jiang.back.mapper.UserMapper;
import com.jiang.back.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public User getUserByUsername(String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public List<Map<String, Object>> getMenuList(String username) {
        List<Map<String , Object>> list = new ArrayList<>();
        List<Permission> permissions = permissionMapper.selectFatherPermissionsByUsername(username);
        for (Permission permission : permissions) {
            Map<String , Object> map = new HashMap<>();
            map.put("menu" , permission);
            List<Permission> permissions1 = permissionMapper.selectSubPermissionsByFatherId(permission.getPermissionId());
            map.put("subMenu" , permissions1);
            list.add(map);
        }
        return list;
    }
}
