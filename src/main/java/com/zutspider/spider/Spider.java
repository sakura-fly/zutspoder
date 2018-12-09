package com.zutspider.spider;


import com.zutspider.util.Const;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
     *
     * 登录
     *
     * @param userName 用户名
     * @param pwd      密码
     */
    /**
     * 登录
     *
     * @param userName 用户名
     * @param pwd      密码
     * @return 1成功，-1失败
     * @throws IOException
     */
    public int login(String userName, String pwd) throws IOException {
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
        Connection.Response loginResponse = Jsoup.connect(Const.LOGIN)
                .cookies(cookies)  // 设置cookies
                .headers(Const.dHeaders)  // 设置消息头
                .data(userData)  // 登录信息
                .ignoreContentType(true)
                .method(Connection.Method.POST)
                .execute();

        System.out.println(loginResponse.url());
        if (loginResponse.url().equals("https://authserver.zut.edu.cn/authserver/login")) {
            return -1;
        } else {
            return 1;
        }

    }


}
