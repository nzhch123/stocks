package invest.strategy.impl;

import invest.data.DataEnum;
import invest.data.DataFactory;
import invest.pojo.datapojo.Stock;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Component
public class TestStrategy extends AbstractStrategy{
    DataFactory dataFactory = new DataFactory();
    //邮件内容，必填
    @Override
    protected void setMail() {
        mail.setSubject("测试");
        mail.setContent("测试");

    }

    @Override
    public boolean analyzeStrategy() throws ParseException, IOException {
        Boolean flag=false;
        //getData 可以是 "debt"  或者 "stock"  有新的数据源可以在工厂类里添加
        List<Stock> stocks= (List<Stock>) dataFactory.getData(DataEnum.STOCK_REALTIME);
        for (Stock stock :
                stocks) {
            Double per=stock.getPercent();
            if (per > 0.1) {
                this.setToSendTarget(stock.getName());
                flag = true;
            }
        }
        return flag;
    }
}
