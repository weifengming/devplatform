package com.wfm.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author Weifengming
 * @description 后台服务启动类
 * @date 2020/2/3
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.wfm.platform.**.dao"})
public class ManageApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ManageApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }
}
