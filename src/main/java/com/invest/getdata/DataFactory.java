package com.invest.getdata;

public class DataFactory {
    public Data getData(String data) {
        if (data == null) {
            return null;
        }
        if (data.equalsIgnoreCase("stock")) {
            return new DebtData();
        }
        if (data.equalsIgnoreCase("debt")){
            return new StockData();
        }
        return null;
    }
}
