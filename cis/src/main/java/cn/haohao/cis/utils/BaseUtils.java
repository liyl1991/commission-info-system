package cn.haohao.cis.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BaseUtils {

	public static Date getFirstDayOnCurrentMonth(){
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		try {
			return sdf.parse(year+"-"+(month+1)+"-"+"01");
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date getFirstDayOnPreMonth(){
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		try {
			return sdf.parse(year+"-"+(month)+"-"+"01");
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean isListOnlyHasOne(List<?> list){
		if(list != null && list.size() == 1){
			return true;
		}
		return false;
	}
}
