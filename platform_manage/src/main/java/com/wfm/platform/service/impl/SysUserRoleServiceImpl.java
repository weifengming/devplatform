package com.wfm.platform.service.impl;

import com.wfm.platform.dao.SysUserRoleMapper;
import com.wfm.platform.entities.SysUserRole;
import com.wfm.platform.service.SysUserRoleService;
import com.wfm.platform.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper dao;
    @Autowired
    private IdWorker idWorker;

    @Override
    public int deleteById(String id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int save(SysUserRole record) {
        record.setId(idWorker.nextId() + "");
        return dao.insertSelective(record);
    }

    @Override
    public SysUserRole findById(String id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int update(SysUserRole record) {
        return dao.updateByPrimaryKeySelective(record);
    }
}