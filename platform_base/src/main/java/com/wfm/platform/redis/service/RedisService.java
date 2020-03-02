package com.wfm.platform.redis.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Weifengming
 * @description redis 服务接口
 * @date 2020/2/23
 */
public interface RedisService<T extends Serializable> extends ICache<T> {

    public abstract void batchAdd(Map<String, Map<String, String>> paramMap);

    public abstract Map<String, String> batchGet(List<String> paramList, String paramString);

    public abstract void removeAllBatch();

    public abstract void hdel(String paramString1, String paramString2);
}
