import invest.data.DataEnum;
import invest.data.DataFactory;
import invest.pojo.datapojo.StockHistory;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class test {
    @Test
    public void t() throws IOException {
        DataFactory dataFactory = new DataFactory("600519");
        List<StockHistory> stockHistories= (List<StockHistory>) dataFactory.getData(DataEnum.STOCK_HISTORY);
        System.out.println(stockHistories);
    }
}
