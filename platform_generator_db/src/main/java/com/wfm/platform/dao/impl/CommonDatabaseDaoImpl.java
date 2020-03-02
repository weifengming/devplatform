package com.wfm.platform.dao.impl;

import com.wfm.platform.constants.GeneratorConstants;
import com.wfm.platform.constants.GeneratorDatabaseType;
import com.wfm.platform.dao.MetaDataConverter;
import com.wfm.platform.exception.DAOException;
import com.wfm.platform.util.Dom4jUtils;
import com.wfm.platform.vo.ConnectParam;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Weifengming
 * @description 通用数据库元信息查询类
 * @date 2020/2/28
 */
public class CommonDatabaseDaoImpl extends AbstractDatabaseDaoImpl {

    private String driver;
    private String url;
    private Map<String, String> selectMap = new HashMap<>();
    protected MetaDataConverter converter;
    private GeneratorDatabaseType databaseType;

    public CommonDatabaseDaoImpl(ConnectParam connectParam, GeneratorDatabaseType databaseType) {
        super(connectParam);
        setDatabaseType(databaseType);
        loadSqlXml(databaseType);
    }

    public void setDatabaseType(GeneratorDatabaseType databaseType) {
        this.databaseType = databaseType;
        setConverter(converter);
    }

    /**
     * 加载 数据库语句 xml
     *
     * @param databaseType
     */
    private void loadSqlXml(GeneratorDatabaseType databaseType) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(
                databaseType.getFileName());
        Document doc = Dom4jUtils.getDocument(is);
        if (doc != null) {
            Element root = doc.getRootElement();

            driver = root.elementText(GeneratorConstants.GENERATOR_DRIVER.code);
            url = root.elementText(GeneratorConstants.GENERATOR_URL.code);

            for (Element selectElem : (List<Element>) root.elements(GeneratorConstants.GENERATOR_ELEMENT_SELECT.code)) {
                selectMap.put(selectElem.attributeValue(GeneratorConstants.GENERATOR_ELEMENT_ATTRIBUTE_NAME.code), selectElem.getTextTrim());
            }
        }
        try {
            is.close();
        } catch (IOException e) {
        }
    }

    @Override
    protected String getDriver() throws DAOException {
        return driver;
    }

    @Override
    protected String getUrl(String host, int port, String dbName) throws DAOException {
        return String.format(url, host, port, dbName);
    }

    @Override
    protected String getQuerySql(String sqlKey) throws DAOException {
        if (selectMap.containsKey(sqlKey)) {
            return selectMap.get(sqlKey);
        }
        throw new DAOException(DAOException.QUERY_EXCEPTION, "获取sql查询出错，数据库枚举类型为：" + databaseType + "，查询语句为：" + sqlKey, null);
    }
}
