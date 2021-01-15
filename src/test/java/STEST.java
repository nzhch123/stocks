
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.invest.pojo.Stocks;
import com.invest.utils.GetMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;


public class STEST {
    public static final String url="https://xueqiu.com/service/v5/stock/screener/quote/list?page=1&size=5000&order=desc&orderby=percent&order_by=percent&market=CN&type=sh_sz";
    @Value("${test}")
    private String test;

    @Test
    public void getData() {
        System.out.println(test);
    }

}