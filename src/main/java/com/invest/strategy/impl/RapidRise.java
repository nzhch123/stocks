package com.invest.strategy.impl;

import com.invest.getdata.Data;
import com.invest.getdata.DataFactory;
import com.invest.pojo.Debt;
import com.invest.pojo.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

//该策略为转债正股快速上涨，转债涨幅没有跟上的时候，快速进行买入
@Component
public class RapidRise extends AbstractStrategy{
    @Autowired
    DataFactory dataFactory;

    @Override
    protected void setInpireMailDays() {
        this.inpireMailDays=3;
    }

    @Override
    protected void setMail() {
        mail.setSubject("转债正股快速上涨");
        mail.setContent("债正股快速上涨，转债涨幅没有跟上的时候，快速进行买入");

    }
    @Override
    public boolean analyzeStrategy() {
        Boolean flag=false;
        Data debtData=dataFactory.getData("debt");
        List<Debt> debtList= (List<Debt>) debtData.getData();
        for (Debt debt :
                debtList) {
            Float stockIncrease = StringtoFloat(debt.getSincreaseRt());
            Float debtIncrease = StringtoFloat(debt.getIncreaseRt());
            if (stockIncrease > 5.0 && (stockIncrease - debtIncrease > 3.0)) {
                this.setToSendTarget(debt.getBondNm());
                flag=true;
            }
        }
        return flag;
    }
}
