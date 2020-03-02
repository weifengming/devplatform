package com.wfm.platform.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.session.RowBounds;

/**
 * @author Weifengming
 * @description 分页对象
 * @date 2020/2/17
 */
@ApiModel(description = "分页对象")
public class PageBean extends RowBounds {

    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_PAGE = 1;

    @ApiModelProperty(name = "page", value = "页号")
    protected int page = DEFAULT_PAGE;

    @ApiModelProperty(name = "pageSize", value = "分页大小")
    protected int pageSize = DEFAULT_PAGE_SIZE;

    @ApiModelProperty(name = "showTotal", value = "是否显示总条数")
    protected boolean showTotal = true;


    public PageBean() {
    }

    public PageBean(Integer page) {
        this.page = page.intValue();
    }

    public PageBean(Boolean showTotal) {
        this.showTotal = showTotal.booleanValue();
    }

    public PageBean(Integer page, Integer pageSize) {
        this.page = page.intValue();
        this.pageSize = pageSize.intValue();
    }

    public PageBean(Integer page, Integer pageSize, Boolean showTotal) {
        this.page = page.intValue();
        this.pageSize = pageSize.intValue();
        this.showTotal = showTotal.booleanValue();
    }

    public Integer getPageSize() {
        return Integer.valueOf(this.pageSize);
    }

    public Integer getPage() {
        return Integer.valueOf(this.page);
    }

    public boolean showTotal() {
        return this.showTotal;
    }

    public void setShowTotal(boolean showTotal) {
        this.showTotal = showTotal;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @JsonIgnore
    public int getLimit() {
        return this.pageSize;
    }

    @JsonIgnore
    public int getOffset() {
        return this.page > 0 ? (this.page - 1) * this.pageSize : 0;
    }
}
