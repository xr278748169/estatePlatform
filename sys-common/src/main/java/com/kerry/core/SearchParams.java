package com.kerry.core;

import java.io.Serializable;
import java.util.Map;

/**
 * 查询参数类
 * Created by wangshen on 2017/4/10.
 */
public class SearchParams implements Serializable{

    private int page;

    private int pageSize;

    private String sort;

    private String order;

    private Map params;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }
}
