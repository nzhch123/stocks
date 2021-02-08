package com.invest.data.getdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.invest.data.Data;
import com.invest.pojo.ConvertibleBond;
import com.invest.utils.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//获取可转债实时信息
public class ConvertibleBondRealTimeData implements Data<List<ConvertibleBond>> {
    private final String url="https://www.jisilu.cn/data/cbnew/cb_list/";
    @Override
    public List<ConvertibleBond> getData() {
        Map<String, String> mapParam = new HashMap<String, String>();
        String result = HttpRequest.sendPost(url, mapParam);
        JSONObject jb = JSONObject.parseObject(result);
        JSONArray jsonArray = jb.getJSONArray("rows");
        List<ConvertibleBond> convertibleBondList =new ArrayList<ConvertibleBond>();
        for (int i = 0; i <jsonArray.size() ; i++) {
            JSONObject jsonObject=(JSONObject)jsonArray.getJSONObject(i).get("cell");
            ConvertibleBond convertibleBond = JSON.toJavaObject(jsonObject, ConvertibleBond.class);
            convertibleBondList.add(convertibleBond);
        }
        return convertibleBondList;
    }
}
