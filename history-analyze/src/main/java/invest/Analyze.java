package invest;

import invest.data.DataEnum;
import invest.data.DataFactory;
import invest.pojo.datapojo.Stock;
import invest.pojo.datapojo.StockHistory;
import invest.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.IOException;
import java.util.List;

public class Analyze {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DataFactory getList = new DataFactory();
        List<Stock> stockList = (List<Stock>) getList.getData(DataEnum.STOCK_REALTIME);
        EntityManager em=null;
        //3.获取事务对象，开启事务
        for (Stock s :
                stockList) {
            DataFactory stockHistory = new DataFactory(s.getSymbol());
            Object object=stockHistory.getData(DataEnum.STOCK_HISTORY);
            List<StockHistory> stockHistoryCsvList = null;
            if (object != null) {
                stockHistoryCsvList = (List<StockHistory>) object;
            }
            if (stockHistoryCsvList != null) {
                for (StockHistory n: stockHistoryCsvList
                     ) {
                    em = JPAUtil.getEntityManager();
                    EntityTransaction tx = em.getTransaction(); //获取事务对象
                    tx.begin();//开启事务
                    em.persist(n); //保存操作
                    tx.commit();
                }
            }

        }
        //保存
        //5.提交事务
        //6.释放资源
        em.close();

    }

}
