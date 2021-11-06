package com.birdRun543.code0.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.birdRun543.code0.dao.Table1Mapper;
import com.birdRun543.code0.model.Table1;
import com.birdRun543.code0.model.query.BasicQuery;
import com.birdRun543.code0.service.Table1Service;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 规格类型 服务实现类
 * </p>
 *
 * @author hanbing
 * @since 2021-11-06
 */
@Service
public class Table1ServiceImpl extends ServiceImpl<Table1Mapper, Table1> implements Table1Service {

    @Override
    public List<Table1> getList(BasicQuery query) {
        return list();
    }
}
