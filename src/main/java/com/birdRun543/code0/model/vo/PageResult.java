package com.birdRun543.code0.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author hanbing
 * @date 2021/11/6
 */
@Data
public class PageResult<T> {

    private int total;

    private List<T> rows;

    @SuppressWarnings("unused")
    public PageResult() {
    }

    public PageResult(List<T> rows) {
        this.total = rows.size();
        this.rows = rows;
    }
}


