package com.wfm.platform.service.impl;

import com.wfm.platform.dao.BaseDao;
import com.wfm.platform.dao.SysOrgMapper;
import com.wfm.platform.entities.SysOrg;
import com.wfm.platform.service.SysOrgService;
import com.wfm.platform.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysOrgServiceImpl extends ServiceImpl<String, SysOrg> implements SysOrgService {

    @Autowired
    private SysOrgMapper orgDao;
    @Autowired
    private IdWorker idWorker;

    @Override
    protected BaseDao<String, SysOrg> getDao() {
        return this.orgDao;
    }

    @Override
    public List<SysOrg> findAllParents() {
        return orgDao.findAllParents();
    }

    @Override
    public List<SysOrg> findChildrenByParentId(String parentId) {
        return orgDao.findChildrenByParentId(parentId);
    }
}