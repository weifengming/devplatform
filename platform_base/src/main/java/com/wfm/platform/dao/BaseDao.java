package com.wfm.platform.dao;

import com.wfm.platform.exception.SystemException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Weifengming
 * @description 基础数据层类
 * @date 2020/2/17
 */
public abstract interface BaseDao<PK extends Serializable, T> {

    public abstract void create(T paramT);

    public abstract void update(T paramT);

    public abstract int remove(PK paramPK);

    public abstract T get(PK paramPK);

    public abstract List<T> query(Map<String, Object> paramMap)
            throws SystemException;
}
