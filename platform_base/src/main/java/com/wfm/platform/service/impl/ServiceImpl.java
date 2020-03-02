package com.wfm.platform.service.impl;

import com.github.pagehelper.PageHelper;
import com.wfm.platform.annotation.RedisCache;
import com.wfm.platform.dao.BaseDao;
import com.wfm.platform.entities.BaseModel;
import com.wfm.platform.exception.SystemException;
import com.wfm.platform.query.*;
import com.wfm.platform.service.Service;
import com.wfm.platform.util.BeanUtils;
import com.wfm.platform.util.IdWorker;
import com.wfm.platform.util.StringHelper;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Weifengming
 * @description 基础服务实现
 * @date 2020/2/21
 */
@Transactional
public abstract class ServiceImpl<PK extends Serializable, T extends Serializable>
        implements Service<PK, T> {
    protected abstract BaseDao<PK, T> getDao();

    private Class<? super T> getTypeClass() {
        Class rawType = (Class) ((java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        return rawType;
    }

    private void handleQueryFilter(QueryFilter queryFilter)
            throws SystemException {
        if (BeanUtils.isEmpty(queryFilter)) {
            throw new SystemException("QueryFilter通用查询对象不能为空.");
        }
        Class clazz = queryFilter.getClazz();
        Class typeClass = getTypeClass();
        if (BeanUtils.isEmpty(clazz)) {
            queryFilter.setClazz(typeClass);
        } else if (!clazz.equals(typeClass))
            throw new SystemException(String.format("QueryFilter中的实体类:%s与Dao泛型中的实体类:%s不一致.", new Object[]{clazz, typeClass}));
    }

    public void create(T entity) {
        if ((entity instanceof BaseModel)) {
            BaseModel model = (BaseModel) entity;
            if (BeanUtils.isEmpty(model.getId())) {
                IdWorker idWorker = new IdWorker();
                model.setId(idWorker.nextId());
            }
            if (model.getCreateTime() == null) {
                model.setCreateTime(LocalDateTime.now());
            }
            if (StringHelper.isEmpty(model.getCreateBy())) {
                //model.setCreateBy(AuthenticationUtil.getCurrentUserId());
            }
            getDao().create(entity);
        }
    }

    public void update(T entity) {
        if ((entity instanceof BaseModel)) {
            BaseModel model = (BaseModel) entity;
            if (model.getUpdateTime() == null) {
                model.setUpdateTime(LocalDateTime.now());
            }
            if (StringHelper.isEmpty(model.getUpdateBy()))
                //model.setUpdateBy(AuthenticationUtil.getCurrentUserId());
                getDao().update(entity);
        }
    }

    public void remove(PK entityId) {
        getDao().remove(entityId);
    }

    public T get(PK entityId) {
        if (BeanUtils.isEmpty(entityId)) {
            return null;
        }
        return getDao().get(entityId);
    }

    public List<T> getByIds(List<PK> entityIds, String idColumn) throws SystemException {
        String idProp = StringHelper.isEmpty(idColumn) ? "id" : idColumn;

        QueryFilter queryFilter = QueryFilter.build()
                .withQuery(new QueryField(idProp, entityIds, QueryOP.IN));

        PageList query = query(queryFilter);
        return query.getRows();
    }

    public void removeByIds(PK[] ids) {
        if (ids != null)
            for (PK pk : ids)
                remove(pk);
    }

    @RedisCache
    public PageList<T> query(QueryFilter queryFilter)
            throws SystemException {
        handleQueryFilter(queryFilter);
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, 2147483647, false);
        } else {
            PageHelper.startPage(pageBean.getPage().intValue(), pageBean.getPageSize().intValue(), pageBean.showTotal());
        }
        List query = getDao().query(queryFilter.getParams());
        return new PageList(query);
    }

    public T getUnique(String column, Object value)
            throws SystemException {
        QueryFilter queryFilter = QueryFilter.build()
                .withDefaultPage()
                .withQuery(new QueryField(column, value));

        PageList query = query(queryFilter);
        long total = query.getTotal();
        if (total <= 0L) {
            return null;
        }
        if (total == 1L) {
            return (T) query.getRows().get(0);
        }

        throw new TooManyResultsException(String.format("符合查询条件的记录为:%s条，超过1条.", new Object[]{Long.valueOf(total)}));
    }

    public List<T> getAll() {
        QueryFilter queryFilter = QueryFilter.build();
        PageList query = query(queryFilter);
        return query.getRows();
    }

    public PageList<T> getAllByPage(PageBean pageBean) {
        QueryFilter queryFilter = QueryFilter.build()
                .withPage(pageBean);

        PageList query = query(queryFilter);
        return query;
    }

    public void startPageBean(PageBean pageBean) {
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, 2147483647, false);
        } else
            PageHelper.startPage(pageBean.getPage().intValue(), pageBean.getPageSize().intValue(), pageBean.showTotal());
    }
}
