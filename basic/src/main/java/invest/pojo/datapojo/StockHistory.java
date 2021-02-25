package invest.pojo.datapojo;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhichao.ni
 * 2021/2/4
 **/
@Entity
@Table(name = "stock_history")
@Data
public class StockHistory implements Serializable {

    private static final long serialVersionUID = 1788292458158196802L;
    @Id
    @CsvBindByName(column = "日期")
    private Date date;

    @CsvBindByName(column = "股票代码")
    private String code;

    @CsvBindByName(column = "名称")
    private String name;

    @CsvBindByName(column = "收盘价")
    @Column(name = "closing_price")
    private Float closingPrice;

    @CsvBindByName(column = "最高价")
    @Column(name = "highest_price")
    private Float highestPrice;

    @CsvBindByName(column = "最低价")
    @Column(name = "lowest_price")
    private Float lowestPrice;

    @CsvBindByName(column = "开盘价")
    @Column(name = "opening_price")
    private Float openingPrice;

    @CsvBindByName(column = "涨跌额")
    @Column(name = "rise_number")
    private Float riseNumber;

    @CsvBindByName(column = "涨跌幅")
    @Column(name = "rise_range")
    private Float riseRange;

    @CsvBindByName(column = "成交量")
    private Float volume;

    @CsvBindByName(column = "成交金额")
    private Float amount;
}
