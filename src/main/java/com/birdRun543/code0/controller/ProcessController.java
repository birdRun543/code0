package com.birdRun543.code0.controller;

import com.birdRun543.code0.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hanbing
 *
 * @date 2021-10-24 12:32
 */
@Controller
@RequestMapping("test")
public class ProcessController extends BaseController {


    @GetMapping("hello")
    @ResponseBody
    public String test() {

        return "hello world";
    }
}