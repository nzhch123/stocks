package com.invest;

import com.invest.strategy.Strategy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MailedRecord {

    public Map<Class<? extends Strategy>, Date> getStrategyDateMap() {
        return strategyDateMap;
    }

    public void setStrategyDateMap(Map<Class<? extends Strategy>, Date> strategyDateMap) {
        this.strategyDateMap = strategyDateMap;
    }

    Map<Class<?extends Strategy>, Date> strategyDateMap=new HashMap<>();


}
