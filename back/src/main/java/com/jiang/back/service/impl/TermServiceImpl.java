package com.jiang.back.service.impl;

import com.jiang.back.entity.Term;
import com.jiang.back.mapper.TermMapper;
import com.jiang.back.service.ITermService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiang
 * @since 2021-09-15
 */
@Service
public class TermServiceImpl extends ServiceImpl<TermMapper, Term> implements ITermService {

}
