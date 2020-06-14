package cn.zeroce.design.chengwen.wx.core.response;

public enum ResultCode {
    LOGIN_SUCCESS(20000, "请求成功！"),
    LOGIN_FAIL(30001, "请求成功，但登录失败，密码有误"),

    LOGOUT_SUCCESS(20000, "请求成功，退出登录成功"),

    GET_USERINFO_SUCCESS(20000, "请求成功，获取userInfo成功"),
    GET_USERINFO_FAIL(30002, "请求成功，获取userInfo失败"),

    GET_POST_LIST_SUCCESS(20000, "请求成功，分页获取article list成功"),
    GET_POST_LIST_FAIL(30003, "请求成功，获取article list失败"),

    /** 成功 */
    SUCCESS(20000, "成功"),
    /** 操作失败 */
    FAIL(205, "操作失败"),
    /** 数据已存在 */
    DATA_IS_EXIST(208, "数据已存在"),
    /** 数据不存在 */
    DATA_IS_NOT_EXIST(209, "数据不存在"),
    /** 没有结果 */
    NOT_DATA(911, "没有结果"),
    /** 没有登录 */
    NOT_LOGIN(600, "没有登录"),
    /** 发生异常 */
    EXCEPTION(401, "发生异常"),
    /** 系统错误 */
    SYS_ERROR(402, "系统错误"),
    /** 参数错误 */
    PARAMS_ERROR(403, "参数错误 "),
    /** 不支持或已经废弃 */
    NOT_SUPPORTED(410, "不支持或已经废弃"),
    /** AuthCode错误 */
    INVALID_AUTHCODE(444, "无效的AuthCode"),
    /** 太频繁的调用 */
    TOO_FREQUENT(445, "太频繁的调用"),
    /** 未知的错误 */
    UNKNOWN_ERROR(499, "未知错误"),
    /** 未设置方法 */
    NOT_METHOD(4004, "未设置方法"),
    /** 权限不足 */
    NOT_PERMISSION(405, "权限不足"),


    SUCCEED_REQUEST_FAILED_RESULT(1000, "成功请求，但结果不是期望的成功结果"),
    FIND_FAILED(2000, "查询失败"),
    SAVE_FAILED(2001, "保存失败"),
    UPDATE_FAILED(2002, "更新失败"),
    DELETE_FAILED(2003, "删除失败"),
    DUPLICATE_NAME(2004, "账户名重复"),
    DATABASE_EXCEPTION(4001, "数据库异常"),
    UNAUTHORIZED_EXCEPTION(4002, "认证异常"),
    VIOLATION_EXCEPTION(4003, "验证异常"),

    ;

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
