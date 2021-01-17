package com.invest.getdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.invest.pojo.Stocks;
import com.invest.utils.HttpRequest;

import java.util.ArrayList;
import java.util.List;

public class StockData implements Data<List<Stocks>> {
    public static final String url="https://xueqiu.com/service/v5/stock/screener/quote/list?page=1&size=5000&order=desc&orderby=percent&order_by=percent&market=CN&type=sh_sz";
    @Override
    public List<Stocks> getData() {
        String result = HttpRequest.sendGet(url, null);
        JSONObject jb = JSONObject.parseObject(result);
        JSONObject data=jb.getJSONObject("data");
        JSONArray jsonArray = data.getJSONArray("list");
        List<Stocks> stocksList=new ArrayList<Stocks>();
        for (int i = 0; i <jsonArray.size() ; i++) {
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            Stocks stock = JSON.toJavaObject(jsonObject,Stocks.class);
            stocksList.add(stock);
        }
        return stocksList;
    }

}