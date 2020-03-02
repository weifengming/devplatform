package com.wfm.platform.service.impl;

import com.wfm.platform.dao.SysFunMapper;
import com.wfm.platform.entities.SysFun;
import com.wfm.platform.service.SysFunService;
import com.wfm.platform.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysFunServiceImpl implements SysFunService {

    @Autowired
    private SysFunMapper dao;
    @Autowired
    private IdWorker idWorker;

    @Override
    public int deleteById(String id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int save(SysFun record) {
        record.setId(idWorker.nextId() + "");
        return dao.insertSelective(record);
    }

    @Override
    public SysFun findById(String id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int update(SysFun record) {
        return dao.updateByPrimaryKeySelective(record);
    }
}