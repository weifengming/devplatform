package com.wfm.platform.entities;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Weifengming
 * @description 树组件
 * @date 2020/2/18
 */
public class TreeEntity extends BaseModel<String> implements Tree {

    @ApiModelProperty(name = "isParent", value = "是否父节点")
    protected String isParent;

    public static final String ICON_COMORG = "";
    protected Long sn;
    protected String icon;
    protected String name;
    protected String parentId;
    protected String id;
    protected boolean nocheck = false;
    protected boolean chkDisabled = false;
    protected boolean click = true;
    protected String title = "";

    protected List<TreeEntity> children = new ArrayList();


    public String getIsParent() {
        return this.isParent;
    }

    public TreeEntity() {
    }

    public TreeEntity(String name, String title, String id, String parentId, String icon) {
        setName(name);
        this.parentId = parentId;
        this.id = id;
        this.icon = icon;
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getSn() {
        return this.sn;
    }

    public void setSn(Long sn) {
        this.sn = sn;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isNocheck() {
        return this.nocheck;
    }

    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }

    public boolean isChkDisabled() {
        return this.chkDisabled;
    }

    public boolean isClick() {
        return this.click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }

    public void setChkDisabled(boolean chkDisabled) {
        this.chkDisabled = chkDisabled;
    }

    public String getText() {
        return this.name;
    }

    public List getChildren() {
        return this.children;
    }

    public void setChildren(List children) {
        this.children = children;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }
}
