package com.test001.demo.util;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Result {
    private int code;
    private String message;
    private Map<String,Object> data;


    public Result(int code, String message, Map<String, Object> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
