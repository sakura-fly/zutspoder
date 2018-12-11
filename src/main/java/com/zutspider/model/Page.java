package com.zutspider.model;

import java.util.HashMap;
import java.util.Map;

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


    /**
     * 转换成新闻要用的 map
     *
     * @return 新闻要用的 map
     */
    public Map<String, String> toNewsPage() {
        Map<String, String> pageMap = new HashMap<String, String>();
        pageMap.put("pageNo", pageNumber);
        pageMap.put("pageSize", pageSize);
        return pageMap;
    }

}
