package invest.data.getdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import invest.data.Data;
import invest.pojo.datapojo.CloseEndFund;
import invest.utils.HttpRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloseEndFundRealTimeData implements Data<List<CloseEndFund>> {
    private final String url="https://www.jisilu.cn/data/cf/cf_list/?___jsl=LST___t=1612791895516";
    @Override
    public List<CloseEndFund> getData() {
        Map<String, String> mapParam = new HashMap<String, String>();
        String result = HttpRequest.sendPost(url, mapParam);
        JSONObject jb = JSONObject.parseObject(result);
        JSONArray jsonArray = jb.getJSONArray("rows");
        List<CloseEndFund> closeEndFundList =new ArrayList<CloseEndFund>();
        for (int i = 0; i <jsonArray.size() ; i++) {
            JSONObject jsonObject=(JSONObject)jsonArray.getJSONObject(i).get("cell");
            CloseEndFund convertibleBond = JSON.toJavaObject(jsonObject, CloseEndFund.class);
            closeEndFundList.add(convertibleBond);
        }
        return closeEndFundList;
    }
}
