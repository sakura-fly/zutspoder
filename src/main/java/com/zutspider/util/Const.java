package com.zutspider.util;

import java.util.HashMap;
import java.util.Map;

public class Const {


    // URL
    // 登录
    public final static String LOGIN = "https://authserver.zut.edu.cn/authserver/login";
    // 办事大厅
    public final static String SERVICE_HALL = "http://i.zut.edu.cn/";
    // 办事大厅
    public final static String QUERY_SCORE = "http://app.zut.edu.cn/publicapp/sys/cjcxapp/modules/scoreQuery/scoreQueryAction.do";

    // 获取学年学期
    public static final String SCHOOL_YEAR_TERMS = "http://app.zut.edu.cn/publicapp/sys/cjcxapp/scoreQuery/getSchoolYearTerms.do";

    // 查课表
    public static final String QUERY_COURSE = "http://app.zut.edu.cn/publicapp/sys/mykbxt/myTimeTable/queryThisWeekCourses.do";


    // 请求头
    public final static Map<String, String> dHeaders = new HashMap<String, String>(){
        {
            // put("Host","authserver.zut.edu.cn");
            put("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0");
            put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            put("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
            // put("Referer","https://authserver.zut.edu.cn/authserver/login?service=http://i.zut.edu.cn/new/index.html");
            put("Accept-Encoding","gzip, deflate, br");
            put("Connection","keep-alive");
            put("Upgrade-Insecure-Requests","1");
            put("Content-Type","application/x-www-form-urlencoded");
        }
    };



}
