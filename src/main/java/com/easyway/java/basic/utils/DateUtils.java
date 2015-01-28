package com.easyway.java.basic.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * 
 * @author longgangbai 2014-6-19 上午10:06:41
 */
public class DateUtils {

	public static final String DATEFORMATLONG = "yyyy-MM-dd HH:mm:ss";
	public static final String DATEFORMATMEDIUM = "yyyy-MM-dd HH:mm";
	public static final String DATEFORMATSHORT = "yyyy-MM-dd";
	public static final String DATEFORDATESEARCH = "yyyyMMdd";
	public static final String DATEFORTIMESEARCH = "yyyyMMddHHmm";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String DATEFORDATESEARCH_MM_DD = "MM-dd";
	public static final String YYYY_MM_DD_HH_MM_SS_SSS_LONG = "yyyyMMddHHmmssSSS";

	/**
	 * DOCUMENT ME!
	 */
	public static final String YYYY_MM_DD_HH_MM_SS_LONG = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyyMMdd HH:mm:ss";
	/**
	 * DOCUMENT ME!
	 */
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

	/**
	 * DOCUMENT ME!
	 */
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String MM_DD = "MM-dd";
	/**
	 * DOCUMENT ME!
	 */
	public static final String YYYYMMDD = "yyyyMMdd";

	/**
	 * DOCUMENT ME!
	 */
	public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";

	/**
	 * DOCUMENT ME!
	 */
	public static String today;

	/**
	 * DOCUMENT ME!
	 */
	public static String yesterday;
	private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static final SimpleDateFormat DEFAULT_DATE_TIME_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");

	private DateUtils() {
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public static Date getYesterdayDate() {
		return new Date(getCurrentTimeMillis() - 0x5265c00L);
	}

	public static Date getNMonthDate(int n) {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, n);
		return c.getTime();

	}

	public static Date getNDayDate(int n) {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, n);
		return c.getTime();

	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param formatDate
	 *            DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static String getCurrentFormatDate(String formatDate) {
		Date date = getCurrentDate();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate);

