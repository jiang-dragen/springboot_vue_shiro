package com.jiang.back.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiang.back.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jiang
 * @since 2021-09-15
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
