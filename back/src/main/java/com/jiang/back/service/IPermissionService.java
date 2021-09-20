package com.jiang.back.service;

import com.jiang.back.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiang
 * @since 2021-09-15
 */
public interface IPermissionService extends IService<Permission> {

    Set<String> getPermissionByUsername(String username);
}
