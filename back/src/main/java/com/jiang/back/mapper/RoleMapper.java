package com.jiang.back.mapper;

import com.jiang.back.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiang.back.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jiang
 * @since 2021-09-15
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    Set<String> selectRolesByUsername(String username);
}
