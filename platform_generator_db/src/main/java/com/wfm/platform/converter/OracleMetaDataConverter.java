package com.wfm.platform.converter;

import com.wfm.platform.dao.MetaDataConverter;
import com.wfm.platform.entities.Column;
import com.wfm.platform.entities.Index;

import java.util.Map;

/**
 * @author Weifengming
 * @description Oracle元数据转化器
 * @date 2020/2/28
 */
public class OracleMetaDataConverter extends CommonMetaDataConverter {
    private static final MetaDataConverter instance = new OracleMetaDataConverter();

    public static MetaDataConverter getInstance() {
        return instance;
    }

    @Override
    public Column convertMap2Column(Map<String, String> map) {
        Column column = super.convertMap2Column(map);

        String charLength = getValue(map, "CHAR_LENGTH");
        /*if (!"0".equals(charLength) && column.getDataType().contains("CHAR")) {
          String length = getValue(map, "DATA_LENGTH");
          String.valueOf(Integer.valueOf(charLength).intValue() * 2)
          if (NumberUtils.toInt(length) == NumberUtils.toInt(charLength) * 2) {
              charLength += " char";
          }*/
        column.setLength(charLength);
     /* } else {
          // Oracel，非数值精度为null
          String precision = getValue(map, "DATA_PRECISION");
          if (StringUtils.isNotEmpty(precision)
                  && !"0".equals(precision)) {
              column.setLength(precision);
          } else {
              column.setLength(getValue(map, "DATA_LENGTH"));
          }
        }*/
        return column;
    }

    @Override
    public Index convertMap2Index(Map<String, String> map) {
        Index index = super.convertMap2Index(map);
        // Oracle的user_indexes的uniqueness字段 as IS_UNIQUE，如果返回的是NONUNIQUE表示可为空
        index.setUnique("NONUNIQUE".equals(
                getValue(map, "IS_UNIQUE")) ? false : true);
        return index;
    }
}
