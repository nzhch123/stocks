package com.invest.data.getdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.invest.data.Data;
import com.invest.pojo.CorporateDebt;
import com.invest.pojo.NationalBond;
import com.invest.utils.HttpRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CorporateRealTimeData implements Data<List<CorporateDebt>> {
    private static final String url = "https://xueqiu.com/service/v5/stock/screener/quote/list?page=1&size=10000&order=desc&orderby=percent&order_by=percent&exchange=CN&market=CN&industry=%E4%BC%81%E5%80%BA&type=corp&_=1612790119845";

    @Override
    public  List<CorporateDebt> getData() throws IOException {
        String result = HttpRequest.sendGet(url, null);
        JSONObject jb = JSONObject.parseObject(result);
        JSONObject data = jb.getJSONObject("data");
        JSONArray jsonArray = data.getJSONArray("list");
        List<CorporateDebt> bondList = new ArrayList<CorporateDebt>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            CorporateDebt stock = JSON.toJavaObject(jsonObject, CorporateDebt.class);
            bondList.add(stock);
        }
        return bondList;
    }

}
