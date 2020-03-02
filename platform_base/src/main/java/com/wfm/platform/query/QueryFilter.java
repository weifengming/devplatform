package com.wfm.platform.query;

import com.wfm.platform.exception.SystemException;
import com.wfm.platform.util.BeanUtils;
import com.wfm.platform.util.StringHelper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.*;

/**
 * @author Weifengming
 * @description 通用查询器
 * @date 2020/2/17
 */
@ApiModel(description = "通用查询器")
public class QueryFilter {

    public static final String WHERE_SQL_TAG = "whereSql";
    public static final String ORDER_SQL_TAG = "orderBySql";

    @ApiModelProperty(name = "pageBean", value = "分页信息")
    private PageBean pageBean;

    @ApiModelProperty(name = "sorter", value = "排序字段")
    private List<FieldSort> sorter = new ArrayList();

    @ApiModelProperty(name = "params", value = "查询参数")
    private Map<String, Object> params = new LinkedHashMap();

    @ApiModelProperty(name = "querys", value = "查询条件组")
    private List<QueryField> querys = new ArrayList();
    private Class<?> clazz;

    public PageBean getPageBean() {
        return this.pageBean;
    }

    private QueryFilter() {
    }

    private QueryFilter(Class<?> clazz) {
        this.clazz = clazz;
    }

    public static QueryFilter build() {
        return new QueryFilter();
    }

    public static QueryFilter build(Class<?> clazz) {
        return new QueryFilter(clazz);
    }

    public QueryFilter withDefaultPage() {
        this.pageBean = new PageBean();
        return this;
    }

    public QueryFilter withPage(PageBean pageBean) {
        this.pageBean = pageBean;
        return this;
    }

    public QueryFilter withSorter(FieldSort fieldSort) {
        this.sorter.add(fieldSort);
        return this;
    }

    public QueryFilter withQuery(QueryField queryField) {
        this.querys.add(queryField);
        return this;
    }

    public QueryFilter withParam(String key, Object value) {
        this.params.put(key, value);
        return this;
    }

    public Map<String, Object> getParams() throws SystemException {
        generatorSQL();

        initParams(this.querys);
        return this.params;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public void addFilter(String property, Object value, QueryOP operation, FieldRelation relation) {
        QueryField queryField = new QueryField(property, value, operation, relation);
        this.querys.add(queryField);
    }

    public void addFilter(String property, Object value, QueryOP operation, FieldRelation relation, String group) {
        QueryField queryField = new QueryField(property, value, operation, relation, group);
        this.querys.add(queryField);
    }

    public void addFilter(String property, Object value, QueryOP operation) {
        QueryField queryField = new QueryField(property, value, operation, FieldRelation.AND);
        this.querys.add(queryField);
    }

    public void addParams(String property, Object value) {
        this.params.put(property, value);
    }

    public List<FieldSort> getSorter() {
        return this.sorter;
    }

    public void setSorter(List<FieldSort> sorter) {
        this.sorter = sorter;
    }

    public List<QueryField> getQuerys() {
        return this.querys;
    }

    public void setQuerys(List<QueryField> querys) {
        this.querys = querys;
    }

    public Class<?> getClazz() {
        return this.clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    private void generatorSQL() throws SystemException {
        String querySQL = generatorQuerySQL();
        if (StringHelper.isNotEmpty(querySQL)) {
            this.params.put(WHERE_SQL_TAG, querySQL);
        }
        int orderSize = this.sorter.size();
        if (orderSize > 0) {
            StringBuffer sb = new StringBuffer();
            int i = 0;
            for (FieldSort fieldSort : this.sorter) {
                if (i++ > 0) {
                    sb.append(",");
                }
                sb.append(fieldSort.toSql(this.clazz));
            }
            this.params.put(ORDER_SQL_TAG, sb.toString());
        }
    }

    private String generatorQuerySQL() throws SystemException {
        int size = this.querys.size();
        if (size == 0) return "";
        if (size == 1) {
            return ((QueryField) this.querys.get(0)).toSql(this.clazz);
        }
        if (size > 1) {
            Map map = new HashMap();

            for (QueryField element : this.querys) {
                String group = element.getGroup();
                List list = (List) map.get(group);
                if (BeanUtils.isEmpty(list)) {
                    list = new ArrayList();
                    map.put(group, list);
                }
                list.add(element);
            }
            Object sbList = new ArrayList();

            Iterator it = map.keySet().iterator();
            while (it.hasNext()) {
                StringBuffer sqlBuf = new StringBuffer();
                String group = (String) it.next();
                List list = (List) map.get(group);
                QueryField firstField = (QueryField) list.get(0);
                String relation = firstField.getRelation().value();
                int fieldList = list.size();
                for (int i = 0; i < fieldList; i++) {
                    if (i > 0) {
                        sqlBuf.append(" " + relation + " ");
                    }
                    sqlBuf.append(((QueryField) list.get(i)).toSql(this.clazz));
                }
                if (fieldList > 1) {
                    sqlBuf.insert(0, " (");
                    sqlBuf.append(") ");
                }
                ((List) sbList).add(sqlBuf);
            }

            StringBuffer result = new StringBuffer();
            for (int i = 0; i < ((List) sbList).size(); i++) {
                if (i > 0) {
                    result.append(" " + FieldRelation.AND.value() + " ");
                }
                result.append(((StringBuffer) ((List) sbList).get(i)).toString());
            }
            return result.toString();
        }
        return (String) "";
    }

    private void initParams(List<QueryField> querys) {
        if (BeanUtils.isEmpty(querys)) return;
        for (QueryField element : querys) {
            QueryField queryField = element;
            QueryOP operation = queryField.getOperation();
            if ((QueryOP.IS_NULL.equals(operation)) || (QueryOP.NOTNULL.equals(operation)) ||
                    (QueryOP.IN
                            .equals(operation))) {
                continue;
            }
            String property = queryField.getProperty();
            if (property.indexOf(".") > -1) {
                property = property.substring(property.indexOf(".") + 1);
            }
            Object value = queryField.getValue();
            this.params.put(property, value);
        }
    }
}
