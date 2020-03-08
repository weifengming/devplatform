package com.wfm.platform.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Weifengming
 * @description 跨域过滤器
 * @date 2020/3/7
 */
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@WebFilter(filterName = "crossFilter", urlPatterns = {"/*"})
public class CrossFilter implements Filter {

    public void destroy() {
        System.out.println("跨域访问过滤器销毁");
    }

    public void init(FilterConfig arg0) throws ServletException {
        System.out.println("跨域访问过滤器开启");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws
            IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod()))
            response.setStatus(200);
        else
            chain.doFilter(req, resp);
    }
}
