package com.wfm.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Weifengming
 * @description 基础服务启动类
 * @date 2020/2/22
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.wfm.platform.**.dao"})
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }
}
