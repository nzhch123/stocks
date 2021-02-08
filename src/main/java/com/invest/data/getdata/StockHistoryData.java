package com.invest.data.getdata;

import com.invest.data.Data;
import com.invest.pojo.StockHistory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.invest.utils.CsvUtil.getCsvData;

/**
 * @author zhichao.ni
 * 2021/2/5
 **/
//获取股票历史信息
public class StockHistoryData implements Data {


    String code;
    String startTime;
    String endTime;
    // String url = "http://quotes.money.163.com/service/chddata.html?code=0000001&start=19901219&end=20150911&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;VOTURNOVER;VATURNOVER";
    private final String pre = "http://quotes.money.163.com/service/chddata.html?code=";
    private final String after = "&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;VOTURNOVER;VATURNOVER";
    private final String p1 = "&start=";
    private final String p2 = "&end=";

    public StockHistoryData(String code, Date startTime, Date endTime) {
        this.code = code;
        SimpleDateFormat sdfs = new SimpleDateFormat("yyyyMMdd");
        this.startTime = sdfs.format(startTime);
        this.endTime = sdfs.format(endTime);
    }

    @Override
    public List<StockHistory> getData() throws IOException {
        String url = pre + code + p1 + startTime + p2 + after;
        HttpHeaders headers = new HttpHeaders();
        InputStream inputStream = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            List list = new ArrayList<>();
            headers.setAccept(list);
            ResponseEntity<byte[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<byte[]>(headers),
                    byte[].class);

            byte[] result = response.getBody();
            StockHistory stockHistory = null;
            inputStream = new ByteArrayInputStream(result);
            Reader reader = null;
            List<StockHistory> list1 = getCsvData(inputStream, StockHistory.class);
            return list1;
        } finally {
            inputStream.close();
        }
    }
}
