package com.invest.strategy.impl;

import com.invest.pojo.Mail;
import com.invest.strategy.MailedRecord;
import com.invest.strategy.Strategy;
import com.invest.utils.DateUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
            if (DateUtil.getDayDiffer(new Date(), mapRecord.get(data)) > inpireMailDays) {
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

}
