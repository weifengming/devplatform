package com.wfm.platform.controller;

import com.wfm.platform.entities.Result;
import com.wfm.platform.entities.SysOrg;
import com.wfm.platform.entities.TreeEntity;
import com.wfm.platform.exception.StatusCode;
import com.wfm.platform.service.SysOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Weifengming
 * @description 组织机构控制器
 * @date 2020/5/4
 */
@Api(value = "API - SysOrgController", description = "组织机构管理")
@CrossOrigin
@RestController
@RequestMapping("/api/manage/sorg/v1")
public class SysOrgController {

    @Autowired
    private SysOrgService sysOrgService;

    @ApiOperation(value = "获取组织机构树", httpMethod = "GET", notes = "获取组织机构树")
    @RequestMapping(value = "/org/jsonParentsOrgTree", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public Result jsonParentsOrgTree() {
        try {
            List<SysOrg> allParentsOfOrg = sysOrgService.findAllParents();
            List<TreeEntity> orgTree = new ArrayList<>();
            for (SysOrg sysOrg : allParentsOfOrg) {
                TreeEntity tree = new TreeEntity(sysOrg.getOrgName(),
                        sysOrg.getOrgName(),
                        sysOrg.getId(),
                        sysOrg.getParentId(),
                        "");
                orgTree.add(tree);
            }
            return new Result(true, StatusCode.OK, "获取组织机构成功", orgTree);
        } catch (Exception e) {
            return new Result(false, StatusCode.ERROR, e.getMessage());
        }
    }


    @ApiOperation(value = "根据父节点ID获取组织机构树", httpMethod = "GET", notes = "根据父节点ID获取组织机构树")
    @RequestMapping(value = "/org/jsonOrgTreeByParentId", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public Result jsonOrgTreeByParentId(String parentId) {
        try {
            List<SysOrg> orgChildren = sysOrgService.findChildrenByParentId(parentId);
            List<TreeEntity> orgTree = new ArrayList<>();
            for (SysOrg sysOrg : orgChildren) {
                TreeEntity tree = new TreeEntity(sysOrg.getOrgName(),
                        sysOrg.getOrgName(),
                        sysOrg.getId(),
                        sysOrg.getParentId(),
                        "");
                orgTree.add(tree);
            }
            return new Result(true, StatusCode.OK, "获取组织机构成功", orgTree);
        } catch (Exception e) {
            return new Result(false, StatusCode.ERROR, e.getMessage());
        }
    }
}
