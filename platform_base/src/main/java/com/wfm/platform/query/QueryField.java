package com.wfm.platform.query;

import com.wfm.platform.exception.SystemException;
import com.wfm.platform.service.DbTypeService;
import com.wfm.platform.util.ApplicationContextUtil;
import com.wfm.platform.util.BeanUtils;
import com.wfm.platform.util.FieldConvertUtil;
import com.wfm.platform.util.StringHelper;
import com.wfm.platform.util.date.DateFormatUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Weifengming
 * @description 查询条件
 * @date 2020/2/18
 */
@ApiModel(description = "查询条件")
public class QueryField {
    @ApiModelProperty(name = "property", value = "实体类属性")
    private String property;

    @ApiModelProperty(name = "operation", value = "比较符")
    private QueryOP operation = QueryOP.EQUAL;

    @ApiModelProperty(name = "value", value = "比较值")
    private Object value;

    @ApiModelProperty(name = "relation", value = "同一个分组内的多个条件之间的组合关系")
    private FieldRelation relation = FieldRelation.AND;

    @ApiModelProperty(name = "group", value = "查询条件分组，默认分组为main，多个分组将会按照and的关系组合在一起")
    private String group = "main";

    @ApiModelProperty("false")
    private Boolean hasInitValue = Boolean.valueOf(false);

    public QueryField() {
    }

    public QueryField(String property, Object value) {
        this(property, value, QueryOP.EQUAL, FieldRelation.AND);
    }

    public QueryField(String property, Object value, QueryOP operation) {
        this(property, value, operation, FieldRelation.AND);
    }

    public QueryField(String property, Object value, FieldRelation relation) {
        this(property, value, QueryOP.EQUAL, relation);
    }

    public QueryField(String property, Object value, QueryOP operation, FieldRelation relation) {
        this(property, value, operation, relation, null);
    }

    public QueryField(String property, Object value, QueryOP operation, FieldRelation relation, String group) {
        this.property = property;
        this.value = value;
        this.operation = operation;
        this.relation = relation;
        if (StringHelper.isNotEmpty(group))
            this.group = group;
    }

