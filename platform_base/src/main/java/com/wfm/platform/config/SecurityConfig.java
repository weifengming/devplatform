package com.wfm.platform.config;

import com.wfm.platform.security.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Weifengming
 * @description spring-security 配置类
 * @date 2020/2/15
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityUserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //authorizeRequests表示开始说明需要的权限
        //需要的权限分为两部分：1 拦截路径 2 访问路径需要的权限
        //antMatchers 表示拦截什么路径
        //permitAll 表示任何权限都可以访问
        //anyRequest 任何的请求 authenticated 认证后才可以访问
        //csrf().disable() 固定写法 表示csrf拦截失效

        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
        http.logout().logoutSuccessUrl("/");
    }
}
