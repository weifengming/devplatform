package com.wfm.platform.service.impl;

import com.wfm.platform.dao.SysUserTokenMapper;
import com.wfm.platform.entities.SysUserToken;
import com.wfm.platform.service.SysUserTokenService;
import com.wfm.platform.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysUserTokenServiceImpl implements SysUserTokenService {

    @Autowired
    private SysUserTokenMapper dao;
    @Autowired
    private IdWorker idWorker;

    @Override
    public int deleteById(String id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int save(SysUserToken record) {
        record.setId(idWorker.nextId() + "");
        return dao.insertSelective(record);
    }

    @Override
    public SysUserToken findById(String id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int update(SysUserToken record) {
        return dao.updateByPrimaryKeySelective(record);
    }
}