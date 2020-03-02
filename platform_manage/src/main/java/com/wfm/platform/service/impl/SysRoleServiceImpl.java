package com.wfm.platform.service.impl;

import com.wfm.platform.dao.SysRoleMapper;
import com.wfm.platform.entities.SysRole;
import com.wfm.platform.service.SysRoleService;
import com.wfm.platform.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper dao;
    @Autowired
    private IdWorker idWorker;

    @Override
    public int deleteById(String id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int save(SysRole record) {
        record.setId(idWorker.nextId() + "");
        return dao.insertSelective(record);
    }

    @Override
    public SysRole findById(String id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int update(SysRole record) {
        return dao.updateByPrimaryKeySelective(record);
    }
}