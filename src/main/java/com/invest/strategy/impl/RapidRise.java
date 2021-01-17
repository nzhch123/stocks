package com.invest.strategy.impl;

import com.invest.pojo.Mail;
import org.springframework.stereotype.Component;

//该策略为转债正股快速上涨，转债涨幅没有跟上的时候，快速进行买入
@Component
public class RapidRise extends AbstractStrategy{

    @Override
    protected void toSendSubject() {

    }

    @Override
    public void analyzeStrategy() {

    }

    @Override
    public Mail sendMessage() {

        return null;
    }
}
