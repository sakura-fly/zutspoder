package com.zutspider.model;

/**
 * 页码信息
 */
public class Page {
    // 页码
    private String pageNumber;

    // 每页数量
    private String pageSize;

    public Page() {
    }

    public Page(String pageNumber, String pageSize) {

        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
