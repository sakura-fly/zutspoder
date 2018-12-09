import com.zutspider.spider.Spider;
import com.zutspider.util.Const;
import org.junit.Test;

import java.io.IOException;

public class Run {


    @Test
    public void login() throws IOException {
        Spider spider = Spider.getInstance();
        spider.login("201560140336", "wzl1677773");
    }
}
