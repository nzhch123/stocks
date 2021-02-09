package com.invest.data;

import com.invest.data.getdata.*;
import com.invest.exception.BaseException;

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
            case NATIONAL_DEBT_REALTIME:
                return new NationlDebtRealTimeData();
            case CLODE_END_REALTIME:
                return new CloseEndFundRealTimeData();
            case STOCK_HISTORY:
                return new StockHistoryData(code, startTime, endTime);
            case CORPORATE_DEBT_REALTIME:
                return new CorporateRealTimeData();
            default:
                throw new BaseException("Enum not exist");
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
