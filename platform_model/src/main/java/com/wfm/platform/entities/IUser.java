package com.wfm.platform.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Weifengming
 * @description 用户抽象接口
 * @date 2020/3/10
 */
public abstract interface IUser extends UserDetails {

    void setUserId(String userId);

    String getUserId();

    void setUsername(String username);

    @Override
    String getUsername();

    void setRealname(String realname);

    void getRealname();

    @Override
    Collection<? extends GrantedAuthority> getAuthorities();

    void setAuthorities(Collection<? extends GrantedAuthority> authorities);

    @Override
    String getPassword();

    @Override
    boolean isEnabled();
}
