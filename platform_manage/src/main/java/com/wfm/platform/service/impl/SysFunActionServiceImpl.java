package com.wfm.platform.service.impl;

import com.wfm.platform.dao.SysFunActionMapper;
import com.wfm.platform.entities.SysFunAction;
import com.wfm.platform.service.SysFunActionService;
import com.wfm.platform.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysFunActionServiceImpl implements SysFunActionService {

    @Autowired
    private SysFunActionMapper dao;
    @Autowired
    private IdWorker idWorker;

    @Override
    public int deleteById(String id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int save(SysFunAction record) {
        record.setId(idWorker.nextId() + "");
        return dao.insertSelective(record);
    }

    @Override
    public SysFunAction findById(String id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int update(SysFunAction record) {
        return dao.updateByPrimaryKeySelective(record);
    }
}