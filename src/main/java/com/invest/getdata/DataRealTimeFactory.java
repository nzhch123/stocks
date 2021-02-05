package com.invest.getdata;

import org.springframework.stereotype.Component;

@Component
public class DataRealTimeFactory {
    public Data getData(DataRealTimeEnum data) {
        switch (data) {
            case DEBT:
                return new DebtRealTimeData();
            case STOCK:
                return new StockRealTimeData();
            default:
                return null;
        }

    }
}
