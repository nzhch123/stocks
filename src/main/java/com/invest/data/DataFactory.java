package com.invest.data;

import com.invest.data.getdata.ConvertibleBondRealTimeData;
import com.invest.data.getdata.StockHistoryData;
import com.invest.data.getdata.StockRealTimeData;
import com.invest.exception.EnumNotFoundException;

import java.util.Date;
import java.util.regex.Pattern;


public class DataFactory {

    String code;
    Date startTime;
    Date endTime;
    public DataFactory() {

    }
    public DataFactory(String code, Date startTime, Date endTime) {
        if (startWithChar(code)) {
            code = code.replaceAll("[a-zA-Z]","" );
        }
        this.code = code;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public Data getData(DataEnum data) {
        switch (data) {
            case CONVERTABLE_BOND_REALTIME:
                return new ConvertibleBondRealTimeData();
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
