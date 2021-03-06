package com.wfm.platform.service.impl;

import com.wfm.platform.dao.SysLogMapper;
import com.wfm.platform.entities.SysLog;
import com.wfm.platform.service.SysLogService;
import com.wfm.platform.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper dao;
    @Autowired
    private IdWorker idWorker;

    @Override
    public int deleteById(String id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int save(SysLog record) {
        record.setId(idWorker.nextId() + "");
        return dao.insertSelective(record);
    }

    @Override
    public SysLog findById(String id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int update(SysLog record) {
        return dao.updateByPrimaryKeySelective(record);
    }
}