package com.wfm.platform.query;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Weifengming
 * @description 分页查询对象
 * @date 2020/2/17
 */
@ApiModel(description = "分页查询对象")
public class PageList<E> {
    @ApiModelProperty(name = "rows", value = "结果列表")
    List<E> rows;

    @ApiModelProperty(name = "total", value = "总记录数")
    long total;

    @ApiModelProperty(name = "page", value = "当前页码")
    int page;

    @ApiModelProperty(name = "pageSize", value = "每页条数")
    int pageSize;

    public PageList() {
    }

    public PageList(List<E> c) {
        if ((c instanceof Page)) {
            Page page = (Page) c;
            this.rows = page.getResult();
            this.total = page.getTotal();
            this.page = page.getPageNum();
            this.pageSize = page.getPageSize();
        } else {
            this.rows = c;
            this.total = this.rows.size();
        }
    }

    public PageList(Page<E> page) {
        this.rows = page.getResult();
        this.total = page.getTotal();
        this.page = page.getPageNum();
        this.pageSize = page.getPageSize();
    }

    public List<E> getRows() {
        return this.rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
