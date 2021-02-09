package invest.utils;

/**
 * @author zhichao.ni
 * 2021/2/5
 **/
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:节假日工具类
 * @Auther: Work-PC
 * @Date: 2019-02-13 19:50
 * @Version: 1.0.0
 */
public class DateUtil {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) throws Exception {
		// 节假日
		String holidays = "2019-01-01,2019-01-04,2019-01-05,2019-01-06,2019-01-07,2019-01-08,2019-01-09,2019-01-10,2019-04-05,2019-04-06,2019-04-07,2019-05-01,2019-06-07,2019-06-08,2019-06-09,2019-09-13,2019-09-14,2019-09-15,2019-10-01,2019-10-02,2019-10-03,2019-10-04,2019-10-05,2019-10-06,2019-10-07";
		// 设置工作日
		int num = 1;
		// 当前时间
		String today = sdfs.format(new Date());

		System.out.println("当前,日期为==============" + today);
		// num个工作日前
		String workDayStart = getWorkDayStart(holidays, today, num);
		System.out.println(num + "个工作日前,日期为========" + workDayStart);
		// num个工作日后
		String workDayEnd = getWorkDayEnd(holidays, today, num);
		System.out.println(num + "个工作日后,日期为========" + workDayEnd);
	}


	/**
	 * 获取当前时间之前n个工作日的日期
	 *
	 * @param holidays 节假日（日期格式：2019-01-01,2019-01-04,2019-01-05,......）
	 * @param today    当前日期（日期格式：2019-01-01 08:08:08）
	 * @param num      需要设置的n个工作日
	 * @return
	 * @throws Exception
	 */
	public static String getWorkDayStart(String holidays, String today, int num) throws Exception {
		// 转化为数组
		String[] dayArr = holidays.split(",");
		List<String> holidayList = new ArrayList<String>(Arrays.asList(dayArr));
		// 将字符串转换成日期
		Date date = sdfs.parse(today);

		// 获取工作日
		Date workDay = getWorkDay(holidayList, num, date, -1);
		String workDayStr = sdf.format(workDay);
		long workTime = getTime(today, workDayStr) - 1000;    // 减1秒

		return sdfs.format(new Date(workTime));
	}

	/**
	 * 获取当前时间之后n个工作日的日期
	 *
	 * @param holidays 节假日（日期格式：2019-01-01,2019-01-04,2019-01-05,......）
	 * @param today    当前日期（日期格式：2019-01-01 08:08:08）
	 * @param num      需要设置的n个工作日
	 * @return
	 * @throws Exception
	 */
	public static String getWorkDayEnd(String holidays, String today, int num) throws Exception {
		// 转化为数组
		String[] dayArr = holidays.split(",");
		List<String> holidayList = new ArrayList<String>(Arrays.asList(dayArr));

		// 将字符串转换成日期
		Date date = sdfs.parse(today);

		// 获取工作日
		Date workDay = getWorkDay(holidayList, num, date, 1);
		String workDayStr = sdf.format(workDay);
		long workTime = getTime(today, workDayStr) + 1000;    // 加1秒

		return sdfs.format(new Date(workTime));

	}

	/**
	 * 获取工作日
	 *
	 * @param holidayList 节假日（日期格式：2019-01-01,2019-01-04,2019-01-05,......）
	 * @param num         需要设置的n个工作日
	 * @param day         目标日期
	 * @return
	 * @throws Exception
	 */
	public static Date getWorkDay(List<String> holidayList, int num, Date day, int n) throws Exception {
		int delay = 1;
		while (delay <= num) {
			// 获取前一天或后一天日期
			Date endDay = getDate(day, n);
			String time = sdf.format(endDay);

			//当前日期+1即tomorrow,判断是否是节假日,同时要判断是否是周末,都不是则将scheduleActiveDate日期+1,直到循环num次即可
			if (!isWeekend(time) && !isHoliday(time, holidayList)) {
				delay++;
			} else if (isWeekend(time)) {
				System.out.println(time + "::是周末");
			} else if (isHoliday(time, holidayList)) {
				System.out.println(time + "::是节假日");
			}
			day = endDay;
		}
		return day;
	}

	/**
	 * yyyy-MM-dd HH:mm:ss格式日期---获取时间戳精确到秒
	 *
	 * @param start 开始日期（日期格式：2019-01-01 08:08:08）
	 * @param end   结束日期（日期格式：2019-01-01 08:08:08）
	 * @return
	 * @throws Exception
	 */
	public static long getTime(String start, String end) throws Exception {
		if (StringUtils.isEmpty(start) || StringUtils.isEmpty(end)) {
			throw new RuntimeException("today is empty");
		}

		long time1 = sdfs.parse(start).getTime();
		long time2 = sdf.parse(start).getTime();
		long time3 = sdf.parse(end).getTime();

		long time = time3 + (time1 - time2);

		return time;
	}

	/**
	 * 获取前一天或后一天日期
	 *
	 * @param date 日期
	 * @param n    判断参数
	 * @return
	 */
	public static Date getDate(Date date, int n) {
		if (n > 0) {    // 获取前一天
			date = getTomorrow(date);
		}
		if (n < 0) {    // 获取后一天
			date = getYesterday(date);
		}
		return date;
	}

	/**
	 * 获取后一天的日期
	 *
	 * @param date
	 * @return
	 */
	public static Date getTomorrow(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +1);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 获取前一天的日期
	 *
	 * @param date
	 * @return
	 */
	public static Date getYesterday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 判断是否是周末
	 *
	 * @param sdate
	 * @return
	 * @throws Exception
	 */
	public static boolean isWeekend(String sdate) throws Exception {
		Date date = sdf.parse(sdate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 判断是否是节假日
	 *
	 * @param sdate
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static boolean isHoliday(String sdate, List<String> list) throws Exception {
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (sdate.equals(list.get(i))) {
					return true;
				}
			}
		}
		return false;
	}
	public static final int getDayDiffer(Date startDate, Date endDate) throws ParseException {
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
