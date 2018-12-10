package com.zutspider.model;

public class GradeQuery {
    // 查询条件
    private String name;
    // 查询内容
    private String value;
    private String builder;
    private String linkOpt;

    public GradeQuery(String name, String value, String builder, String linkOpt) {
        this.name = name;
        this.value = value;
        this.builder = builder;
        this.linkOpt = linkOpt;
    }
    public GradeQuery(String name, String builder, String linkOpt) {
        this.name = name;
        this.builder = builder;
        this.linkOpt = linkOpt;
    }

    public GradeQuery() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }

    public String getLinkOpt() {
        return linkOpt;
    }

    public void setLinkOpt(String linkOpt) {
        this.linkOpt = linkOpt;
    }
}
