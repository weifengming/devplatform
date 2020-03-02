package com.wfm.platform.service;

import com.wfm.platform.exception.SystemException;
import com.wfm.platform.query.PageBean;
import com.wfm.platform.query.PageList;
import com.wfm.platform.query.QueryFilter;

import java.util.List;

/**
 * @author Weifengming
 * @description 基础service
 * @date 2020/2/21
 */
public abstract interface CommonService<T> {
    public abstract void execute(String paramString);

    public abstract List<T> query(String paramString);

    public abstract PageList<T> query(String paramString, PageBean paramPageBean);

    public abstract PageList<T> query(String paramString, QueryFilter paramQueryFilter)
            throws SystemException;
}
