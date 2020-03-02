package com.wfm.platform.service.impl;

import com.wfm.platform.dao.SysMenuMapper;
import com.wfm.platform.entities.SysMenu;
import com.wfm.platform.service.SysMenuService;
import com.wfm.platform.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper dao;
    @Autowired
    private IdWorker idWorker;

    @Override
    public int deleteById(String id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int save(SysMenu record) {
        record.setId(idWorker.nextId() + "");
        return dao.insertSelective(record);
    }

    @Override
    public SysMenu findById(String id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int update(SysMenu record) {
        return dao.updateByPrimaryKeySelective(record);
    }
}