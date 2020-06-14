package cn.zeroce.design.chengwen.utils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * Url工具
 *
 * @author: zeroce
 * @date 20.3.22 16:10
 */
public class UrlUtils {
    private UrlUtils() {}

    /**
     * 请求的相对路径 /account/list
     *
     * @param request request
     * @return 相对路径
     */
    public static String getMappingUrl(final ServletRequest request) {
        return getMappingUrl((HttpServletRequest) request);
    }
    public static String getMappingUrl(final HttpServletRequest request) {
        return request.getRequestURI().substring(request.getContextPath().length());
    }
}
