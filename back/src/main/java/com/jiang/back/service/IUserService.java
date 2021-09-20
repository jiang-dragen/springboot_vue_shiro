package com.jiang.back.service;

import com.jiang.back.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiang
 * @since 2021-09-15
 */
public interface IUserService extends IService<User> {

        User getUserByUsername(String username);

        List<Map<String , Object>> getMenuList(String username);

}
