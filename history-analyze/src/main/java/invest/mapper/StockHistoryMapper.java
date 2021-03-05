package invest.mapper;

import invest.model.StockHistoryModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockHistoryMapper {
    int insertStockHistory( StockHistoryModel stockHistoryModel);
}
