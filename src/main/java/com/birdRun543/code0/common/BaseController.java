package com.birdRun543.code0.common;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Controlle
 * @author hanbing
 */
public class BaseController {
    // todo 2023-02-02

    @Autowired
    protected HttpServletRequest request;

}
