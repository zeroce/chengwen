package cn.zeroce.design.chengwen.wx.core.response;

import cn.zeroce.design.chengwen.wx.utils.ResultUtil;
import org.springframework.http.HttpStatus;

/**
 * 响应结果生成工具
 *
 * @author: zeroce
 * @date 20.3.18 23:47
 */
public class ResultGenerator {
    /**
     * 成功响应结果
     *
     * @param data 内容
     * @return 响应结果
     */
    public static <T> ResultUtil<T> genOkResult(final T data) {
        ResultUtil<T> result = new ResultUtil<>();
        result.setCode(HttpStatus.OK.value());
        result.setData(data);
        return result;
    }

    /**
     * 成功响应结果
     *
     * @return 响应结果
     */
    public static <T> ResultUtil<T> genOkResult() {
        return genOkResult(null);
    }

    /**
     * 失败响应结果
     *
     * @param code 状态码
     * @param message 消息
     * @return 响应结果
     */
    public static <T> ResultUtil<T> genFailedResult(final int code, final String message) {
        ResultUtil<T> result = new ResultUtil<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 失败响应结果
     *
     * @param resultCode 状态码枚举
     * @param message 消息
     * @return 响应结果
     */
    public static <T> ResultUtil<T> genFailedResult(final ResultCode resultCode, final String message) {
        return genFailedResult(resultCode.getCode(), message);
    }

    /**
     * 失败响应结果
     *
     * @param resultCode 状态码枚举
     * @return 响应结果
     */
    public static <T> ResultUtil<T> genFailedResult(final ResultCode resultCode) {
        return genFailedResult(resultCode.getCode(), resultCode.getMessage());
    }

    /**
     * 失败响应结果
     *
     * @param message 消息
     * @return 响应结果
     */
    public static <T> ResultUtil<T> genFailedResult(final String message) {
        return genFailedResult(ResultCode.SUCCEED_REQUEST_FAILED_RESULT.getCode(), message);
    }

    /**
     * 失败响应结果
     *
     * @return 响应结果
     */
    public static <T> ResultUtil<T> genFailedResult() {
        return genFailedResult(ResultCode.SUCCEED_REQUEST_FAILED_RESULT);
    }
}
