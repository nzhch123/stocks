package invest.data;

import invest.data.getdata.*;
import invest.exception.BaseException;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DataFactory {

    String code;
    Date startTime;
    Date endTime;

    public DataFactory() {

    }
    public DataFactory(String code) {
        this.code =convertCode(code) ;
    }
    public DataFactory(String code, Date startTime, Date endTime) {
        this.code = convertCode(code) ;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DataFactory(String code, Date endTime) {
        this.code = convertCode(code) ;
        this.endTime = endTime;
    }

    public List<? extends Object> getData(DataEnum data) throws IOException {
        switch (data) {
            case CONVERTABLE_BOND_REALTIME:
                return new ConvertibleBondRealTimeData().getData();
            case STOCK_REALTIME:
                return new StockRealTimeData().getData();
            case NATIONAL_DEBT_REALTIME:
                return new NationlDebtRealTimeData().getData();
            case CLODE_END_REALTIME:
                return new CloseEndFundRealTimeData().getData();
            case STOCK_HISTORY:
                if (code == null) {
                    throw new BaseException("请输入股票代码");
                }
                if (startTime == null) {
                    if (endTime == null) {
                        return new StockHistoryData(code).getData();
                    } else {
                        return new StockHistoryData(code, endTime).getData();
                    }
                } else {
                    return new StockHistoryData(code, startTime, endTime).getData();
                }
            case CORPORATE_DEBT_REALTIME:
                return new CorporateRealTimeData().getData();
            default:
                throw new BaseException("Enum not exist");
        }

    }
//去掉股票代码中可能包含的字母
    private String convertCode(String code) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(code);
        return m.replaceAll("").trim();
    }
}
