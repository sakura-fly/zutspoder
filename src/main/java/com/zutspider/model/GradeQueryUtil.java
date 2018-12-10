package com.zutspider.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * 成绩查询工具
 */
public class GradeQueryUtil {

    // 总查询条件
    private ArrayList<Object> queryList = new ArrayList<Object>();

    // or查询条件
    private ArrayList<GradeQuery> orQueryList = new ArrayList<GradeQuery>();

    // 学年
    private GradeQuery xnxqdm = new GradeQuery("XNXQDM", "equal", "AND");

    // 总查询信息包括查询条件与分页
    private Map<String, String> dataMap = new HashMap<String, String>();


    // 设置学年
    public GradeQueryUtil setXn(String xn) {
        xnxqdm.setValue(xn);
        queryList.add(xnxqdm);
        return this;
    }

    // 设置查询课程名
    public GradeQueryUtil setKcm(String kcm) {
        GradeQuery kcmQuery = new GradeQuery("KCM", kcm, "include", "OR");
        orQueryList.add(kcmQuery);
        return this;
    }

    // 组合查询条件
    public GradeQueryUtil buildQuery() {
        queryList.add(orQueryList);
        dataMap.put("querySetting",new JSONArray(queryList).toString());
        return this;
    }

    // 添加页码信息
    public GradeQueryUtil addPage2Map(Page page) {
        JSONObject pageJsonObject = new JSONObject(page);
        Map<String, Object> pageMap = pageJsonObject.toMap();
        Set<String> pageKeys = pageMap.keySet();
        for (String key : pageKeys) {
            dataMap.put(key, (String) pageMap.get(key));
        }

        return this;
    }

    // 获取查询信息
    public Map<String, String> getQuery(){
        return dataMap;
    }


}
