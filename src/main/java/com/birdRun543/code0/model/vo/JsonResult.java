package com.birdRun543.code0.model.vo;

import lombok.Data;

/**
 * @author hanbing
 * @date 2021/11/6
 */
@Data
public class JsonResult {
    private Boolean flag;

    private Integer code;

    private String message;

    private Object data;


    public JsonResult() {
    }

    public JsonResult(Boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public JsonResult(Boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

}

