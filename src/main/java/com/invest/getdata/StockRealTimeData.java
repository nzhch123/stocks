package com.invest.getdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.invest.pojo.Stock;
import com.invest.utils.HttpRequest;

import java.util.ArrayList;
import java.util.List;

public class StockRealTimeData implements Data<List<Stock>> {
    final String url="https://xueqiu.com/service/v5/stock/screener/quote/list?page=1&size=5000&order=desc&orderby=percent&order_by=percent&market=CN&type=sh_sz";
    @Override
    public List<Stock> getData() {
        String result = HttpRequest.sendGet(url, null);
        JSONObject jb = JSONObject.parseObject(result);
        JSONObject data=jb.getJSONObject("data");
        JSONArray jsonArray = data.getJSONArray("list");
        List<Stock> stocksList=new ArrayList<Stock>();
        for (int i = 0; i <jsonArray.size() ; i++) {
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            Stock stock = JSON.toJavaObject(jsonObject, Stock.class);
            stocksList.add(stock);
        }
        return stocksList;
    }

}
