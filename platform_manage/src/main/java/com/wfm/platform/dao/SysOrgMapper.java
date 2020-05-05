package com.wfm.platform.dao;

import com.wfm.platform.entities.SysOrg;

import java.util.List;

public interface SysOrgMapper extends BaseDao<String, SysOrg> {

    List<SysOrg> findAllParents();

    List<SysOrg> findChildrenByParentId(String parentId);
}