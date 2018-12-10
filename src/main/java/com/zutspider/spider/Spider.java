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
        System.out.println("登录中");
        // 获取Response
        Connection.Response r = Jsoup.connect(Const.LOGIN + "?service=" + Const.SERVICE_HALL).execute();

        // 合并cookies
        cookies.putAll(r.cookies());

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
        Connection.Response loginResponse = post(userData, Const.LOGIN);



        System.out.println("登录结束");

        return isLoginIndex(loginResponse);


    }

    /**
     * 是否登录
     *
     * @return 1登录，-1未登录
     */
    private ZSResponse isLoginIndex(Connection.Response response) {
        ZSResponse r = new ZSResponse();
        // 跳转到登录页说明没有登录
        if (response.url().toString().startsWith(Const.LOGIN)) {
            r.setCode(-1);
        } else {
            r.setCode(1);
            r.setText(response.body());
        }
        return r;
    }

    private Connection.Response post(Map<String, String> data, String url) throws IOException {
        // System.out.println("---------before-------------");
        // Set<String> keys = cookies.keySet();
        // for (String k : keys) {
        //     System.out.println(k + "===" + cookies.get(k));
        // }
        // System.out.println("---------before-------------");



        Connection.Response resp = Jsoup.connect(url)
                .method(Connection.Method.POST)
                .cookies(cookies)  // 设置cookies
                .headers(Const.dHeaders)  // 设置消息头
                .data(data)  // post信息
                .ignoreContentType(true)
                .execute();



        // System.out.println("++++++++++++++++++++++");
        // System.out.println(resp.method());
        // System.out.println(resp.statusMessage());
        // System.out.println(resp.url());
        // System.out.println("++++++++++++++++++++++");


        // 合并cookies
        cookies.putAll(resp.cookies());


        // System.out.println("----------after------------");
        // Set<String> keys2 = cookies.keySet();
        // for (String k : keys2) {
        //     System.out.println(k + "===" + cookies.get(k));
        // }
        // System.out.println("----------after------------");
        return resp;
    }

    public ZSResponse queryScore(Map<String, String> data) throws IOException {
        System.out.println("查询成绩");
        // Map<String, String> data = new HashMap<String, String>();
        // data.put("pageNumber", "1");
        // data.put("pageSize", "10");
        // data.put("querySetting",
        //         // " [\n" +
        //         // "    [\n" +
        //         // "        {\n" +
        //         // "            \"name\": \"KCM\",\n" +
        //         // "            \"value\": \"模式\",\n" +
        //         // "        }\n" +
        //         // "    ],\n" +
        //         "    {\n" +
        //         "        \"name\": \"XNXQDM\",\n" +
        //         "        \"linkOpt\": \"AND\"," +
        //         "        \"value\": \"2017-2018-2\",\n" +
        //         "    }\n" +
        //         "]");
        // data.put("querySetting", "[{\"name\":\"XNXQDM\",\"linkOpt\":\"AND\",\"builderList\":\"cbl_String\",\"builder\":\"equal\",\"value\":\"2017-2018-2\"}]");

        Connection.Response resp = post(data, Const.QUERY_SCORE);
        ZSResponse logResp;
        if ((logResp = isLoginIndex(resp)).getCode() == -1){
            return logResp;
        }
        if (resp.method() == Connection.Method.GET){
            resp = post(data, Const.QUERY_SCORE);
        }

        System.out.println("查询成绩结束");
        return isLoginIndex(resp);
    }


}
