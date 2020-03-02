package com.wfm.platform.config;

import com.wfm.platform.util.IdWorker;
import com.wfm.platform.util.JWTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Weifengming
 * @description 管理端配置类
 * @date 2020/2/3
 */
@Configuration
public class ManageConfig {

    /**
     * 分布式ID生成器
     *
     * @return
     */
    @Bean
    public IdWorker getIdWorker() {
        return new IdWorker();
    }

    /**
     * BCrypt 密码加密
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * jwttoken生成器
     *
     * @return
     */
    @Bean
    public JWTokenUtil jwtToken() {
        return new JWTokenUtil();
    }
}
