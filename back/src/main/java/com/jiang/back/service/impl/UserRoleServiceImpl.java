package com.jiang.back.service.impl;

import com.jiang.back.entity.UserRole;
import com.jiang.back.mapper.UserRoleMapper;
import com.jiang.back.service.IUserRoleService;
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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
