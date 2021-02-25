package invest.data.getdata;

import invest.data.Data;
import invest.pojo.csv.StockHistoryCsv;
import invest.pojo.datapojo.StockHistory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static invest.utils.CodeConverterUtil.convertCode;
import static invest.utils.CsvUtil.getCsvData;

/**
 * @author zhichao.ni
 * 2021/2/5
 **/
//获取股票历史信息
public class StockHistoryData implements Data {


    String code="";
    String startTime="";
    String endTime="";
    // String url = "http://quotes.money.163.com/service/chddata.html?code=0000001&start=19901219&end=20150911&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;VOTURNOVER;VATURNOVER";
    private final String pre = "http://quotes.money.163.com/service/chddata.html?code=";
    private final String after = "&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;VOTURNOVER;VATURNOVER";
    private final String p1 = "&start=";
    private final String p2 = "&end=";

    public StockHistoryData(String code) {
        if (code.startsWith("6")) {
            this.code = "0" + code;
        } else {
            this.code = "1" + code;
        }

    }

    public StockHistoryData(String code, Date endTime) {
        if (code.startsWith("6")) {
            this.code = "0" + code;
        } else {
            this.code = "1" + code;
        }
        SimpleDateFormat sdfs = new SimpleDateFormat("yyyyMMdd");
        this.startTime = sdfs.format(startTime);
        this.endTime = sdfs.format(endTime);
    }
    public StockHistoryData(String code, Date startTime, Date endTime) {
        if (code.startsWith("6")) {
            this.code = "0" + code;
        } else {
            this.code = "1" + code;
        }
        SimpleDateFormat sdfs = new SimpleDateFormat("yyyyMMdd");
        this.startTime = sdfs.format(startTime);
        this.endTime = sdfs.format(endTime);
    }

    @Override
    public List<StockHistory> getData() throws IOException {
        String url = pre + code + p1 + startTime + p2 + after;
        HttpHeaders headers = new HttpHeaders();
        InputStream inputStream = null;
        List<StockHistory> list2 = new ArrayList<>();
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
            StockHistoryCsv stockHistoryCsv = null;
            inputStream = new ByteArrayInputStream(result);
            Reader reader = null;
            List<StockHistoryCsv> list1 = getCsvData(inputStream, StockHistoryCsv.class);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (StockHistoryCsv s :
                    list1) {
                StockHistory stockHistory = new StockHistory();
                stockHistory.setCode(convertCode(s.getCode()));
                stockHistory.setAmount(new Float(s.getAmount()));
                stockHistory.setClosingPrice(new Float(s.getClosingPrice()));
                stockHistory.setDate(sdf.parse(s.getDate()));
                stockHistory.setHighestPrice(new Float(s.getHighestPrice()));
                stockHistory.setLowestPrice(new Float(s.getLowestPrice()));
                stockHistory.setName(s.getName());
                stockHistory.setOpeningPrice(new Float(s.getOpeningPrice()));
                stockHistory.setRiseNumber(new Float(s.getRiseNumber()));
                stockHistory.setRiseRange(new Float(s.getRiseRange()));
                stockHistory.setVolume(new Float(s.getVolume()));
                list2.add(stockHistory);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            return list2;
        }
    }
}
