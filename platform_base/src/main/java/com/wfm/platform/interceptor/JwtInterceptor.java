package com.wfm.platform.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.wfm.platform.entities.Result;
import com.wfm.platform.exception.StatusCode;
import com.wfm.platform.util.JWTokenUtil;
import com.wfm.platform.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author Weifengming
 * @description JWT拦截器
 * @date 2020/2/16
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JWTokenUtil jwtoken;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("Authorization");
        Result result = null;
        PrintWriter out = null;
        try {
            if (StringHelper.isNotEmpty(header)) {
                if (header.startsWith("Bearer ")) {
                    String token = header.substring(7);
                    String username = jwtoken.getUsernameFromToken(token);

                    boolean isValidate = jwtoken.validateToken(token, username);
                    if (isValidate) {
                        request.setAttribute("loginname", username);
                        request.setAttribute("token", token);
                        return true;
                    }
                }
            }
            result = new Result(false, StatusCode.ILLEGAL_TOKEN, "Illegal token");
            out = response.getWriter();
            out.write(JSONObject.toJSONString(result));
            return false;
        } catch (Exception e) {
            logger.error("the token is expired and not valid anymore", e);
            result = new Result(false, StatusCode.ILLEGAL_TOKEN, "Illegal token");
            out = response.getWriter();
            out.write(JSONObject.toJSONString(result));
        } finally {
            if (null != out) {
                out.close();
            }
        }
        return false;
    }
}
