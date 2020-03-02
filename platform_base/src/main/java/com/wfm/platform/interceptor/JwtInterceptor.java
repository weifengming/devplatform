package com.wfm.platform.interceptor;

import com.wfm.platform.util.JWTokenUtil;
import com.wfm.platform.util.StringHelper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Weifengming
 * @description JWT拦截器
 * @date 2020/2/16
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JWTokenUtil jwtoken;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("Authorization");
        if (StringHelper.isNotEmpty(header)) {
            if (header.startsWith("Bearer ")) {
                String token = header.substring(7);
                try {
                    Claims claims = jwtoken.parseJwt(token);
                    String username = claims.getSubject();
                    request.setAttribute("loginname",username);
                    request.setAttribute("token",token);
                } catch (Exception e) {
                    throw new RuntimeException("令牌不正确");
                }
            }
        }
        return true;
    }
}
