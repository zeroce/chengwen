package cn.zeroce.design.chengwen.filter;

import cn.zeroce.design.chengwen.core.response.ResultCode;
import cn.zeroce.design.chengwen.core.response.ResultGenerator;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * 认证入口点 因为 RESTFul 没有登录界面所以只显示未登录提示
 *
 * @author: zeroce
 * @date 20.3.18 23:13
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("Content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        response
                .getWriter()
                .println(ResultGenerator.genFailedResult(ResultCode.UNAUTHORIZED_EXCEPTION).toString());
        response.getWriter().close();
    }
}
