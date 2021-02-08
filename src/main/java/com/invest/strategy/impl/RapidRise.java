package com.invest.strategy.impl;

import com.invest.data.Data;
import com.invest.data.DataEnum;
import com.invest.data.DataFactory;
import com.invest.pojo.datapojo.ConvertibleBond;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
//编写参照例子
//该策略为转债正股快速上涨，转债涨幅没有跟上的时候，快速进行买入
@Component
public class RapidRise extends AbstractStrategy{

    DataFactory dataFactory = new DataFactory();
    //邮件内容，必填
    @Override
    protected void setMail() {
        mail.setSubject("转债正股快速上涨");
        mail.setContent("债正股快速上涨，转债涨幅没有跟上的时候，快速进行买入");

    }
    //默认为5，可以自己设置
    @Override
    protected void setInpireMailDays() {
        this.inpireMailDays = 3;
    }
    @Override
    public boolean analyzeStrategy() throws ParseException, IOException {
        Boolean flag=false;
        //getData 可以是 "debt"  或者 "stock"  有新的数据源可以在工厂类里添加
        Data debtData= dataFactory.getData(DataEnum.CONVERTABLE_BOND_REALTIME);
        List<ConvertibleBond> convertibleBondList = (List<ConvertibleBond>) debtData.getData();
        for (ConvertibleBond convertibleBond :
                convertibleBondList) {
            Float stockIncrease = StringtoFloat(convertibleBond.getSincreaseRt());
            Float debtIncrease = StringtoFloat(convertibleBond.getIncreaseRt());
            if (stockIncrease > 5.0 && (stockIncrease - debtIncrease > 3.0)) {
                this.setToSendTarget(convertibleBond.getBondNm());
                flag=true;
            }
        }
        return flag;
    }
}
