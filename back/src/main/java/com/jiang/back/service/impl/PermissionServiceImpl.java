package com.jiang.back.service.impl;

import com.jiang.back.entity.Permission;
import com.jiang.back.mapper.PermissionMapper;
import com.jiang.back.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Set<String> getPermissionByUsername(String username) {
        return permissionMapper.selectPermissionByUsername(username);
    }
}
