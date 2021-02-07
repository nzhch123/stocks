package com.invest.getdata;

import com.invest.pojo.StockHistory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author zhichao.ni
 * 2021/2/5
 **/
public class StockHistoryData implements Data {


    String code;
    String startTime;
    String endTime;
    final String pre = "http://quotes.money.163.com/service/chddata.html?code=0000001&start=19901219&end=20150911";
    final String after = "&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;VOTURNOVER;VATURNOVER";
    final String codeVirable = "&start=";
    public StockHistoryData(String code, Date startTime, Date endTime) {
        this.code = code;
         SimpleDateFormat sdfs = new SimpleDateFormat("yyyyMMdd");
        this.startTime = sdfs.format(startTime);
        this.endTime = sdfs.format(endTime);
   }
    @Override
    public List<StockHistory> getData() {
        return null;
    }
}