    public String getProperty() {
        return this.property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public QueryOP getOperation() {
        return this.operation;
    }

    public void setOperation(QueryOP operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Boolean isGroup() {
        return Boolean.valueOf(false);
    }

    public FieldRelation getRelation() {
        return this.relation;
    }

    public void setRelation(FieldRelation relation) {
        this.relation = relation;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Boolean getHasInitValue() {
        return this.hasInitValue;
    }

    public void setHasInitValue(Boolean hasInitValue) {
        this.hasInitValue = hasInitValue;
    }

    private void initSqlValue() {
        if (this.hasInitValue.booleanValue()) {
            return;
        }
        this.hasInitValue = Boolean.valueOf(true);
        if (QueryOP.IN.equals(this.operation)) {
            this.value = getInValueSql();
        }
        if (BeanUtils.isNotEmpty(this.value))
            if ((QueryOP.LIKE.equals(this.operation)) && (!this.value.toString().startsWith("%")) && (!this.value.toString().endsWith("%")))
                this.value = new StringBuilder().append("%").append(this.value).append("%").toString();
            else if ((QueryOP.LEFT_LIKE.equals(this.operation)) && (!this.value.toString().startsWith("%")))
                this.value = new StringBuilder().append("%").append(this.value).toString();
            else if ((QueryOP.RIGHT_LIKE.equals(this.operation)) && (!this.value.toString().endsWith("%")))
                this.value = new StringBuilder().append(this.value).append("%").toString();
            else if (QueryOP.EQUAL_IGNORE_CASE.equals(this.operation))
                this.value = this.value.toString().toUpperCase();
    }

    public String toSql(Class clazz)
            throws SystemException {
        initSqlValue();
        if (this.operation == null) {
            this.operation = QueryOP.EQUAL;
        }

        if (this.property == null)
            throw new SystemException("The 'property' in QueryField can not be empty.");
        String fieldParam;
        if (this.property.indexOf(".") > -1) {
            fieldParam = new StringBuilder().append("#{").append(this.property.substring(this.property.indexOf(".") + 1)).append("}").toString();
        } else {
            fieldParam = new StringBuilder().append("#{").append(this.property).append("}").toString();
        }
        String sql = FieldConvertUtil.property2Field(this.property, clazz);
        if (QueryOP.EQUAL.equals(this.operation)) {
            sql = new StringBuilder().append(sql).append(" = ").append(fieldParam).toString();
        } else if (QueryOP.EQUAL_IGNORE_CASE.equals(this.operation)) {
            sql = new StringBuilder().append("upper(").append(sql).append(")").append(" = ").append(fieldParam).toString();
        } else if (QueryOP.LESS.equals(this.operation)) {
            sql = new StringBuilder().append(sql).append(" < ").append(fieldParam).toString();
        } else if (QueryOP.LESS_EQUAL.equals(this.operation)) {
            sql = new StringBuilder().append(sql).append(" <= ").append(fieldParam).toString();
        } else if (QueryOP.GREAT.equals(this.operation)) {
            sql = new StringBuilder().append(sql).append(" > ").append(fieldParam).toString();
        } else if (QueryOP.GREAT_EQUAL.equals(this.operation)) {
            sql = new StringBuilder().append(sql).append(" >= ").append(fieldParam).toString();
        } else if (QueryOP.NOT_EQUAL.equals(this.operation)) {
            sql = new StringBuilder().append(sql).append(" != ").append(fieldParam).toString();
        } else if (QueryOP.LEFT_LIKE.equals(this.operation)) {
            sql = new StringBuilder().append(sql).append(" like ").append(fieldParam).toString();
        } else if (QueryOP.RIGHT_LIKE.equals(this.operation)) {
            sql = new StringBuilder().append(sql).append(" like  ").append(fieldParam).toString();
        } else if (QueryOP.LIKE.equals(this.operation)) {
            sql = new StringBuilder().append(sql).append(" like  ").append(fieldParam).toString();
        } else if (QueryOP.IS_NULL.equals(this.operation)) {
            sql = new StringBuilder().append(sql).append(" is null ").toString();
        } else if (QueryOP.NOTNULL.equals(this.operation)) {
            sql = new StringBuilder().append(sql).append(" is not null").toString();
        } else if (QueryOP.IN.equals(this.operation)) {
            String valueStr = this.value.toString();
            if ((valueStr.startsWith("(")) && (valueStr.endsWith(")"))) {
                valueStr = valueStr.replace("(", "");
                valueStr = valueStr.replace(")", "");
                String[] strList = valueStr.split(",");
                int len = strList.length;

                if (len > 1000)
                    sql = getOutLimitInSql(sql, strList);
                else
                    sql = new StringBuilder().append(sql).append(" in  ").append(this.value).toString();
            } else {
                sql = new StringBuilder().append(sql).append(" in  ").append(this.value).toString();
            }
        } else if (QueryOP.BETWEEN.equals(this.operation)) {
            sql = new StringBuilder().append(sql).append(getBetweenSql()).toString();
        } else {
            sql = new StringBuilder().append(sql).append(" =  ").append(fieldParam).toString();
        }
        return sql;
    }

    private String getInValueSql() {
        String inValueSql = "";
        if ((this.value instanceof String)) {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            StringTokenizer st = new StringTokenizer(this.value.toString(), ",");
            while (st.hasMoreTokens()) {
                sb.append("'");
                sb.append(st.nextToken());
                sb.append("'");
                sb.append(",");
            }
            sb = new StringBuilder(sb.substring(0, sb.length() - 1));
            sb.append(")");
            inValueSql = sb.toString();
        } else {
            Object localObject1;
            Object obj;
            if ((this.value instanceof List)) {
                List objList = (List) this.value;
                StringBuilder sb = new StringBuilder();
                sb.append("(");
                for (localObject1 = objList.iterator(); ((Iterator) localObject1).hasNext(); ) {
                    obj = ((Iterator) localObject1).next();

                    sb.append("'");
                    sb.append(obj.toString());
                    sb.append("'");
                    sb.append(",");
                }
                sb = new StringBuilder(sb.substring(0, sb.length() - 1));
                sb.append(")");
                inValueSql = sb.toString();
            } else if ((this.value instanceof String[])) {
                String[] objList = (String[]) this.value;
                StringBuilder sb = new StringBuilder();
                sb.append("(");
                for (int i = 0; i < objList.length; i++) {
                    sb.append("'");
                    sb.append(objList[i]);
                    sb.append("'");
                    sb.append(",");
                }
                sb = new StringBuilder(sb.substring(0, sb.length() - 1));
                sb.append(")");
                inValueSql = sb.toString();
            }
        }
        return (String) inValueSql;
    }

    private String getBetweenSql() {
        StringBuilder sb = new StringBuilder();
        sb.append(" between ");
        DbTypeService dbTypeService = (DbTypeService) ApplicationContextUtil.getBean(DbTypeService.class);
        String dbType = dbTypeService.getDbType();
        if ((this.value instanceof List)) {
            List objList = (List) this.value;
            for (int i = 0; i < objList.size(); i++) {
                Object obj = objList.get(i);
                if (i == 1) {
                    sb.append(" and ");
                }
                if ((obj instanceof LocalDateTime)) {
                    String dateString = DateFormatUtil.format((LocalDateTime) obj, "yyyy-MM-dd HH:mm:ss");
                    if ("oracle".equals(dbType))
                        sb.append(new StringBuilder().append("TO_DATE(substr('").append(dateString).append("',1,19),'yyyy-mm-dd hh24:mi:ss')").toString());
                    else
                        sb.append(new StringBuilder().append("'").append(dateString).append("'").toString());
                } else {
                    String dataStr = obj.toString();
                    if (dataStr.endsWith("Z"))
                        try {
                            dataStr = dataStr.replace("Z", " UTC");
                            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
                            LocalDateTime date = LocalDateTime.parse(dataStr, format);
                            dataStr = DateFormatUtil.format(date, "yyyy-MM-dd HH:mm:ss");
                        } catch (Exception e) {
                        }
                    if ("oracle".equals(dbType))
                        sb.append(new StringBuilder().append("TO_DATE(substr('").append(dataStr).append("',1,19),'yyyy-mm-dd hh24:mi:ss')").toString());
                    else {
                        sb.append(new StringBuilder().append("'").append(dataStr).append("'").toString());
                    }
                }
            }
        }
        sb.append(" ");
        return sb.toString();
    }

    private String getOutLimitInSql(String sql, String[] strList) {
        int index = 0;
        int times = 0;
        int i = 0;
        int len = strList.length;
        String field = sql;
        String[] newValue = new String[1000];
        StringBuilder newSql = new StringBuilder();
        newSql.append(" ( ");
        for (String str : strList) {
            newValue[index] = str;
            index++;
            i++;
            if (index % 1000 == 0) {
                if (times > 0) {
                    newSql.append(" or ");
                }
                times++;
                newSql.append(" ");
                newSql.append(field);
                newSql.append(" in ");
                newSql.append("(");
                newSql.append(StringHelper.convertArrayToString(newValue));
                newSql.append(")");
                newSql.append(" ");
                int size = len - 1000 * times;
                newValue = size >= 1000 ? new String[1000] : new String[size];
                index = 0;
            } else if (i == len) {
                if (times > 0) {
                    newSql.append(" or ");
                }
                times++;
                newSql.append(" ");
                newSql.append(field);
                newSql.append(" in ");
                newSql.append("(");
                newSql.append(StringHelper.convertArrayToString(newValue));
                newSql.append(")");
                newSql.append(" ");
            }
        }

        newSql.append(" ) ");
        return newSql.toString();
    }
}
