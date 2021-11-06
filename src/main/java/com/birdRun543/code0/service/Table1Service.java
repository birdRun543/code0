package com.birdRun543.code0.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.birdRun543.code0.model.Table1;
import com.birdRun543.code0.model.query.BasicQuery;

import java.util.List;

/**
 * <p>
 * 规格类型 服务类
 * </p>
 *
 * @author hanbing
 * @since 2021-11-06
 */
public interface Table1Service extends IService<Table1> {

    List<Table1> getList(BasicQuery query);
}
