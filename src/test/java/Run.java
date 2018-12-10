import com.zutspider.model.GradeQueryUtil;
import com.zutspider.model.Page;
import com.zutspider.model.ZSResponse;
import com.zutspider.spider.Spider;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Run {


    @Test
    public void login() throws IOException {
        Spider spider = Spider.getInstance();
        ZSResponse r = spider.login("201560140336", "wzl167777");
        System.out.println(r.getCode());
    }

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
        qu.setKcm("数")
                .addPage2Map(new Page("1","10"))
                .setXn("2017-2018-1")
                .buildQuery();

        ZSResponse r = spider.queryScore(qu.getQuery());
        System.out.println(r.getText());
    }

    @Test
    public void pr() throws UnsupportedEncodingException {
        String uq = "querySetting=%5B%7B%22name%22%3A%22XNXQDM%22%2C%22caption%22%3A%22%E5%AD%A6%E5%B9%B4%E5%AD%A6%E6%9C%9F%22%2C%22linkOpt%22%3A%22AND%22%2C%22builderList%22%3A%22cbl_String%22%2C%22builder%22%3A%22equal%22%2C%22value%22%3A%222017-2018-2%22%2C%22value_display%22%3A%222017-2018+%E7%AC%AC%E4%BA%8C%E5%AD%A6%E6%9C%9F%22%7D%5D&pageSize=10&pageNumber=1";
        System.out.println(URLDecoder.decode(uq, "utf-8"));
    }
}
