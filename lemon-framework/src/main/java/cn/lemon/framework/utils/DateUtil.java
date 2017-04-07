package cn.lemon.framework.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DateUtil
 *
 * @author lonyee
 * @date 2016-07-14
 */
public class DateUtil {
    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
	 * 获取当前时区时间
	 */
    public static Date getNowTime() {
		TimeZone time = TimeZone.getTimeZone("GMT+8"); //设置为东八区
		//TimeZone.setDefault(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(time);
		return calendar.getTime();
	}
    
    /**
	 * 获取当前时间戳
	 */
    public static long getTimestamp() {
		TimeZone time = TimeZone.getTimeZone("GMT+8"); //设置为东八区
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(time);
		return calendar.getTimeInMillis() / 1000;
	}
	
    /**
     * 计算两个日期之间的间隔月数
     *
     * @param start
     * @param end
     * @return
     */
    public static int getMonthInterval(Date start, Date end) {
        DateTime s = new DateTime(start);
        DateTime e = new DateTime(end);

        return (e.getYear() - s.getYear()) * 12 + e.getMonthOfYear();
    }

    /**
     * 计算两个日期之间的间隔日数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDayInterval(Date startDate, Date endDate) {
        DateTime start = new DateTime(startDate.getTime());
        DateTime end = new DateTime(endDate.getTime());
        Days days = Days.daysBetween(start, end);
        return days.getDays();
    }


    /**
     * 计算指定日期的当周第一天，如2015-06-03 00:00:00
     *
     * @param timepoint
     * @return
     */
    public static Date getWeekStart(Date timepoint) {
        DateTime dt = new DateTime(timepoint);
        return dt.dayOfWeek().withMinimumValue().toDate();
    }

    /**
     * 计算指定日期的当周最后一天，如2015-06-09 23:59:59
     *
     * @param timepoint
     * @return
     */
    public static Date getWeekEnd(Date timepoint) {
        DateTime dt = new DateTime(timepoint);
        Date end = dt.dayOfWeek().withMaximumValue().toDate();
        return buildMaxOfDate(end);
    }

    /**
     * 计算指定日期的当月第一天，如2015-08-01 00:00:00
     *
     * @param timepoint
     * @return
     */
    public static Date getMonthStart(Date timepoint) {
        DateTime dt = new DateTime(timepoint);
        Date start = dt.dayOfMonth().withMinimumValue().toDate();
        return buildMinOfDate(start);
    }

    /**
     * 计算指定日期的当月最后一天，如2015-08-31 23:59:59
     *
     * @param timepoint
     * @return
     */
    public static Date getMonthEnd(Date timepoint) {
        DateTime dt = new DateTime(timepoint);
        Date end = dt.dayOfMonth().withMaximumValue().toDate();
        return buildMaxOfDate(end);
    }

    /**
     * 日期按日加减计算
     *
     * @param timepoint
     * @param period
     * @return
     */
    public static Date addDatesByDay(Date timepoint, int period) {
        DateTime dt = new DateTime(timepoint);
        return dt.plusDays(period).toDate();
    }

    /**
     * 日期按周加减计算
     *
     * @param timepoint
     * @param period
     * @return
     */
    public static Date addDatesByWeek(Date timepoint, int period) {
        DateTime dt = new DateTime(timepoint);
        return dt.plusWeeks(period).toDate();
    }

    /**
     * 日期按月加减计算
     *
     * @param timepoint
     * @param period
     * @return
     */
    public static Date addDatesByMonth(Date timepoint, int period) {
        DateTime dt = new DateTime(timepoint);
        return dt.plusMonths(period).toDate();
    }

    /**
     * 计算指定日期的最小时间，如2015-08-01 00:00:00
     *
     * @param d
     * @return
     */
    public static Date buildMinOfDate(Date d) {
        DateTime time = new DateTime(d);
        return time.millisOfDay().withMinimumValue().toDate();
    }

    /**
     * 计算指定日期的最大时间，如2015-08-01 23:59:59
     *
     * @param d
     * @return
     */
    public static Date buildMaxOfDate(Date d) {
        DateTime time = new DateTime(d);
        return time.millisOfDay().withMaximumValue().toDate();
    }

    /**
     * 转换日期为字符串类型（pattern自行指定）
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String date2String(Date date, String pattern) {
        try {
            DateTime dt = new DateTime(date.getTime());
            return dt.toString(pattern);
        } catch (Exception e) {
            logger.error("Failed to output as string, pattern is: " + pattern, e);
            return null;
        }
    }

    /**
     * 转换日期为字符串（不带时间）
     *
     * @param date
     * @return
     */
    public static String date2String(Date date) {
        return date2String(date, DATE_PATTERN);
    }

    /**
     * 转换日期为字符串（带时间）
     *
     * @param date
     * @return
     */
    public static String datetime2String(Date date) {
        return date2String(date, DATETIME_PATTERN);
    }

    /**
     * 转换字符串为不带时间的日期格式，如"2015-08-10"
     *
     * @param str
     * @return
     */
    public static Date str2Date(String str) {
        return str2Date(str, DATE_PATTERN);
    }

    /**
     * 转换字符串为带时间的日期格式，如"2015-08-10 20:11:20"
     *
     * @param str
     * @return
     */
    public static Date str2Datetime(String str) {
        return str2Date(str, DATETIME_PATTERN);
    }

    /**
     * 转换字符串为日期格式（pattern自行指定）
     *
     * @param str
     * @param pattern
     * @return
     */
    public static Date str2Date(String str, String pattern) {
        try {
            DateTime dt = DateTime.parse(str, DateTimeFormat.forPattern(pattern));
            return dt.toDate();
        } catch (Exception e) {
            logger.error("Failed parse to Date type, string is " + str, e);
            return null;
        }

    }

    /**
     * 判断两个日期是否是同一日期
     *
     * @param data1
     * @param data2
     * @return
     */
    public static boolean compareToDate(Date data1, Date data2) {
        if (data1 == null && data2 == null) {
            return true;
        }
        if (data1 == null || data2 == null) {
            return false;
        }
        try {
            String data1Str = date2String(data1);
            String data2Str = date2String(data2);
            return data1Str.equals(data2Str);
        } catch (Exception e) {
            logger.error("Failed compare to Date.", e);
            return false;
        }

    }

    /**
     * 获取指定日期指定Field 值
     *
     * @param date
     * @param field
     * @return
     */
    public static int get(Date date, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(field);
    }

    /**
     * 获得指定日期的前一天
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static Date getYesterday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);
        return c.getTime();
    }

    public static void main(String[] args) {
        //Date e = new Date();
        Date dat = DateUtil.str2Date("2015-11-2");
        Date date = addDatesByMonth(dat, -3);
        System.out.println("days:" + DateUtil.date2String(date, "yyyy-MM-dd"));
    }
}