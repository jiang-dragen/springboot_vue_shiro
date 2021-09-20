package com.jiang.back.service;

import com.jiang.back.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiang.back.entity.UserRole;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiang
 * @since 2021-09-15
 */
public interface IRoleService extends IService<Role> {

    Set<String> getRoleByUsername(String username);
}
