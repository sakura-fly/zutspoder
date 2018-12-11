import com.zutspider.model.GradeQueryUtil;
import com.zutspider.model.Page;
import com.zutspider.model.ZSResponse;
import com.zutspider.spider.Spider;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Run {



    // 测试登录
    @Test
    public void login() throws IOException {
        Spider spider = Spider.getInstance();
        ZSResponse r = spider.login("201560140336", "wzl167777");
        System.out.println(r.getCode());
    }

    // 测试查成绩
    @Test
    public void queryScore() throws IOException {
        Spider spider = Spider.getInstance();
        spider.login("201560140336", "wzl167777");

        // final GradeQuery kcm =new GradeQuery("CJ","83", "include","OR");
        // final GradeQuery xnxqdm =new GradeQuery("XNXQDM", "2017-2018-2", "equal","AND");
        // final List<GradeQuery> l = new ArrayList<GradeQuery>(){
        //     {
        //         add(kcm);
        //     }
        // };
        // List l2 = new ArrayList(){
        //     {
        //         add(l);
        //         add(xnxqdm);
        //     }
        // };
        // JSONArray jsonArray = new JSONArray(l2);
        // System.out.println(jsonArray);
        // Map<String, String> data = new HashMap<String, String>();
        // data.put("pageNumber", "1");
        // data.put("pageSize", "10");
        // data.put("querySetting",jsonArray.toString());


        GradeQueryUtil qu = new GradeQueryUtil();
        qu.setKcm("数")  //  查询课程名
                .addPage2Map(new Page("1", "10")) //  设置分页信息
                .setXn(null)  // 设置学年，不设置或者设置为null，空字符串为不限制学年学期
                .buildQuery();  //  组合查询条件

        ZSResponse r = spider.queryScore(qu.getQuery());
        System.out.println(r.getText());
    }

    @Test
    public void pr() throws UnsupportedEncodingException {
        String uq = "querySetting=%5B%7B%22name%22%3A%22XNXQDM%22%2C%22caption%22%3A%22%E5%AD%A6%E5%B9%B4%E5%AD%A6%E6%9C%9F%22%2C%22linkOpt%22%3A%22AND%22%2C%22builderList%22%3A%22cbl_String%22%2C%22builder%22%3A%22equal%22%2C%22value%22%3A%222017-2018-2%22%2C%22value_display%22%3A%222017-2018+%E7%AC%AC%E4%BA%8C%E5%AD%A6%E6%9C%9F%22%7D%5D&pageSize=10&pageNumber=1";
        System.out.println(URLDecoder.decode(uq, "utf-8"));
    }


    // 测试获取学年学期
    @Test
    public void getyear() throws IOException {
        login();
        Spider spider = Spider.getInstance();
        ZSResponse r = spider.getSchoolYearTerms();
        System.out.println(r);
    }

    // 查成绩
    @Test
    public void querykb() throws IOException {
        login();
        Spider spider = Spider.getInstance();
        ZSResponse r = spider.queryCourses("2017-11-11");
        System.out.println(r);
    }

    // 查新闻
    @Test
    public void queryNews() throws IOException {
        login();
        Spider spider = Spider.getInstance();
        ZSResponse r = spider.querynews("","补助",new Page("",""));
        System.out.println(r);
    }

}
