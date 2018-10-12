package person.marlon.diamond.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import person.marlon.diamond.common.time.Period;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtil {

	protected static Logger logger = LoggerFactory.getLogger(TimeUtil.class);

	/**
	 * 计算两个Date之间的自然月及天数
	 */
	public static Period getNaturalMonthsAndDays(Date start, Date end) {
		Period period;
		if(start == null || end == null){
			logger.error("start date {} or end date {] is null!",start,end);
			return Period.ZERO;
		}

		if(start.getTime() > end.getTime()){
			logger.error("start date {} is later than end date {} !",start.getTime(),end.getTime());
			return Period.ZERO;
		}

		//GregorianCalendar startCalendar = new GregorianCalendar(2017,1,28);
		GregorianCalendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(start);
		logger.info("start Date  YEAR-MONTH-DAY_OF_MONTH --> " + startCalendar.get(Calendar.YEAR) + "-" +
				+ (startCalendar.get(Calendar.MONTH) + 1)  + "-" +
				+ startCalendar.get(Calendar.DAY_OF_MONTH));
		//GregorianCalendar endCalendar = new GregorianCalendar(2018,6,31);
		GregorianCalendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(end);
		logger.info("end Date YEAR-MONTH-DAY_OF_MONTH --> " + endCalendar.get(Calendar.YEAR) + "-" +
				+ (endCalendar.get(Calendar.MONTH) + 1)+ "-" +
				+ endCalendar.get(Calendar.DAY_OF_MONTH));

		int startMonthDays = getMonthDays(start);
		int endMonthDays = getMonthDays(end);

		logger.info("start Date month days --> " + startMonthDays);
		logger.info("end Date month days --> " + endMonthDays);
		int betweenDaysOfDayOfDate = 0;//起始时间和结束时间月份日期天数相差的天数，如15,16，则为-1
		int betweenMonths = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);//两个月份的差额，按天（几号确定是否要+-1）
		int betweenYears = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		//考虑年份
		if(betweenYears > 0){
			betweenMonths += (12 * betweenYears);
		}

		//起始时间距离当前月底相差的天数
		int restDaysToStartMonthEnd = startMonthDays - startCalendar.get(Calendar.DAY_OF_MONTH);
		//结束时间距离当前月底相差的天数
		int restDaysToEndMonthEnd = endMonthDays - endCalendar.get(Calendar.DAY_OF_MONTH);

		//如果起始月已经是当前最后一天，直接按结束月的时间天数
		if(restDaysToStartMonthEnd == 0){
			//2-28(非闰)--6-28  3个月 + 28天(当月的月份1要减去)  --2.28 - 7.31(30)这种情形,如果是最后一天，31，算整月，30不算一个月,按结束当月总天数算
			if(restDaysToEndMonthEnd == 0){//3.31-5.31
				//a[0] = betweenMonths;
				//a[1] = betweenDaysOfDayOfDate;
				period = Period.ofMonthsAndDays(betweenMonths,betweenDaysOfDayOfDate);

			}else{//3.31-5.30 | 2-28 - 7-30
				betweenMonths -= 1;
				//a[0] = betweenMonths;
				//a[1] = endCalendar.get(Calendar.DAY_OF_MONTH);
				period = Period.ofMonthsAndDays(betweenMonths,endCalendar.get(Calendar.DAY_OF_MONTH));
			}
		}else{//否则按照起始/结束两个月天数日期计算月份天数差值（6.25,3.24）
			betweenDaysOfDayOfDate = endCalendar.get(Calendar.DAY_OF_MONTH) - startCalendar.get(Calendar.DAY_OF_MONTH);
			if(betweenDaysOfDayOfDate >= 0){//1.1-2.1
				//如果结束的时间是当月最后一天
				if(restDaysToEndMonthEnd == 0){//6.1-7.31 | 6.1-6.30 | 2.27-7.31
					//退款当天都不算，比如月底订购，月底退款（6.30-8.31），直接计算7、8,而如果7.1号退，则7月少算一天（restDaysToStartMonthEnd应该为30）
					//a[0] = betweenMonths;
					//a[1] = restDaysToStartMonthEnd;
					period = Period.ofMonthsAndDays(betweenMonths,restDaysToStartMonthEnd);
				}else{//2.27-7.30
					//a[0] = betweenMonths;
					//a[1] = betweenDaysOfDayOfDate;//天数按相差日期算
					period = Period.ofMonthsAndDays(betweenMonths,betweenDaysOfDayOfDate);
				}
			}else{
				//到这里，不可能开始和结束的日期相减超过30了，超过的情况上面已经判断过2.28-7.30这种（除非是一个月31天，另一个月1天才可能，但是上面开始月最后一天已判断过）
				//if(betweenDaysOfDayOfDate < -30){//7.30-8.1 | 7.30-9.1
				betweenMonths -= 1;
				betweenDaysOfDayOfDate = betweenDaysOfDayOfDate + 30;//1.30-2.1,实际是2天，但因为这种都不是最后一天的按照30天算了，所以实际这里会差上一天
				//a[0] = betweenMonths;
				//a[1] = betweenDaysOfDayOfDate;
				period = Period.ofMonthsAndDays(betweenMonths,betweenDaysOfDayOfDate);
				//}
			}
		}

		logger.info("betweenMonths --> {}",period.getMonths());
		logger.info("betweenDays --> {}", period.getDays());

		//boolean leapYear = calendar.isLeapYear(2001);
		//System.out.println(leapYear);
		return period;
	}

	/**
	 *	计算当前月份一共多少天
	 *
	 * @param current
	 * @return days
	 */
	public static int getMonthDays(Date current){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(current);
		//取当前月的下个月第一天 -1获取当前月的最后一天，获取最后一天的日期即为该月总天数
		GregorianCalendar nextMonthCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH) + 1,1);
		nextMonthCalendar.add(Calendar.DATE, -1);//也可以用天数endCalendar.add(Calendar.DAY_OF_MONTH, -1);//再使endCalendar减去1天
		return nextMonthCalendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 *	计算当前时间是当月几号
	 *
	 * @param current
	 * @return days
	 */
	public static int getCurrentMonthDay(Date current){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(current);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
}
