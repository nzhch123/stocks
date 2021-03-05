package invest;

import invest.data.DataEnum;
import invest.data.DataFactory;
import invest.mapper.StockHistoryMapper;
import invest.model.StockHistoryModel;
import invest.pojo.datapojo.Stock;
import invest.pojo.datapojo.StockHistory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
@Order(value = 1)
@Component
public class Analyze implements CommandLineRunner {
    @Autowired
    StockHistoryMapper stockHistoryMapper;
    @Override
    public void run(String... args) throws Exception {
        DataFactory getList = new DataFactory();
        List<Stock> stockList = (List<Stock>) getList.getData(DataEnum.STOCK_REALTIME);

        //3.获取事务对象，开启事务
        for (Stock s :
                stockList) {
            DataFactory stockHistory = new DataFactory(s.getSymbol());
            Object object = stockHistory.getData(DataEnum.STOCK_HISTORY);
            List<StockHistory> stockHistoryCsvList = null;
            if (object != null) {
                stockHistoryCsvList = (List<StockHistory>) object;
            }
            if (stockHistoryCsvList != null) {
                for (StockHistory n : stockHistoryCsvList
                ) {
                    StockHistoryModel stockHistoryModel=new StockHistoryModel();
                    BeanUtils.copyProperties(n,stockHistoryModel);
                    insertData(stockHistoryModel);
                }
            }

        }
    }
    private Boolean insertData(StockHistoryModel stockHistoryModel) throws InterruptedException {
        for (int i = 0; i <100 ; i++) {
            try {
                stockHistoryMapper.insertStockHistory(stockHistoryModel);
                return Boolean.TRUE;

            } catch (Exception e) {
                Thread.sleep(6000);
            }
        }
        return false;
    }
}

