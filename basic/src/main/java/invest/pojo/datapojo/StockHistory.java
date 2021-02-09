package invest.pojo.datapojo;

import com.opencsv.bean.CsvBindByName;

/**
 * @author zhichao.ni
 * 2021/2/4
 **/
public class StockHistory {
	@CsvBindByName(column = "日期")
	private String date;

	@CsvBindByName(column = "股票代码")
	private String code;

	@CsvBindByName(column = "名称")
	private String name;

	@CsvBindByName(column = "收盘价")
	private String closingPrice;

	@CsvBindByName(column = "最高价")
	private String highestPrice;

	@CsvBindByName(column = "最低价")
	private String lowestPrice;

	@CsvBindByName(column = "开盘价")
	private String openingPrice;

	@CsvBindByName(column = "涨跌额")
	private String riseNumber;

	@CsvBindByName(column = "涨跌幅")
	private String riseRange;

	@CsvBindByName(column = "成交量")
	private String volume;

	@CsvBindByName(column = "成交金额")
	private String amount;
}
