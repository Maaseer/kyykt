package RestResult;

import  lombok.Getter;
import  com.alibaba.fastjson.JSON;
@Getter

public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result setCode(int code)
    {
        this.code = code;
        return this;
    }
    public Result setMessage(String message)
    {
        this.message = message;
        return this;
    }
    public Result setData(T data)
    {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
