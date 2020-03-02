package com.wfm.platform.annotation;

import java.lang.annotation.*;


/**
 * @author Weifengming
 * @description 根据缓存查询
 * @date 2020/2/23
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisCache {
}
