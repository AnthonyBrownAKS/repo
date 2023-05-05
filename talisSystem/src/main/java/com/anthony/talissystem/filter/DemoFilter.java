package com.anthony.talissystem.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;


import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init 初始化执行");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截到请求——放行前逻辑");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("拦截到请求——放行后操作");
    }


    @Override
    public void destroy() {
        System.out.println("资源清空");
    }
}
