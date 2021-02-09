package invest.strategy.impl;

import invest.data.DataEnum;
import invest.data.DataFactory;
import invest.pojo.datapojo.ConvertibleBond;
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
        List<ConvertibleBond> convertibleBondList = (List<ConvertibleBond>) dataFactory.getData(DataEnum.CONVERTABLE_BOND_REALTIME);
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
