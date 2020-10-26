package com.mz.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.core.annotation.Order;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
@WebFilter(filterName = "xssFilter", urlPatterns = "/*", asyncSupported = true)
@Slf4j
public class XssSqlFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("xssFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //设置cookie
        Cookie[] cooKies = ((ShiroHttpServletRequest) servletRequest).getCookies();
        if(cooKies!=null&&cooKies.length>0){
            for (Cookie cookie: cooKies) {
                if("JSESSIONID".equalsIgnoreCase(cookie.getName())){
                    cookie.setPath("/");
                }
                cookie.setHttpOnly(true);
                cookie.setSecure(true);
                response.addCookie(cookie);
            }
        }else{
            Subject subject = SecurityUtils.getSubject();
            //注意同源策略
            String sessionId =  (String)subject.getSession().getId();
            response.setHeader("Set-Cookie", "JSESSIONID="+sessionId+";Path=/;Secure;HttpOnly"); //设置Secure;
        }
        /*
         * DENY ：该页面不允许在 frame 中展示，即便是在相同域名的页面中嵌套也不允许。
         * SAMEORIGIN ：该页面可在相同域名页面的 frame 中展示。
         * ALLOW-FROM uri：该页面可在指定来源的 frame 中展示。
         */
        response.setHeader("x-frame-options", "SAMEORIGIN"); //设置x-frame-options
        XssSqlHttpServletRequestWrapper xssSqlHttpServletRequestWrapper = new XssSqlHttpServletRequestWrapper((HttpServletRequest) servletRequest);
        filterChain.doFilter(xssSqlHttpServletRequestWrapper, response);
    }

    @Override
    public void destroy() {
        log.debug("xssFilter destroy");
    }
}
