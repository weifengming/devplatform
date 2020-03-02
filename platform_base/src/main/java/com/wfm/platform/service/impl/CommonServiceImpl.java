package com.wfm.platform.service.impl;

import com.github.pagehelper.PageHelper;
import com.wfm.platform.exception.SystemException;
import com.wfm.platform.query.PageBean;
import com.wfm.platform.query.PageList;
import com.wfm.platform.query.QueryFilter;
import com.wfm.platform.service.CommonService;
import com.wfm.platform.util.BeanUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Weifengming
 * @description 基础service实现
 * @date 2020/2/21
 */
public class CommonServiceImpl<T> implements CommonService<T> {
    @Resource
    protected SqlSessionTemplate sqlSessionTemplate;
    private static final String NAME_SPACE = "com.wfm.platform.sql.commom";

    private String getNameSpace(String sqlKey) {
        return "com.wfm.platform.common." + sqlKey;
    }

    public void execute(String sql) {
        Assert.notNull(sql, "sql can not be empty.");
        Map map = new HashMap();
        map.put("sql", sql);
        String key = getNameSpace("execute");
        this.sqlSessionTemplate.update(key, map);
    }

    public List<T> query(String sql) {
        Assert.notNull(sql, "sql can not be empty.");
        Map map = new HashMap();
        map.put("sql", sql);
        String key = getNameSpace("query");
        List selectList = this.sqlSessionTemplate.selectList(key, map);
        return selectList;
    }

    public PageList<T> query(String sql, PageBean pageBean) {
        Assert.notNull(sql, "sql can not be empty.");
        Assert.notNull(pageBean, "pageBean can not be empty.");
        Map map = new HashMap();
        map.put("sql", sql);
        String key = getNameSpace("query");
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, 2147483647, false);
        } else {
            PageHelper.startPage(pageBean.getPage().intValue(), pageBean.getPageSize().intValue(), pageBean.showTotal());
        }
        List selectList = this.sqlSessionTemplate.selectList(key, map, pageBean);
        return new PageList(selectList);
    }

    public PageList<T> query(String sql, QueryFilter queryFilter) throws SystemException {
        Assert.notNull(sql, "sql can not be empty.");
        Assert.notNull(queryFilter, "queryFilter can not be empty.");
        Map params = queryFilter.getParams();
        if (CollectionUtils.isEmpty(params)) {
            params = new HashMap();
        }
        params.put("sql", sql);
        PageBean pageBean = queryFilter.getPageBean();
        List selectList;
        if (pageBean == null) {
            PageHelper.startPage(1, 2147483647, false);
            selectList = this.sqlSessionTemplate.selectList(getNameSpace("query"), params);
        } else {
            PageHelper.startPage(pageBean.getPage().intValue(), pageBean.getPageSize().intValue(), pageBean.showTotal());
            selectList = this.sqlSessionTemplate.selectList(getNameSpace("query"), params, pageBean);
        }
        return new PageList(selectList);
    }
}
