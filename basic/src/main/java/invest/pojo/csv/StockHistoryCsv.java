package invest.pojo.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author zhichao.ni
 * 2021/2/4
 **/
@Data
public class StockHistoryCsv implements Serializable {
	private static final long serialVersionUID = 3541076934392205771L;
	@Id
	@CsvBindByName(column = "日期")
	private String  date;

	@CsvBindByName(column = "股票代码")
	private String code;

	@CsvBindByName(column = "名称")
	private String name;

	@CsvBindByName(column = "收盘价")
	@Column(name = "closing_price")
	private String closingPrice;

	@CsvBindByName(column = "最高价")
	@Column(name = "highest_price")
	private String highestPrice;

	@CsvBindByName(column = "最低价")
	@Column(name = "lowest_price")
	private String lowestPrice;

	@CsvBindByName(column = "开盘价")
	@Column(name = "opening_price")
	private String openingPrice;

	@CsvBindByName(column = "涨跌额")
	@Column(name = "rise_number")
	private String riseNumber;

	@CsvBindByName(column = "涨跌幅")
	@Column(name = "rise_range")
	private String riseRange;

	@CsvBindByName(column = "成交量")
	private String volume;

	@CsvBindByName(column = "成交金额")
	private String amount;
}
