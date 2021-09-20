package com.jiang.back.service.impl;

import com.jiang.back.entity.Role;
import com.jiang.back.entity.UserRole;
import com.jiang.back.mapper.RoleMapper;
import com.jiang.back.mapper.UserRoleMapper;
import com.jiang.back.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiang
 * @since 2021-09-15
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Set<String> getRoleByUsername(String username) {
        return roleMapper.selectRolesByUsername(username);
    }

}
