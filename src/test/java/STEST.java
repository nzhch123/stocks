import com.invest.pojo.Mail;
import com.invest.pojo.datapojo.StockHistory;
import com.invest.utils.HttpRequest;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    Mail mail;
    @Test
    public void test()  {

    }

}