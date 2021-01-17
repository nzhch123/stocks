package com.invest.strategy.impl;

import com.invest.getdata.Data;
import com.invest.getdata.DataFactory;
import com.invest.pojo.Mail;
import org.springframework.stereotype.Component;

//该策略为转债正股快速上涨，转债涨幅没有跟上的时候，快速进行买入
@Component
public class RapidRise extends AbstractStrategy{




    @Override
    protected void setData(Data data) {

    }

    @Override
    protected void setMail(Mail mail) {
        super.mail = mail;
    }

    @Override
    protected void setInpireMailDays(Integer inpireMailDays) {
        super.inpireMailDays=inpireMailDays;
    }

    @Override
    public void analyzeStrategy() {
        DataFactory dataFactory=new DataFactory();
        Data data=dataFactory.getData("stock");
        super.setData(data);
    }

    @Override
    public Mail sendMessage() {

        return null;
    }
}
