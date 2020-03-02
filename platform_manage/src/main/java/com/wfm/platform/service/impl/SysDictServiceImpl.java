package com.wfm.platform.service.impl;

import com.wfm.platform.dao.SysDictMapper;
import com.wfm.platform.entities.SysDict;
import com.wfm.platform.service.SysDictService;
import com.wfm.platform.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysDictServiceImpl implements SysDictService {

    @Autowired
    private SysDictMapper dao;
    @Autowired
    private IdWorker idWorker;

    @Override
    public int deleteById(String id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int save(SysDict record) {
        record.setId(idWorker.nextId() + "");
        return dao.insertSelective(record);
    }

    @Override
    public SysDict findById(String id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int update(SysDict record) {
        return dao.updateByPrimaryKeySelective(record);
    }
}