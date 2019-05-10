package com.zutspider.model;

public class BookLib {
    private String bCode;
    private String index;
    private String addr;
    private String stat;
    private String juanqi;
    private String type;


    public String getbCode() {
        return bCode;
    }

    public void setbCode(String bCode) {
        this.bCode = bCode;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getJuanqi() {
        return juanqi;
    }

    public void setJuanqi(String juanqi) {
        this.juanqi = juanqi;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BookLib{" +
                "bCode='" + bCode + '\'' +
                ", index='" + index + '\'' +
                ", addr='" + addr + '\'' +
                ", stat='" + stat + '\'' +
                ", juanqi='" + juanqi + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
