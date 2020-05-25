package com.zstu.ky.RestResult;

import  lombok.Getter;
import  com.alibaba.fastjson.JSON;
@Getter

public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result<T> setCode(ResultCode code)
    {
        this.code = code.getCode();
        return this;
    }
    public Result<T> setMessage(String message)
    {
        this.message = message;
        return this;
    }
    public Result<T> setData(T data)
    {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
