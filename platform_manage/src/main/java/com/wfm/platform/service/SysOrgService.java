package com.wfm.platform.service;

import com.wfm.platform.entities.SysOrg;

import java.util.List;

public interface SysOrgService {

    List<SysOrg> findAllParents();

    List<SysOrg> findChildrenByParentId(String parentId);
}