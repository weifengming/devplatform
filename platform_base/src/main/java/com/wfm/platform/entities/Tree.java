package com.wfm.platform.entities;

import java.util.List;

/**
 * @author Weifengming
 * @description 树组件
 * @date 2020/2/18
 */
public interface Tree {
    public abstract String getId();

    public abstract String getParentId();

    public abstract String getText();

    public abstract List<Tree> getChildren();

    public abstract void setChildren(List<Tree> paramList);

    public abstract void setIsParent(String paramString);
}
