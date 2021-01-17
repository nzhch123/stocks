package com.invest.strategy.impl;

import com.invest.getdata.Data;
import com.invest.pojo.Mail;
import com.invest.strategy.Strategy;

import java.util.Set;

public abstract class AbstractStrategy implements Strategy {
    //获取数据
    protected Data data;
    //要发送的邮件
    protected Mail mail;
    //要发送邮件内容的标的
    protected Set<Data> toSendSubject;
    //邮件重复时间
    protected Integer inpireMailDays;

    protected Data getData() {
        return data;
    }

    abstract protected void setData(Data data);

    public Mail getMail() {
        return mail;
    }

    abstract protected void setMail(Mail mail);

    protected Set<Data> getToSendSubject() {
        return toSendSubject;
    }

    protected void setToSendSubject(Set<Data> toSendSubject) {
        this.toSendSubject = toSendSubject;
    }

    public Integer getInpireMailDays() {
        return inpireMailDays;
    }

    abstract protected void setInpireMailDays(Integer inpireMailDays);

    @Override
    abstract public void analyzeStrategy();

    @Override
    abstract public Mail sendMessage();

}
