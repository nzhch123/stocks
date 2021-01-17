package com.invest.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MailedRecord {
    Map<Class, Date> recordMailed=new HashMap<>();

    public Map<Class, Date> getRecordMailed() {
        return recordMailed;
    }

    public void setRecordMailed(Map<Class, Date> recordMailed) {
        this.recordMailed = recordMailed;
    }
}
