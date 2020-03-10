package com.wfm.platform.jwt;

import java.io.Serializable;

/**
 * @author Weifengming
 * @description jwt权限响应类
 * @date 2020/3/8
 */
public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1250166508152483573L;
    private String token;
    private String username;
    private String realname;
    private String userId;

    public JwtAuthenticationResponse(String token, String username, String realname, String userId) {
        this.token = token;
        this.username = username;
        this.realname = realname;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
