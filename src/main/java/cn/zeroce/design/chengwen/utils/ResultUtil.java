package cn.zeroce.design.chengwen.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultUtil<R> implements Serializable {
    private Integer code;
    private String message;
    private R data;
    private String token;

    public static ResultUtil ok() {
        ResultUtil result = new ResultUtil();
        result.setData(null);
        return result;
    }

    public static <R> ResultUtil ok(R r) {
        ResultUtil result = new ResultUtil();
        result.setData(r);
        return result;
    }

    public static <R> ResultUtil fail(R r) {
        ResultUtil result = new ResultUtil();
        result.setData(r);
        return result;
    }
}
