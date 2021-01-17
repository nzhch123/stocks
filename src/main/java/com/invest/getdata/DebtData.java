package com.invest.getdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.invest.pojo.Debts;
import com.invest.utils.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DebtData implements Data<List<Debts>> {
    public static final String url="https://www.jisilu.cn/data/cbnew/cb_list/";
    @Override
    public List<Debts> getData() {
        Map<String, String> mapParam = new HashMap<String, String>();
        String result = HttpRequest.sendPost(url, mapParam);
        JSONObject jb = JSONObject.parseObject(result);
        JSONArray jsonArray = jb.getJSONArray("rows");
        List<Debts> debtsList=new ArrayList<Debts>();
        for (int i = 0; i <jsonArray.size() ; i++) {
            JSONObject jsonObject=(JSONObject)jsonArray.getJSONObject(i).get("cell");
            Debts debt = JSON.toJavaObject(jsonObject,Debts.class);
            debtsList.add(debt);
        }
        return debtsList;
    }
}
