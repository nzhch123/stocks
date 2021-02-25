import invest.data.DataEnum;
import invest.data.DataFactory;
import invest.pojo.csv.StockHistoryCsv;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class test {
    @Test
    public void t() throws IOException {
        DataFactory dataFactory = new DataFactory("600519");
        List<StockHistoryCsv> stockHistories= (List<StockHistoryCsv>) dataFactory.getData(DataEnum.STOCK_HISTORY);

    }

    public List<Double> getMoveEverage() {

        return null;
    }
}
