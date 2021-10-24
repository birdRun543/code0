package com.example.code0.common;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller基类
 * @author hanbing
 */
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

}
