package cn.zeroce.design.chengwen.core.config;

import cn.zeroce.design.chengwen.filter.AuthenticationFilter;
import cn.zeroce.design.chengwen.filter.MyAuthenticationEntryPoint;
import cn.zeroce.design.chengwen.service.impl.AccountDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Resource
    private AuthenticationFilter authenticationFilter;

    /**
     *
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
    }
     **/

    @Bean
    @Override
    protected AccountDetailsServiceImpl userDetailsService() {
        return new AccountDetailsServiceImpl();
    }

    /** 使用随机加盐哈希算法对密码进行加密 */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // 自定义获取账户信息
                .userDetailsService(this.userDetailsService())
                // 设置密码加密
                .passwordEncoder(this.passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 禁用页面缓存
                .headers().cacheControl().and().and()
                // 关闭 cors 验证
                .cors().disable()
                // 关闭 csrf 验证
                .csrf().disable()
                // 无状态 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 异常处理
                .exceptionHandling()
                // 因为 RESTFul 没有登录界面所以只能显示未登录
                .authenticationEntryPoint(this.myAuthenticationEntryPoint).and()
                // 身份过滤器
                .addFilterBefore(this.authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                // 对所有的请求都做权限校验
                .authorizeRequests()
                // 允许匿名 DELETE 请求
                .antMatchers(HttpMethod.DELETE, "/upload/image/**", "/upload/video/**").permitAll()
                // 允许匿名 POST 请求
                .antMatchers(HttpMethod.POST, "/sys/account", "/sys/account/login/token", "/wx/account/token").permitAll()
                // 允许更新 token 请求
                .antMatchers(HttpMethod.PUT, "/wx/token").permitAll()
                // 除上面外的所有请求，全部需要鉴权认证
                .anyRequest().authenticated();

    }
}
