package com.wfm.platform.converter;

import com.wfm.platform.dao.MetaDataConverter;
import com.wfm.platform.entities.Index;
import com.wfm.platform.entities.Table;

import java.util.Map;

/**
 * @author Weifengming
 * @description MySQL5元数据转换器
 * @date 2020/2/28
 */
public class MySQL5MetaDataConverter extends CommonMetaDataConverter {
    private static MetaDataConverter instance = new MySQL5MetaDataConverter();

    public static final MetaDataConverter getInstance() {
        return instance;
    }

    @Override
    public Table convertMap2Table(Map<String, String> map) {
        Table table = super.convertMap2Table(map);
        /**
         * MySQL5的information_schema.TABLES表的table_comment字段存储的值是：
         * 如果有注释："表注释; InnoDB free: 4096 kB"
         * 如果没有注释，"InnoDB free: 4096 kB"这样存储引擎信息
         * 故需要字符串截取
         */
        /*String desc = table.getDescription();
        int endIndex = desc.lastIndexOf(";");
        if (endIndex != -1) {
            desc = desc.substring(0, endIndex);
        }
        table.setDescription(desc);*/
        return table;
    }

    @Override
    public Index convertMap2Index(Map<String, String> map) {
        Index index = super.convertMap2Index(map);
        // MySql5的只有non_unique字段 , 没有IS_UNIQUE。0表示不为空
        index.setUnique("0".equals(getValue(map, "NON_UNIQUE")));
        return index;
    }
}
