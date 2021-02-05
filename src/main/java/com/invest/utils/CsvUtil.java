package com.invest.utils;

import com.invest.pojo.StockHistory;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author zhichao.ni
 * 2021/2/5
 **/
public class CsvUtil {
	public static <T> List<T> getCsvData(InputStream in, Class<StockHistory> clazz) throws UnsupportedEncodingException {


		HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
		strategy.setType((Class<? extends T>) clazz);
		Reader reader = new InputStreamReader(in, "gbk");
		CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
				.withSeparator(',')
				.withQuoteChar('\'')
				.withQuoteChar('\"')
				.withMappingStrategy(strategy).build();
		return csvToBean.parse();
	}
}
