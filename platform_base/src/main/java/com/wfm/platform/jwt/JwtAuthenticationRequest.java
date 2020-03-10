package com.wfm.platform.jwt;

import java.io.Serializable;

/**
 * @author Weifengming
 * @description jwt权限请求类
 * @date 2020/3/8
 */
public class JwtAuthenticationRequest implements Serializable
{
    private static final long serialVersionUID = -8445943548965154778L;
    private String loginname;
    private String password;

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
