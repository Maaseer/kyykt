package com.zstu.ky.RestResult;

public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE="SUCCESS";
    public static Result<Object> genSuccessResult(){
        return new Result<>().setCode(ResultCode.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE);
    }
    public static <T> Result<T> genSuccessResult(T data){
        return new Result<T>()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }
    public static Result<Object> genFailResult(String message){
        return new Result<>()
                .setCode(ResultCode.BAD_REQUEST)
                .setMessage(message);
    }
}
