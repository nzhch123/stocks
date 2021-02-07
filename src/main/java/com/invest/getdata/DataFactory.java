package com.invest.getdata;

import com.invest.exception.EnumNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.regex.Pattern;


public class DataFactory {

    String code;
    Date startTime;
    Date endTime;
    // String url = "http://quotes.money.163.com/service/chddata.html?code=0000001&start=19901219&end=20150911&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;VOTURNOVER;VATURNOVER";
    public DataFactory(String code, Date startTime, Date endTime) {
        if (startWithChar(code)) {
            code = code.replaceAll("[a-zA-Z]","" );
        }
        this.code = code;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DataFactory() {

    }

    public Data getData(DataRealTimeEnum data) {
        switch (data) {
            case DEBT_REALTIME:
                return new DebtRealTimeData();
            case STOCK_REALTIME:
                return new StockRealTimeData();
            case STOCK_HISTORY:
                return new StockHistoryData(code, startTime, endTime);
            default:
                throw new EnumNotFoundException();
        }

    }
    private static boolean startWithChar(String s) {
        if (s != null && s.length() > 0) {
            String start = s.trim().substring(0, 1);
            Pattern pattern = Pattern.compile("^[A-Za-z]+$");
            return pattern.matcher(start).matches();
        } else {
            return false;
        }
    }
}
