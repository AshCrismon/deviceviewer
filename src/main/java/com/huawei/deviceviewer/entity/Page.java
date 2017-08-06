package com.huawei.deviceviewer.entity;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * Author: hadoop
 * Date: 2017/8/6
 * Email: yadysun@gmail.com
 */
public class Page<T> {

    private int pageNo = 1;
    private int pageSize = 10;
    private int totalCount = 0;
    private int totalPages = 0;
    private List<T> objList;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<T> getObjList() {
        return objList;
    }

    public void setObjList(List<T> objList) {
        this.objList = objList;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
