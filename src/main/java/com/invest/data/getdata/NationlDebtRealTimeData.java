package com.invest.data.getdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.invest.data.Data;
import com.invest.pojo.NationalBond;
import com.invest.utils.HttpRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//获取国债实时信息
public class NationlDebtRealTimeData implements Data<List<NationalBond>> {
    private final String url = "https://xueqiu.com/service/v5/stock/screener/quote/list?page=1&size=2000&order=desc&orderby=percent&order_by=percent&exchange=CN&market=CN&industry=%E4%BC%81%E5%80%BA&type=corp&_=1612755043412";

    @Override
    public List<NationalBond> getData() throws IOException {
        String result = HttpRequest.sendGet(url, null);
        JSONObject jb = JSONObject.parseObject(result);
        JSONObject data = jb.getJSONObject("data");
        JSONArray jsonArray = data.getJSONArray("list");
        List<NationalBond> bondList = new ArrayList<NationalBond>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            NationalBond stock = JSON.toJavaObject(jsonObject, NationalBond.class);
            bondList.add(stock);
        }
        return bondList;
    }

}
