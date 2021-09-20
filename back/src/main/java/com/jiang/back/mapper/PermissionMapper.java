package com.jiang.back.mapper;

import com.jiang.back.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
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
public interface PermissionMapper extends BaseMapper<Permission> {

    Set<String> selectPermissionByUsername(String username);

    List<Permission> selectFatherPermissionsByUsername(String username);

    List<Permission> selectSubPermissionsByFatherId(int fatherId);
}
