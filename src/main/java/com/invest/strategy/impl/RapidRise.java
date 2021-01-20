package com.invest.strategy.impl;

import com.invest.getdata.Data;
import com.invest.getdata.DataFactory;
import com.invest.pojo.Debt;
import com.invest.pojo.Mail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

//该策略为转债正股快速上涨，转债涨幅没有跟上的时候，快速进行买入
@Component

public class RapidRise extends AbstractStrategy{

    @Override
    public void analyzeStrategy() {
        DataFactory dataFactory=new DataFactory();
        Data debtData=dataFactory.getData("debt");
        List<Debt> debtList= (List<Debt>) debtData.getData();
        setInpireMailDays(3);
        for (Debt debt :
                debtList) {
            Float stockIncrease = StringtoFloat(debt.getSincreaseRt());
            Float debtIncrease = StringtoFloat(debt.getIncreaseRt());
            if (stockIncrease > 5.0 && (stockIncrease - debtIncrease > 3.0)) {
                this.setToSendSubject(debt);
            }

        }

    }

    @Override
    public Mail sendMessage() {

        return null;
    }
}