		return simpleDateFormat.format(date);
	}

	public static String getCurrentFormatDate() {
		Date date = getCurrentDate();
		return DEFAULT_DATE_TIME_FORMAT.format(date);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param l
	 *            DOCUMENT ME!
	 * @param formatDate
	 *            DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static String getLong2ShortString(long l, String formatDate) {
		Date date = getLong2Date(l);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate);

		return simpleDateFormat.format(date);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param format
	 *            DOCUMENT ME!
	 * @param str
	 *            DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static Date getString2Date(String format, String str) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date travel_date = null;
		try {
			travel_date = simpleDateFormat.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return travel_date;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param str
	 *            DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static Date getString2Date(String str) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD);
		ParsePosition parseposition = new ParsePosition(0);
		return simpleDateFormat.parse(str, parseposition);
	}

	/**
	 * 制定locale的转换方式
	 * 
	 * @param format
	 * @param locale
	 * @param dateStr
	 * @return
	 */
	public static Date getString2Date(String format, Locale locale,
			String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(format, locale);

		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param l
	 *            DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static Date getLong2Date(long l) {
		return new Date(l);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param l
	 *            DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static int getOffDays(long l) {
		return getOffDays(l, getCurrentTimeMillis());
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param from
	 *            DOCUMENT ME!
	 * @param to
	 *            DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static int getOffDays(long from, long to) {
		return getOffMinutes(from, to) / 1440;
	}

	/**
	 * DOCUMENT ME! 与当前系统相差分钟数
	 * 
	 * @param l
	 *            DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static int getOffMinutes(long l) {
		return getOffMinutes(l, getCurrentTimeMillis());
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param from
	 *            DOCUMENT ME!
	 * @param to
	 *            DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public static int getOffMinutes(long from, long to) {
		return (int) ((to - from) / 60000L);
	}

	public static String getLastNQuarterBeginDate(int n) {
		return getLastNQuarterBeginDate(n, DEFAULT_DATE_FORMAT);
	}

	public static String getLastNQuarterBeginDate(int n, DateFormat format) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, n * 3);

		int q = getQuarter(c.get(Calendar.MONTH));

		c.set(Calendar.MONTH, 3 * (q - 1));
		c.set(Calendar.DATE, c.getActualMinimum(Calendar.DATE));
		return format.format(c.getTime());
	}

	public static String getLastNQuarterEndDate(int n) {
		return getLastNQuarterEndDate(n, DEFAULT_DATE_FORMAT);
	}

	private static int getQuarter(int month) {
		if (month <= 2 && month >= 0)
			return 1;
		if (month <= 5 && month >= 3)
			return 2;
		if (month <= 8 && month >= 6)
			return 3;
		if (month <= 11 && month >= 9)
			return 4;
		throw new IllegalStateException("月份错误");
	}

	public static String getLastNQuarterEndDate(int n, DateFormat format) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, n * 3);

		int q = getQuarter(c.get(Calendar.MONTH));

		c.set(Calendar.MONTH, 2 + 3 * (q - 1));

		c.set(Calendar.DATE, c.getMaximum(Calendar.DAY_OF_MONTH));

		return format.format(c.getTime());
	}

	public static String getLastNMonthBeginDate(int n, DateFormat format) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, n);
		c.set(Calendar.DATE, c.getMinimum(Calendar.DAY_OF_MONTH));
		return format.format(c.getTime());
	}

	public static String getLastNMonthBeginDate(int n) {
		return getLastNMonthBeginDate(n, DEFAULT_DATE_FORMAT);
	}

	public static String getLastNMonthEndDate(int n) {
		return getLastNMonthEndDate(n, DEFAULT_DATE_FORMAT);
	}

	public static String getLastNMonthEndDate(int n, DateFormat format) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, n);
		c.set(Calendar.DATE, c.getActualMaximum(Calendar.DAY_OF_MONTH));

		return format.format(c.getTime());
	}

	public static String getLastNYearBeginDate(int n) {
		return getLastNYearBeginDate(n, DEFAULT_DATE_FORMAT);
	}

	public static Timestamp getTimeStamp(Timestamp date, int field, int amount) {
		Long milliseconds = date.getTime();
		if (Calendar.MINUTE == field) {
			milliseconds = milliseconds + amount * (60 * 1000);
		} else if (Calendar.SECOND == field) {
			milliseconds = milliseconds + amount * 1000;
		}
		return new Timestamp(milliseconds);
	}

	public static Date getOneDay(Date d, int field, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(field, amount);
		return c.getTime();
	}

	/**
	 * 
	 * @param d
	 * @param field
	 * @param amount
	 * @return
	 */
	public static Date getSameDay(Date d, int field, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(field, amount);
		c.set(c.get(Calendar.YEAR), 0, 1);
		return c.getTime();
	}

	public static String getLastNYearBeginDate(int n, DateFormat format) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, n);
		c.set(c.get(Calendar.YEAR), 0, 1);
		return format.format(c.getTime());
	}

	public static String getLastNYearEndDate(int n) {
		return getLastNYearEndDate(n, DEFAULT_DATE_FORMAT);
	}

	public static String getLastNYearEndDate(int n, DateFormat format) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, n);
		c.set(c.get(Calendar.YEAR), 11, 31);
		return format.format(c.getTime());
	}

	/**
	 * 获取下一天的日期
	 * 
	 * @param d
	 *            待处理的日期
	 * @return
	 */
	public static Date getNextDate(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	/**
	 * 获取今天后days天日期
	 * 
	 * @param d
	 *            待处理的日期
	 * @return
	 */
	public static Date getOneDate(Date d, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DAY_OF_MONTH, days);
		return c.getTime();
	}

	public static void main(String[] args) {
	}

	/**
	 * 
	 * @param sysdate
	 * @param departureDate
	 * @return
	 */
	public static boolean specialCompare(Date sysdate, Date departureDate) {
		Calendar sysCalendar = Calendar.getInstance();
		sysCalendar.setTime(sysdate);
		sysCalendar.set(Calendar.HOUR_OF_DAY, 0);
		sysCalendar.set(Calendar.MINUTE, 0);
		sysCalendar.set(Calendar.SECOND, 0);

		Calendar departureCalenar = Calendar.getInstance();
		departureCalenar.setTime(departureDate);
		departureCalenar.set(Calendar.HOUR_OF_DAY, 0);
		departureCalenar.set(Calendar.MINUTE, 0);
		departureCalenar.set(Calendar.SECOND, 0);
		return departureCalenar.getTime().after(sysCalendar.getTime());

	}

	/**
	 * 比较两个时间想差几天，yyyyMMdd格式
	 */
	public static long getQuot(String endDate, String startDate)
			throws ParseException {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
		Date date1 = ft.parse(endDate);
		Date date2 = ft.parse(startDate);
		quot = date1.getTime() - date2.getTime();
		quot = quot / 1000 / 60 / 60 / 24;
		return quot;
	}

	/**
	 * 比较一个时间比当前时间想差几天，yyyyMMdd格式
	 */
	public static long getQuotFromNow(String endDate) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
		Date date1;
		try {
			date1 = ft.parse(endDate);
			Date date2 = new Date();
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return quot;
	}

	/**
	 * 比较一个时间比当前时间想差分钟数，yyyyMMdd格式
	 */
	public static long getDiffMinuFromNow(String departure, String departureTime) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmm");
		Date date1;
		try {
			date1 = ft.parse(departure + departureTime);
			quot = getOffMinutes(date1.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return quot;
	}

}
