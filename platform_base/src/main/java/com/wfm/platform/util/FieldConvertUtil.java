package com.wfm.platform.util;

import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.Collection;

/**
 * @author Weifengming
 * @description mybitas列名字段名映射工具类
 * @date 2020/2/17
 */
public class FieldConvertUtil {
    public static String property2Field(String property, Class clazz) {
        SqlSessionTemplate sqlSessionTemplate = (SqlSessionTemplate) ApplicationContextUtil.getBean(SqlSessionTemplate.class);
        Configuration configuration = sqlSessionTemplate.getConfiguration();
        Collection<String> resultMapNames = configuration.getResultMapNames();
        for (String name : resultMapNames) {
            if (name.indexOf(".") == -1) {
                continue;
            }
            ResultMap resultMap = configuration.getResultMap(name);
            Class type = resultMap.getType();
            if (type.equals(clazz)) {
                for (ResultMapping resultMappirng : resultMap.getPropertyResultMappings()) {
                    if (resultMappirng.getProperty().equals(property)) {
                        property = resultMappirng.getColumn();
                        return property;
                    }
                }
            }
        }
        return property;
    }

    public static String field2Property(String field, Class clazz) {
        String classNameSpace = clazz.getName() + ".";
        SqlSessionTemplate sqlSessionTemplate = (SqlSessionTemplate) ApplicationContextUtil.getBean(SqlSessionTemplate.class);
        Configuration configuration = sqlSessionTemplate.getConfiguration();
        Collection<String> resultMapNames = configuration.getResultMapNames();
        for (String name : resultMapNames) {
            if (name.indexOf(".") == -1) {
                continue;
            }
            ResultMap resultMap = configuration.getResultMap(name);
            if (resultMap.getId().indexOf(classNameSpace) != -1) {
                for (ResultMapping resultMappirng : resultMap.getPropertyResultMappings()) {
                    if (resultMappirng.getColumn().equals(field)) {
                        field = resultMappirng.getProperty();
                        return field;
                    }
                }
            }
        }
        return field;
    }
}
