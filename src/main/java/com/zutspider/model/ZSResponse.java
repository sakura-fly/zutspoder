package com.zutspider.model;


import java.util.List;

/**
 * 请求结果
 */
public class ZSResponse {


    public ZSResponse(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public ZSResponse() {
    }

    // 结果码 1成功，-1未登录
    private int code;
    // 结果内容
    private String text;

    private List data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ZSResponse{" +
                "code=" + code +
                ", text='" + text + '\'' +
                '}';
    }
}
