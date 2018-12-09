package com.zutspider.spider;


import com.zutspider.model.ZSResponse;
import com.zutspider.util.Const;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 爬虫类，单例
 */
public class Spider {

    private static class instance {
        private static final Spider instance = new Spider();
    }

    private Spider() {
    }

    public static Spider getInstance() {
        return instance.instance;
    }

    private Map<String, String> cookies = new HashMap<String, String>();


    /**
     * 登录
     *
     * @param userName 用户名
     * @param pwd      密码
     * @return 1成功，-1失败
     * @throws IOException
     */
    public ZSResponse login(String userName, String pwd) throws IOException {
        // 获取Response
        Connection.Response r = Jsoup.connect(Const.LOGIN + "?service=" + Const.SERVICE_HALL).execute();

        // 替换cookies
        cookies = r.cookies();

        // 获取登录页面文档
        Document doc = Jsoup.parse(r.body());

        // 获取登录信息
        String lt = doc.select("input[name=lt]").val();
        String dllt = doc.select("input[name=dllt]").val();
        String execution = doc.select("input[name=execution]").val();
        String eventId = doc.select("input[name=_eventId]").val();
        String rmShown = doc.select("input[name=rmShown]").val();

        // 用户登录信息
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("lt", lt);
        userData.put("dllt", dllt);
        userData.put("execution", execution);
        userData.put("_eventId", eventId);
        userData.put("rmShown", rmShown);

        userData.put("username", userName);
        userData.put("password", pwd);

        // 登录
        Connection.Response loginResponse = post(userData,Const.LOGIN);

        // 更换cookies
        cookies = loginResponse.cookies();

        // 如果跳转到登录页面，登录失败
        return new ZSResponse(isLoginIndex(loginResponse),loginResponse.body());


    }

    /**
     * 是否登录
     *
     * @return 1登录，-1未登录
     */
    private int isLoginIndex(Connection.Response loginResponse){
        if (loginResponse.url().toString().startsWith("https://authserver.zut.edu.cn/authserver/login")) {
            return -1;
        } else {
            return 1;
        }
    }

    private Connection.Response post(Map<String,String> data,String url) throws IOException {
        System.out.println("----------------------");
        Set<String> keys = cookies.keySet();
        for (String k : keys){
            System.out.println(k + "===" + cookies.get(k));
        }
        System.out.println("----------------------");
        return Jsoup.connect(url)
                .cookies(cookies)  // 设置cookies
                .headers(Const.dHeaders)  // 设置消息头
                .data(data)  // post信息
                .ignoreContentType(true)
                .method(Connection.Method.POST)
                .execute();
    }

    public ZSResponse queryScore() throws IOException {
        Map<String, String> data = new HashMap<String, String>();
        data.put("pageNumber","1");
        data.put("pageSize","10");
        data.put("querySetting","[{\"name\":\"XNXQDM\",\"caption\":\"学年学期\",\"linkOpt\":\"AND\",\"builderList\":\"cbl_String\",\"builder\":\"equal\",\"value\":\"2017-2018-2\",\"value_display\":\"2017-2018 第二学期\"}]");

        Connection.Response resp = post(data, Const.QUERY_SCORE);

        return new ZSResponse(isLoginIndex(resp),resp.body());
    }


}
