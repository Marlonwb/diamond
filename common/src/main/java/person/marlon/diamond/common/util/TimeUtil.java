package person.marlon.diamond.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import person.marlon.diamond.common.time.Period;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimeUtil {

	protected static Logger logger = LoggerFactory.getLogger(TimeUtil.class);

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyy-MM-dd
	 */
	public static String DEFAULT_DAY_FORMAT = "yyyy-MM-dd";

	/**
	 * yyyy/MM/dd
	 */
	public static String DEFAULT_YYYY_MM_DD_FORMAT = "yyyy/MM/dd";

	/**
	 * yyyyMMddHHmmss
	 */
	public static String DEFAULT_DATE_NO_SEPRATOR_FORMAT = "yyyyMMddHHmmss";
	/**
	 * yyyyMMddTHHmmssZ for google calendar
	 */
	public static String DEFAULT_DATE_GOOGLE_DATE = "yyyyMMdd'T'HHmmss'Z'";
	/**
	 * yyyyMMdd
	 */
	public static final String DEFAULT_DAY_NO_SEPRATOR_FORMAT = "yyyyMMdd";
	/**
	 * dd/MM/yyyy
	 */
	public static final String DEFAULT_SLASH_FORMAT = "MM/dd/yyyy";
	/**
	 * MM/dd/yyyy HH mm
	 */
	public static final String DEFAULT_SLASH_FORMAT_HH_MM = "MM/dd/yyyy HH:mm";
	/**
	 * dd/MM/yyyy HH mm
	 */
	public static final String DEFAULT_FORMAT_dd_HH_MM = "dd/MM/yyyy HH:mm";

	/**
	 * dd/MM/yyyy HH mm
	 */
	public static final String DEFAULT_SLASH_FORMAT_HH_MM_A = "MM/dd/yyyy hh:mma";
	/**
	 * dd/MM/yyyy HH mm ss
	 */
	public static final String DEFAULT_SLASH_FORMAT_HH_MM_SS = "MM/dd/yyyy HH:mm:ss";
	/**
	 * HH:mm
	 */
	public static final String DEFAULT_HH_MM = "HH:mm";
	/**
	 * HH:mm a
	 */
	public static final String hh_mm_a = "hh:mma";
	/**
	 * "MMMM dd ,yyyy  hh:mm a"
	 */
	public static final String DEFAULT_MMMM_DD_YYYY_HH_MM_A = "MMM dd, yyyy  hh:mma";

	/**
	 * "MMMM dd ,yyyy  hh:mm a"
	 */
	public static final String DEFAULT_MMMM_DD_YYYY = "MMM dd, yyyy";

	public static final String DEFAULT_DD_MMMM_YYYY = "dd MMM, yyyy";

//	public static final String  DEFAULT_YYYY_MMMM_DD            = "yyyy,MMM dd";
	/**
	 * MM/dd/yyyy agent plan time pattern
	 */
	public static String AGENT_PLAN_TIME_PATTERN = "MM/dd/yyyy";

	/**
	 * yyyyMMddHHmmss
	 */
	public static String ERPTIMESTAMP = "yyyyddMMHHmmss";
	/**
	 * yyyy/MM/dd HH:mm
	 */
	public static String UPGRADE_NOTIFICATION_TIME_FORMAT = "yyyy/MM/dd HH:mm";


	public static String USER_DATE_FORMAT_AHOUR_HH = "HH";

	public static String USER_DATE_FORMAT_AHOUR_hh = "hh";

	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static String STATISTICS_DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm";

	/**
	 * dd/MM/yyyy HH mm
	 */
	public static final String DEFAULT_SLASH_FORMAT_HH_MM_SS_A = "MM/dd/yyyy hh:mm:ss a";

	/**
	 * yyyy-MM-dd'T'HHmmss'Z' for influxdb calendar
	 */
	public static String INFLUXDB_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static String VOUCHER_NAME_DATE_FORMAT = "dd-MM-yyyy";

	/**
	 * yyyyMMddHHmmsssss
	 */
	public static String DATE_FORMAT_MILLISCOND = "yyyyMMddHHmmsssss";

	public static String schedule_upgrade_format = "yyyy-MM-dd hh:mma";

	/**
	 * 计算两个Date之间的自然月及天数
	 */
	public static Period getNaturalMonthsAndDays(Date start, Date end) {
		Period period;
		if (start == null || end == null) {
			logger.error("start date {} or end date {] is null!", start, end);
			return Period.ZERO;
		}

		if (start.getTime() > end.getTime()) {
			logger.error("start date {} is later than end date {} !", start.getTime(), end.getTime());
			return Period.ZERO;
		}

		//GregorianCalendar startCalendar = new GregorianCalendar(2017,1,28);
		GregorianCalendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(start);
		logger.info("start Date  YEAR-MONTH-DAY_OF_MONTH --> " + startCalendar.get(Calendar.YEAR) + "-" +
				+(startCalendar.get(Calendar.MONTH) + 1) + "-" +
				+startCalendar.get(Calendar.DAY_OF_MONTH));
		//GregorianCalendar endCalendar = new GregorianCalendar(2018,6,31);
		GregorianCalendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(end);
		logger.info("end Date YEAR-MONTH-DAY_OF_MONTH --> " + endCalendar.get(Calendar.YEAR) + "-" +
				+(endCalendar.get(Calendar.MONTH) + 1) + "-" +
				+endCalendar.get(Calendar.DAY_OF_MONTH));

		int startMonthDays = getMonthDays(start);
		int endMonthDays = getMonthDays(end);

		logger.info("start Date month days --> " + startMonthDays);
		logger.info("end Date month days --> " + endMonthDays);
		int betweenDaysOfDayOfDate = 0;//起始时间和结束时间月份日期天数相差的天数，如15,16，则为-1
		int betweenMonths = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);//两个月份的差额，按天（几号确定是否要+-1）
		int betweenYears = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		//考虑年份
		if (betweenYears > 0) {
			betweenMonths += (12 * betweenYears);
		}

		//起始时间距离当前月底相差的天数
		int restDaysToStartMonthEnd = startMonthDays - startCalendar.get(Calendar.DAY_OF_MONTH);
		//结束时间距离当前月底相差的天数
		int restDaysToEndMonthEnd = endMonthDays - endCalendar.get(Calendar.DAY_OF_MONTH);

		//如果起始月已经是当前最后一天，直接按结束月的时间天数
		if (restDaysToStartMonthEnd == 0) {
			//2-28(非闰)--6-28  3个月 + 28天(当月的月份1要减去)  --2.28 - 7.31(30)这种情形,如果是最后一天，31，算整月，30不算一个月,按结束当月总天数算
			if (restDaysToEndMonthEnd == 0) {//3.31-5.31
				//a[0] = betweenMonths;
				//a[1] = betweenDaysOfDayOfDate;
				period = Period.ofMonthsAndDays(betweenMonths, betweenDaysOfDayOfDate);

			} else {//3.31-5.30 | 2-28 - 7-30
				betweenMonths -= 1;
				//a[0] = betweenMonths;
				//a[1] = endCalendar.get(Calendar.DAY_OF_MONTH);
				period = Period.ofMonthsAndDays(betweenMonths, endCalendar.get(Calendar.DAY_OF_MONTH));
			}
		} else {//否则按照起始/结束两个月天数日期计算月份天数差值（6.25,3.24）
			betweenDaysOfDayOfDate = endCalendar.get(Calendar.DAY_OF_MONTH) - startCalendar.get(Calendar.DAY_OF_MONTH);
			if (betweenDaysOfDayOfDate >= 0) {//1.1-2.1
				//如果结束的时间是当月最后一天
				if (restDaysToEndMonthEnd == 0) {//6.1-7.31 | 6.1-6.30 | 2.27-7.31
					//退款当天都不算，比如月底订购，月底退款（6.30-8.31），直接计算7、8,而如果7.1号退，则7月少算一天（restDaysToStartMonthEnd应该为30）
					//a[0] = betweenMonths;
					//a[1] = restDaysToStartMonthEnd;
					period = Period.ofMonthsAndDays(betweenMonths, restDaysToStartMonthEnd);
				} else {//2.27-7.30
					//a[0] = betweenMonths;
					//a[1] = betweenDaysOfDayOfDate;//天数按相差日期算
					period = Period.ofMonthsAndDays(betweenMonths, betweenDaysOfDayOfDate);
				}
			} else {
				//到这里，不可能开始和结束的日期相减超过30了，超过的情况上面已经判断过2.28-7.30这种（除非是一个月31天，另一个月1天才可能，但是上面开始月最后一天已判断过）
				//if(betweenDaysOfDayOfDate < -30){//7.30-8.1 | 7.30-9.1
				betweenMonths -= 1;
				betweenDaysOfDayOfDate = betweenDaysOfDayOfDate + 30;//1.30-2.1,实际是2天，但因为这种都不是最后一天的按照30天算了，所以实际这里会差上一天
				//a[0] = betweenMonths;
				//a[1] = betweenDaysOfDayOfDate;
				period = Period.ofMonthsAndDays(betweenMonths, betweenDaysOfDayOfDate);
				//}
			}
		}

		logger.info("betweenMonths --> {}", period.getMonths());
		logger.info("betweenDays --> {}", period.getDays());

		//boolean leapYear = calendar.isLeapYear(2001);
		//System.out.println(leapYear);
		return period;
	}

	/**
	 * 计算当前月份一共多少天
	 *
	 * @param current
	 * @return days
	 */
	public static int getMonthDays(Date current) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(current);
		//取当前月的下个月第一天 -1获取当前月的最后一天，获取最后一天的日期即为该月总天数
		GregorianCalendar nextMonthCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1);
		nextMonthCalendar.add(Calendar.DATE, -1);//也可以用天数endCalendar.add(Calendar.DAY_OF_MONTH, -1);//再使endCalendar减去1天
		return nextMonthCalendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 计算当前时间是当月几号
	 *
	 * @param current
	 * @return days
	 */
	public static int getCurrentMonthDay(Date current) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(current);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}


	/**
	 * 指定日期格式，转化时间字符串为Date对象
	 *
	 * @param pattern
	 * @param dateString
	 * @return
	 */
	public static Date parseDatePattern(String pattern, String dateString) {
		if(StringUtils.isEmpty(pattern)) pattern = DEFAULT_DAY_FORMAT;
		DateFormat df = new SimpleDateFormat(pattern/*, Locale.ENGLISH*/);
		try {
			return df.parse(dateString);
		} catch (ParseException e) {
			logger.debug("time format error --> {}",e);
			return null;
		}
	}

	public static Date parseDatePattern(String dateString) {
		if (StringUtils.isBlank(dateString)) {
			return null;
		}
		return parseDatePattern(DEFAULT_DAY_FORMAT, dateString);
	}

	/**
	 * 根据TimeZoneId获取时区
	 * TimeZone timeZone = calendar.getTimeZone();
	 *
	 * timeZone = sun.util.calendar.ZoneInfo[id="UTC",offset=0,dstSavings=0,useDaylight=false,transitions=0,lastRule=null]
	 * timeZone = sun.util.calendar.ZoneInfo[id="Asia/Shanghai",offset=28800000,dstSavings=0,useDaylight=false,transitions=19,lastRule=null]
	 *
	 * TimeZone.getTimeZone(timezoneId)
	 */
	public static int getTimeZoneOffset(TimeZone timeZone){
		if(timeZone != null){
			int rawOffset = timeZone.getRawOffset();
			return rawOffset / (3600 * 1000);
		}
		return 0;
	}

	public static int getTimeZoneOffset(String timezoneId){
		return getTimeZoneOffset(TimeZone.getTimeZone(timezoneId));
	}
	/**
	 * 获取当前服务器系统时区起始时间
	 */
	public static Map<String, Object> parseTimePeriod(Map<String, Object> paramsMap,String timezoneId) {
		Map<String,Object> searchMap = new HashMap<>();
		if (paramsMap.get("createTime") != null) {
			List createTime = (List) paramsMap.get("createTime");
			if (createTime != null && createTime.size() >= 2) {
				Object start = createTime.get(0);
				Object end = createTime.get(1);
				logger.info("receive the params of  start and end --> start[{}] ,end[{}]", start, end);

				Long startDateLong;//时间戳
				Long endDateLong;
				String err;
				try {
					String startDayPattern = (String) start;
					String endDayPattern = (String) end;
					Date startDate = TimeUtil.parseDatePattern(startDayPattern);
					Date endDate = TimeUtil.parseDatePattern(endDayPattern);

					if (startDate == null || endDate == null) {
						err = "not supported type --> start:[" + start + "] ,end:[" + end + "]";
						searchMap.put("error", err);
						return searchMap;
					}
					startDateLong = startDate.getTime();
					endDateLong = endDate.getTime();

					if (startDateLong > endDateLong) {
						err = "time format error:start time couldn't be later than end time!--> start:[" + start + "] ,end:[" + end + "]";
						searchMap.put("error", err);
						return searchMap;
					}
				} catch (Exception e) {
					logger.error("parse createTime param error --> {}", e);
					//出错，返回一个错误的String,Controller处判断是否是错误，返回前端相应信息
					err = "parse createTime param error --> start:[" + start + "] ,end:[" + end + "]";
					searchMap.put("error", err);
					return searchMap;
				}

				TimeZone timeZone = TimeZone.getTimeZone(timezoneId);
				if (timeZone == null) {
					logger.error("parse user timeZoneOffSet error:invalid timezoneId --> {}", timezoneId);
					err = "parse user timeZoneOffSet error:invalid timezoneId --> {" + timezoneId + "}";
					searchMap.put("error", err);
					return searchMap;
				}
				Integer timeZoneOffSet = -getTimeZoneOffset(timeZone);//用户配置的时区,这里取相反数（-）是因为要将用户的时区算回0时区
				//计算转换相应的时区信息
				logger.info("parsed user timeZoneOffSet --> {}", timeZoneOffSet);
				try {
					startDateLong += (timeZoneOffSet * 60L * 60L * 1000L);
					endDateLong += (timeZoneOffSet * 60L * 60L * 1000L) + 24L * 60L * 60L * 1000L;//区间+1天 （0:00）
				} catch (NumberFormatException e) {
					logger.error("parse user timezone error --> {}", e.toString());
					err = "parse user timezone error";
					searchMap.put("error", err);
					return searchMap;
				}
				logger.info("the param pass to database --> startDateLong[{}] ,endDateLong[{}]", startDateLong, endDateLong);
				searchMap.put("start", new Date(startDateLong));
				searchMap.put("end", new Date(endDateLong));
			}
		}
		return searchMap;
	}

}
