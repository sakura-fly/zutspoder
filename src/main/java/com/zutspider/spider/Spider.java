package com.zutspider.spider;


import com.zutspider.model.Page;
import com.zutspider.model.ZSResponse;
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
        // 用户信息
        userData.put("username", userName);
        userData.put("password", pwd);

        // 登录
        Connection.Response loginResponse = send(userData, Const.LOGIN, Connection.Method.POST);


        System.out.println("登录结束");

        return isLoginIndex(loginResponse);


    }

    /**
     * 是否登录
     *
     * @return 1登录，设置内容；-1未登录，不设置内容
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


    /**
     * 发送请求
     *
     * @param data   请求参数
     * @param url    请求地址
     * @param method 请求方法
     * @return
     * @throws IOException
     */
    private Connection.Response send(Map<String, String> data, String url, Connection.Method method) throws IOException {
        // System.out.println("---------before-------------");
        // Set<String> keys = cookies.keySet();
        // for (String k : keys) {
        //     System.out.println(k + "===" + cookies.get(k));
        // }
        // System.out.println("---------before-------------");

        data = data == null ? new HashMap<String, String>() : data;

        Connection.Response resp = Jsoup.connect(url)
                .method(method)
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

    /**
     * 查询成绩
     *
     * @param data 查询信息
     * @return {
     * "datas": {
     * "scoreQueryAction": {
     * "totalSize": 1,      总数
     * "pageNumber": 1,     页码
     * "pageSize": 10,      每页数量
     * "rows": [
     * {
     * "PM": 4.2,                                   学分绩点
     * "CJ": "71",                                  成绩
     * "WID": "201500004438RB7001036 2017-101",     主键
     * "XF": 2,                                     课程学分
     * "KCJD": 2.1,                                 课程绩点
     * "KCXZ": "考试",                              考核方式
     * "XNXQDM": "2017-2018-2",                     学年学期
     * "KCM": "网络安全技术",                       课程名
     * "XDFS": "初修",                              修读方式
     * "KCLB": "必修课/专业课",                     课程类别
     * "KCH": "RB7001036 "                          课程号
     * }
     * ]
     * }
     * },
     * "code": "0"
     * }
     */
    public ZSResponse queryScore(Map<String, String> data) throws IOException {
        System.out.println("查询成绩");
        // 请求查询成绩
        Connection.Response resp = send(data, Const.QUERY_SCORE, Connection.Method.POST);
        ZSResponse logResp;
        // 如果未登录返回结果
        if ((logResp = isLoginIndex(resp)).getCode() == -1) {
            return logResp;
        }
        // 如果跳转到get可能是cookies问题，再来一次
        if (resp.method() == Connection.Method.GET) {
            resp = send(data, Const.QUERY_SCORE, Connection.Method.POST);
        }
        System.out.println("查询成绩结束");
        return isLoginIndex(resp);
    }


    /**
     * 获取学年学期
     * 查成绩的学习学年从这获取
     *
     * @return 学年学期信息
     * 格式如下，显示name，穿参数用id
     * {
     * "datas": {
     * "code": {
     * "rows": [
     * {
     * "name": "2018-2019 第一学期",
     * "id": "2018-2019-1"
     * },
     * {
     * "name": "2017-2018 第二学期",
     * "id": "2017-2018-2"
     * },
     * {
     * "name": "2017-2018 第一学期",
     * "id": "2017-2018-1"
     * },
     * {
     * "name": "2016-2017 第二学期",
     * "id": "2016-2017-2"
     * },
     * {
     * "name": "2016-2017 第一学期",
     * "id": "2016-2017-1"
     * }
     * ]
     * }
     * }
     * }
     */
    public ZSResponse getSchoolYearTerms() throws IOException {
        Connection.Response resp = send(null, Const.SCHOOL_YEAR_TERMS, Connection.Method.GET);
        return isLoginIndex(resp);
    }
    /*


     */

    /**
     * 查课表
     *
     * @param date 查询的日期 格式: yyyy-MM-dd
     * @return 这天所在的周的课表
     * <p>
     * <p>
     * {
     * "amClasses": 4,
     * "weekOfTerm": "12",                                 周次
     * "schoolYearTerm": "2017-2018-1",                    学年
     * "code": 200,
     * "timeTable": [                              课表列表，jsonArray，没有的时候这是空的
     * {
     * "section": "1-2",                           节次
     * "position": "",
     * "date": "2017-11-27",                       日期
     * "weekday": "1",                             星期
     * "classId": "2015191403",
     * "id": "",
     * "name": "计算机组成原理",                   课程名
     * "nameEN": "",
     * "classroom": "西区1号教学楼0509[薛滨]"      教师与教室
     * }
     * ],
     * "date": "2017-11-27",
     * "pmClasses": 4,
     * "eveClasses": 2,
     * "allTeachWeeks": 19,
     * "allTermWeeks": 25
     * }
     */
    public ZSResponse queryCourses(String date) throws IOException {
        System.out.println("查询课表");
        Map<String, String> dateMap = new HashMap<String, String>();
        if (date != null && !date.isEmpty()) {
            dateMap.put("date", date);
        }
        Connection.Response resp = send(dateMap, Const.QUERY_COURSE, Connection.Method.GET);
        System.out.println("查询课表完成");
        return isLoginIndex(resp);
    }


    /**
     * 查询新闻
     * @param column 标签id，没有空字符串
     * @param cti 查询条件，内容或标题,没有空字符串
     * @param page 分页信息，空字符串第0页10条
     * @return
     *
     * {
     *     "qp": {
     *         "aList": [
     *             {
     *                 "sendTimeDesc": "2018年8月31日 09:16",                                  发布时间详情
     *                 "cover": "",
     *                 "attchMentNumber": "",
     *                 "noticeDesc": "",                                                       内容，太长了我给删了，
     *                 "columnName": "校内公告",                                               标签名
     *                 "attchList": null,
     *                 "wid": "c2e3f4441cf0418b96b91a02bb4fe582",                              主键，查看详情的时候用
     *                 "noticeId": null,
     *                 "columnId": null,
     *                 "senderId": null,
     *                 "noticeTitle": "关于发放2018—2019学年第一学期在职人员误餐补助的通知",   标题
     *                 "sendDepartment": "校务公开领导小组办公室",                              发布单位
     *                 "sendPeople": null,
     *                 "createTime": null,
     *                 "sendTime": "2018-08-31 09:16:04",                                       发布时间
     *                 "clickNumber": "1144",                                                   阅读数
     *                 "noticeType": "",
     *                 "downTime": null,
     *                 "noticeContent": "",
     *                 "token": "",
     *                 "viewScope": "",
     *                 "privilegeIsolation": "",
     *                 "belongDept": "",
     *                 "auditorId": "",
     *                 "auditorName": ""
     *             },
     *         ],
     *         "pageNo": 0,
     *         "pageSize": 10,
     *         "param": {                                               调用接口传的参数
     *             "cti": "2018",
     *             "pageNo": "0",
     *             "pageSize": "10",
     *             "column": "1509676580481440"
     *         },
     *         "totalItem": 5                                           总数
     *     },
     *     "list": [                                                    标签列表
     *         {
     *             "uwid": "",
     *             "wid": "ffa616ef75df4e6d8358564bd3484f3f",
     *             "columnId": "1509676580481440",                      标签id，按标签查询的时候穿这个
     *             "columnName": "校内公告",                            标签名
     *             "columnPriority": "2",
     *             "columnDesc": "",
     *             "viewScope": "",
     *             "privilegeIsolation": "0"
     *         },
     *         ]
     * }
     *
     */
    public ZSResponse querynews(String column, String cti, Page page) throws IOException {
        System.out.println("查询新闻");
        // 转换分页为查询新闻格式的map
        Map<String, String> data = page.toNewsPage();

        // 查询标签
        data.put("column",column);
        // 查询内容
        data.put("cti",cti);
        System.out.println(data);

        Connection.Response resp = send(data, Const.QUERY_NEWS, Connection.Method.POST);
        // 如果跳转到get可能是cookies问题，再来一次
        if (resp.method() == Connection.Method.GET) {
            resp = send(data, Const.QUERY_NEWS, Connection.Method.POST);
        }
        System.out.println("查询新闻完成");
        return isLoginIndex(resp);
    }


}
