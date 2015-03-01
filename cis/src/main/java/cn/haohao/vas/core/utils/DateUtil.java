package cn.haohao.vas.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

/**
 * @类名称: DateUtil
 * @类描述:
 * @创建人： wuwei
 * @创建时间：May 26, 2010 10:08:19 AM
 * @see
 */
public class DateUtil extends DateUtils {

	public static final String DATE_FORMAT_CHINESE_DAY = "yyyy年MM月dd日";

	public static final String DATE_FORMAT_CHINESE_MINUTE = "yyyy年MM月dd日 HH:mm";

	public static final String DATE_FORMAT_CHINESE_SECOND = "yyyy年MM月dd日 HH:mm:ss";

	public static final String DATE_FORMAT_NORMAL_YEAR = "yyyy";

	public static final String DATE_FORMAT_NORMAL_MONTH = "yyyy-MM";

	public static final String DATE_FORMAT_NORMAL_DAY = "yyyy-MM-dd";

	public static final String DATE_FORMAT_NORMAL_MINUTE = "yyyy-MM-dd HH:mm";

	public static final String DATE_FORMAT_NORMAL_SECOND = "yyyy-MM-dd HH:mm:ss";

	public static final String TIME_FORMAT_NORMAL = "HH:mm";

	public static final String TIME_FORMAT_NORMAL_HH = "HH";

	public static final String TIME_FORMAT_NORMAL_MM = "mm";

	public static final String TIME_FORMAT_NORMAL_YYYYMM = "yyyyMM";

	/**
	 * 功能：获得当前web服务器日期 格式：yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		return getDateString(new java.util.Date(), DATE_FORMAT_NORMAL_DAY);
	}

	/**
	 * 功能：获得当前web服务器日期 自定义格式
	 * 
	 * @return
	 */
	public static String getCurrentDateByFormat(String format) {
		return getDateString(new java.util.Date(), format);
	}

	/**
	 * 功能：获得当前web服务器日期 格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		return getDateString(new java.util.Date(), DATE_FORMAT_CHINESE_SECOND);
	}

	/**
	 * 功能：时间转换成yyyy-MM-dd格式字符串
	 * 
	 * @return
	 */
	public static String getDateString(Date date) {
		return getDateString(date, DATE_FORMAT_NORMAL_DAY);
	}

	/**
	 * 功能：时间转换成yyyy-MM-dd HH:mm:ss格式字符串
	 * 
	 * @return
	 */
	public static String getDateTimeString(Date date) {
		return getDateString(date, DATE_FORMAT_CHINESE_SECOND);
	}

	/**
	 * 根据format格式格式化Date 返回String
	 * 
	 * @param date
	 * @param format
	 *            DateUtil.DATE_FORMAT_CHINESE_DAY = "yyyy年MM月dd日"; DateUtil.DATE_FORMAT_CHINESE_MINUTE =
	 *            "yyyy年MM月dd日 HH:mm"; DateUtil.DATE_FORMAT_CHINESE_SECOND = "yyyy年MM月dd日 HH:mm:ss";
	 *            DateUtil.DATE_FORMAT_NORMAL_YEAR = "yyyy"; DateUtil.DATE_FORMAT_NORMAL_MONTH = "yyyy-MM";
	 *            DateUtil.DATE_FORMAT_NORMAL_DAY = "yyyy-MM-dd"; DateUtil.DATE_FORMAT_NORMAL_MINUTE =
	 *            "yyyy-MM-dd HH:mm"; DateUtil.DATE_FORMAT_NORMAL_SECOND = "yyyy-MM-dd HH:mm:ss";
	 *            DateUtil.TIME_FORMAT_NORMAL = "HH:mm"; DateUtil.TIME_FORMAT_NORMAL_HH = "HH";
	 *            DateUtil.TIME_FORMAT_NORMAL_MM = "mm";
	 * @return
	 */
	public static String getDateString(Date date, String format) {
		String ret = "";
		if (format != null) {
			ret = new java.text.SimpleDateFormat(format).format(date);
		} else {
			ret = new java.text.SimpleDateFormat(DATE_FORMAT_NORMAL_MINUTE).format(date);
		}
		return ret;
	}

	/**
	 * 根据format格式化String，返回Date
	 * 
	 * @param datastr
	 * @param format
	 * @return Date
	 */
	public static Date stringToDate(String datestr, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			if (datestr == null) {
				return null;
			}
			Date date = sdf.parse(datestr);
			return date;
		} catch (ParseException e) {
			// Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static Date getDateOnly(Date date) {
		return stringToDate(getDateString(date), DATE_FORMAT_NORMAL_DAY);
	}

	public static Integer getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static Integer getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static Integer getWeekDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		return day == 1 ? 7 : day - 1;
	}

	public static Integer getDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DATE);
	}

	public static Date set(Date date, int calendarField, int value) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendarField, value);
		return calendar.getTime();
	}

	public static int get(Date date, int calendarField) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(calendarField);
	}
}
