package com.wfm.platform.dao;

import com.wfm.platform.entities.*;

import java.util.Map;

/**
 * @author Weifengming
 * @description 元数据转换器接口
 * @date 2020/2/27
 */
public interface MetaDataConverter {
    /**
     * 将map转换为Table
     *
     * @param map
     * @return
     */
    Table convertMap2Table(Map<String, String> map);

    /**
     * 将map转换为Column
     *
     * @param map
     * @return
     */
    Column convertMap2Column(Map<String, String> map);

    /**
     * 将map转换为PrimaryKey
     *
     * @param map
     * @return
     */
    PrimaryKey convertMap2PrimaryKey(Map<String, String> map);

    /**
     * 将map转换为ForeignKey
     *
     * @param map
     * @return
     */
    ForeignKey convertMap2ForeignKey(Map<String, String> map);

    /**
     * 将map转换为Index
     *
     * @param map
     * @return
     */
    Index convertMap2Index(Map<String, String> map);

    /**
     * 将map转换为Triger
     *
     * @param map
     * @return
     */
    Trigger convertMap2Trigger(Map<String, String> map);
}
