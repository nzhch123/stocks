package com.invest.strategy.impl;

import com.invest.getdata.Data;
import com.invest.pojo.Mail;
import com.invest.strategy.Strategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public abstract class AbstractStrategy implements Strategy {
    //要发送的邮件
    protected Mail mail;
    //要发送邮件内容的标的
    protected Set<Object> toSendSubject;
    //邮件重复时间
    protected Integer inpireMailDays;

    public Mail getMail() {
        return mail;
    }

    protected void setMail(Mail mail) {
        this.mail = mail;
    }
    protected void setToSendSubject(Object data) {
        this.toSendSubject.add(data);
    }

    protected void setInpireMailDays(Integer inpireMailDays) {
        this.inpireMailDays = inpireMailDays;
    }

    @Override
    abstract public void analyzeStrategy();

    @Override
    abstract public Mail sendMessage();

    protected static final Float StringtoFloat(String s) {
        int l = s.length();
        Float n = Float.parseFloat(s.substring(0, l - 1));
        return n;
    }

    public void setContext() {

    }

    protected static final int getDayDiffer(Date startDate, Date endDate) throws ParseException {
        //判断是否跨年
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String startYear = yearFormat.format(startDate);
        String endYear = yearFormat.format(endDate);
        if (startYear.equals(endYear)) {
            /*  使用Calendar跨年的情况会出现问题    */
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            int startDay = calendar.get(Calendar.DAY_OF_YEAR);
            calendar.setTime(endDate);
            int endDay = calendar.get(Calendar.DAY_OF_YEAR);
            return endDay - startDay;
        } else {
            /*  跨年不会出现问题，需要注意不满24小时情况（2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 0）  */
            //  只格式化日期，消除不满24小时影响
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long startDateTime = dateFormat.parse(dateFormat.format(startDate)).getTime();
            long endDateTime = dateFormat.parse(dateFormat.format(endDate)).getTime();
            return (int) ((endDateTime - startDateTime) / (1000 * 3600 * 24));
        }
    }

}
