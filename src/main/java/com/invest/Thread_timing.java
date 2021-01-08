package com.invest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import com.invest.pojo.Debts;
import com.invest.utils.GetPostMessage;
import com.invest.utils.MailUtil;

public class Thread_timing extends Thread {
    //存储发送过的正股涨幅过快的转债和时间，避免两天内重复发送邮件
    private static Map<String, Date> fastCrease = new HashMap<String, Date>();
    //存储已经发送过的溢价率比较低的转债和时间，避免七天之内重复发送
    private static Map<String, Date> prRateRecord = new HashMap<String, Date>();

    static Logger logger = Logger.getLogger(Thread_timing.class);

    private List<Debts> debtsList;


    public void run() {
        logger.info("定时线程已启动");
        while (true) {
            Calendar date = Calendar.getInstance();
            int hour = date.get(Calendar.HOUR_OF_DAY);
            int minute = date.get(Calendar.MINUTE);
            int week = date.get(Calendar.DAY_OF_WEEK) - 1;
            boolean flag = (week > 0 && week < 6) && ((hour == 9 && minute > 30) || (hour < 15 && hour > 9));
            if (flag) {
                debtsList = GetPostMessage.getDebts();
                try {
                    strategy();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(30000);//每隔半分钟检测一次
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //需要编写策略直接在该函数写
    private void strategy() throws Exception {
        Set<String> toSendPrDebt = new HashSet<String>();
        Set<String> toSendFast = new HashSet<>();
        for (Debts debt :
                debtsList) {
            Float debtIncrease = StringtoFloat(debt.getIncreaseRt());
            Float stockIncrease = StringtoFloat(debt.getSincreaseRt());
            Float premiumRate = StringtoFloat(debt.getPremiumRt());
            //溢价率低于百分之十了会进行通知
            if (premiumRate < -10.0) {
                if (prRateRecord.containsKey(debt.getBondId())) {
                    if (getDayDiffer(new Date(), prRateRecord.get(debt.getBondId())) >= 7) {
                        prRateRecord.put(debt.getBondId(), new Date());
                        toSendPrDebt.add(debt.getBondNm());
                    }
                }
            }
            //正股长得过快，转债没有跟上会发邮件进行通知
            if (fastCrease.containsKey(debt.getBondId())) {
                if (getDayDiffer(new Date(), prRateRecord.get(debt.getBondId())) >= 3) {
                    if (stockIncrease > 5.0 && (stockIncrease - debtIncrease > 3.0)) {
                        fastCrease.put(debt.getBondId(), new Date());
                        toSendFast.add(debt.getBondNm());
                    }
                }
            }
        }
        if (toSendPrDebt.size() != 0) {
            String toSendPrDebtStr = toSendPrDebt.stream().collect(Collectors.joining(" \n ", "", ""));
            MailUtil.sendMessage("溢价率低于百分之十了", toSendPrDebtStr + "溢价率低于百分之十了");
        }
        if (toSendFast.size() != 0) {
            String toSendFastStr = toSendFast.stream().collect(Collectors.joining(" \n ", "", ""));
            MailUtil.sendMessage("正股上涨过快", toSendFastStr + "溢价率低于百分之十了");
        }
    }

    private Float StringtoFloat(String s) {
        int l = s.length();
        Float n = Float.parseFloat(s.substring(0, l - 1));
        return n;
    }

    private int getDayDiffer(Date startDate, Date endDate) throws ParseException {
        //判断是否跨年
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String startYear = yearFormat.format(startDate);
        String endYear = yearFormat.format(endDate);
        if (startYear.equals(endYear)) {
            /*  使用Calendar跨年的情况会出现问题    */
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            int startDay = calendar.get(Calendar.DAY_OF_YEAR);
            calendar.setTime(endDate);
            int endDay = calendar.get(Calendar.DAY_OF_YEAR);
            return endDay - startDay;
        } else {
            /*  跨年不会出现问题，需要注意不满24小时情况（2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 0）  */
            //  只格式化日期，消除不满24小时影响
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long startDateTime = dateFormat.parse(dateFormat.format(startDate)).getTime();
            long endDateTime = dateFormat.parse(dateFormat.format(endDate)).getTime();
            return (int) ((endDateTime - startDateTime) / (1000 * 3600 * 24));
        }
    }
}