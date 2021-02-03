package com.invest.strategy.impl;

import com.invest.pojo.Mail;
import com.invest.strategy.Strategy;
import com.invest.strategy.MailedRecord;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractStrategy implements Strategy {
    //要发送的邮件
    protected Mail mail;
    //要发送邮件内容的标的
    protected Set<String> toSendTarget;
    //相同标的的邮件间隔时间,防止之前发过邮件了，短时间重复发送
    protected Integer inpireMailDays;

    public Mail getMail() {
        return mail;
    }

    abstract protected void setMail();

    public Set<String> getToSendTarget() {
        return toSendTarget;
    }

    protected void setToSendTarget(String data) throws ParseException {
        Map<String, Date> mapRecord=MailedRecord.classMap.get(this.getClass());
        if (mapRecord.containsKey(data)) {
            if (getDayDiffer(new Date(), mapRecord.get(data)) > inpireMailDays) {
                mapRecord.put(data,new Date());
                this.toSendTarget.add(data);
            }
        }


    }

    protected void setInpireMailDays() {
        this.inpireMailDays = 5;
    }


    protected static final Float StringtoFloat(String s) {
        int l = s.length();
        Float n = Float.parseFloat(s.substring(0, l - 1));
        return n;
    }

    @Override
    public boolean setContext() throws ParseException {
        if (analyzeStrategy()) {
            toSendTarget=new HashSet<>();
            setMail();
            String toSendTarget = getToSendTarget().stream().collect(Collectors.joining("/n", "/n", "/n"));
            String content = mail.getContent() + "/n" + toSendTarget;
            mail.setContent(content);
            return true;

        }
        return false;
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
