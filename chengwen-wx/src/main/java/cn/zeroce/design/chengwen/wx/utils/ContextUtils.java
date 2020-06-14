package cn.zeroce.design.chengwen.wx.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 上下文工具
 *
 * @author zeroce
 */
public class ContextUtils {
    private ContextUtils() {}

    /**
     * 获取 request
     * @return
     */
    public static HttpServletRequest getRequest() {
        final ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes == null ? null : attributes.getRequest();
    }
}
