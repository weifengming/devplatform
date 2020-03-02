package com.wfm.platform.service.impl;

import com.wfm.platform.dao.SysRoleFunMapper;
import com.wfm.platform.entities.SysRoleFun;
import com.wfm.platform.service.SysRoleFunService;
import com.wfm.platform.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysRoleFunServiceImpl implements SysRoleFunService {

    @Autowired
    private SysRoleFunMapper dao;
    @Autowired
    private IdWorker idWorker;

    @Override
    public int deleteById(String id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int save(SysRoleFun record) {
        record.setId(idWorker.nextId() + "");
        return dao.insertSelective(record);
    }

    @Override
    public SysRoleFun findById(String id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int update(SysRoleFun record) {
        return dao.updateByPrimaryKeySelective(record);
    }
}