import com.invest.pojo.StockHistory;
import com.invest.utils.HttpRequest;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class STEST {

    @Test
    public void test() throws IOException {
        String tt = HttpRequest.sendGet("http://quotes.money.163.com/service/chddata.html?code=0000001&start=19901219&end=20150911&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;VOTURNOVER;VATURNOVER", null);
        String url = "http://quotes.money.163.com/service/chddata.html?code=0000001&start=19901219&end=20150911&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;VOTURNOVER;VATURNOVER";
        HttpHeaders headers = new HttpHeaders();
        InputStream inputStream = null;
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
       List<StockHistory> list1= getCsvData(inputStream, StockHistory.class);
        Collections.reverse(list1);
            if (inputStream != null) {
                inputStream.close();
            }


    }


	public <T> List<T> getCsvData(InputStream in, Class<StockHistory> clazz) throws UnsupportedEncodingException {


		HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
		strategy.setType((Class<? extends T>) clazz);
        Reader reader = new InputStreamReader(in ,"gbk");
		CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
                .withSeparator(',')
                .withQuoteChar('\'')
                .withQuoteChar('\"')
				.withMappingStrategy(strategy).build();
		return csvToBean.parse();
	}
}