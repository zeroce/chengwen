package cn.zeroce.design.chengwen.core.exception;

import cn.zeroce.design.chengwen.core.response.ResultCode;

/**
 * Service 异常
 *
 * @author: zeroce
 * @date 20.3.20 15:36
 */
public class ServiceException extends RuntimeException {
    private ResultCode resultCode;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }

    public ServiceException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
