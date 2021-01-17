package com.invest.strategy.impl;

import com.invest.getdata.Data;
import com.invest.pojo.Mail;
import com.invest.strategy.Strategy;

import java.util.List;
import java.util.Set;

public abstract class AbstractStrategy implements Strategy {

    private List<Data> data;

    private Mail mail;

    private Set<Data> toSendSubject;

    abstract protected void toSendSubject();

    @Override
    abstract public void analyzeStrategy();

    @Override
    abstract public Mail sendMessage();

}
