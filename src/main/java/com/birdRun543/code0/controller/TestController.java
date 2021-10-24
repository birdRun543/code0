package com.example.code0.controller;

import com.example.code0.common.BaseController;
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
public class TestController extends BaseController {


    @GetMapping("hello")
    @ResponseBody
    public String test() {

        return "hello world";
    }
}
