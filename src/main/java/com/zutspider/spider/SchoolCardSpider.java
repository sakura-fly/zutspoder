package com.zutspider.spider;


import com.zutspider.util.Const;
import org.jsoup.Connection;

import java.io.IOException;
import java.util.HashMap;

/**
 * 一卡通的爬虫
 * 单例模式
 */
public class SchoolCardSpider {


    private static class instance {
        private static final SchoolCardSpider instance = new SchoolCardSpider();
    }


    public static SchoolCardSpider getInstance() {
        return instance.instance;
    }


    private SchoolCardSpider() {
    }


    // 要穿的参数
    private String __EVENTTARGET = null;
    private String __EVENTARGUMENT = null;
    private String __VIEWSTATE = null;
    private String __EVENTVALIDATION = null;

    Spider spider = Spider.getInstance();


    /**
     * 检查蚕食是否有空的
     * @return true是有空的
     */
    public boolean checkParm(){
        return __EVENTARGUMENT == null || __EVENTTARGET == null || __VIEWSTATE == null || __EVENTVALIDATION == null;
    }


    /**
     * 存款信息查询
     */
    public void queryIntoMoney() throws IOException {
        Connection.Response resp = spider.send(new HashMap<String, String>(), Const.QUERY_CARD_INTO_MONEY, Connection.Method.POST);
        System.out.println(resp);
    }





    public String get__EVENTTARGET() {
        return __EVENTTARGET;
    }

    public void set__EVENTTARGET(String __EVENTTARGET) {
        this.__EVENTTARGET = __EVENTTARGET;
    }

    public String get__EVENTARGUMENT() {
        return __EVENTARGUMENT;
    }

    public void set__EVENTARGUMENT(String __EVENTARGUMENT) {
        this.__EVENTARGUMENT = __EVENTARGUMENT;
    }

    public String get__VIEWSTATE() {
        return __VIEWSTATE;
    }

    public void set__VIEWSTATE(String __VIEWSTATE) {
        this.__VIEWSTATE = __VIEWSTATE;
    }

    public String get__EVENTVALIDATION() {
        return __EVENTVALIDATION;
    }

    public void set__EVENTVALIDATION(String __EVENTVALIDATION) {
        this.__EVENTVALIDATION = __EVENTVALIDATION;
    }
}
