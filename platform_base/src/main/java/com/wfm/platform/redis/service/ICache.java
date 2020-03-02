package com.wfm.platform.redis.service;

import java.io.Serializable;

/**
 * @author Weifengming
 * @description 缓存通用接口
 * @date 2020/2/23
 */
public abstract interface ICache<T extends Serializable> {
    public abstract void add(String paramString, T paramT, int paramInt);

    public abstract void add(String paramString, T paramT);

    public abstract void delByKey(String paramString);

    public abstract void delByStartKey(String paramString);

    public abstract void clearAll();

    public abstract T getByKey(String paramString);

    public abstract boolean containKey(String paramString);
}
