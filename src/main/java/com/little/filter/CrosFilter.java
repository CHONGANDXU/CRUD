package com.little.filter;

import org.jetbrains.annotations.NotNull;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "crosFilter", urlPatterns = {"/*"})
public class CrosFilter implements Filter {
    // 重写其中的doFilter方法
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, @NotNull FilterChain chain) throws IOException, ServletException {
        // 设置响应头允许跨域请求
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        // 继续执行下一个过滤器
        chain.doFilter(req, response);
    }
}
