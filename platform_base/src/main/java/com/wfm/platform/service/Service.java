package com.wfm.platform.service;

import com.wfm.platform.exception.SystemException;
import com.wfm.platform.query.PageBean;
import com.wfm.platform.query.PageList;
import com.wfm.platform.query.QueryFilter;

import java.io.Serializable;
import java.util.List;

/**
 * @author Weifengming
 * @description 基础服务接口
 * @date 2020/2/21
 */
public abstract interface Service<PK extends Serializable, T> {
    public abstract void create(T paramT);

    public abstract void update(T paramT);

    public abstract void remove(PK paramPK);

    public abstract T get(PK paramPK);

    public abstract List<T> getByIds(List<PK> paramList, String paramString)
            throws SystemException;

    public abstract void removeByIds(PK[] paramArrayOfPK);

    public abstract PageList<T> query(QueryFilter paramQueryFilter)
            throws SystemException;

    public abstract T getUnique(String paramString, Object paramObject)
            throws SystemException;

    public abstract List<T> getAll();

    public abstract PageList<T> getAllByPage(PageBean paramPageBean);

    public abstract void startPageBean(PageBean paramPageBean);
}
