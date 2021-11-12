package com.birdRun543.code0.controller;

import com.birdRun543.code0.common.BaseController;
import com.birdRun543.code0.model.Table1;
import com.birdRun543.code0.model.query.BasicQuery;
import com.birdRun543.code0.model.vo.PageResult;
import com.birdRun543.code0.service.Table1Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hanbing
 * @date 2021-10-24 12:32
 */
@Controller
@RequestMapping("test")
public class ProcessController extends BaseController {

    private final Table1Service table1Service;

    public ProcessController(Table1Service table1Service) {
        this.table1Service = table1Service;
    }

    @GetMapping("hello")
    @ResponseBody
    public String test() {

        return "hello world";
    }

    @PostMapping("table1/list")
    @ResponseBody
    public PageResult<Table1> getList(@RequestBody BasicQuery query) {
        List<Table1> table1List = table1Service.getList(query);
        return new PageResult<>(table1List);
    }
}