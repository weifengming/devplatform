package com.wfm.platform.service.impl;

import com.wfm.platform.dao.SysOrgMapper;
import com.wfm.platform.entities.SysOrg;
import com.wfm.platform.service.SysOrgService;
import com.wfm.platform.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysOrgServiceImpl implements SysOrgService {

    @Autowired
    private SysOrgMapper dao;
    @Autowired
    private IdWorker idWorker;

    @Override
    public int deleteById(String id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int save(SysOrg record) {
        record.setId(idWorker.nextId() + "");
        return dao.insertSelective(record);
    }

    @Override
    public SysOrg findById(String id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int update(SysOrg record) {
        return dao.updateByPrimaryKeySelective(record);
    }
}