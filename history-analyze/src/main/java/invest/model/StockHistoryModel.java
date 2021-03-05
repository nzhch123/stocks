package invest.model;

import lombok.Data;

import java.util.Date;
@Data
public class StockHistoryModel {
    private Long id;

    private Date date;

    private String code;

    private String name;

    private Float closingPrice;

    private Float highestPrice;

    private Float lowestPrice;

    private Float openingPrice;

    private Float riseNumber;

    private  Float riseRange;

    private Float volume;

    private Float amount;
}
