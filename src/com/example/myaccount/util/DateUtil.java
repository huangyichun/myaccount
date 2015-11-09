package com.example.myaccount.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取一月中的第几个星期
 * @author Administrator
 *
 */
public class DateUtil {
	
	public static int getWeek(String str) throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		Date date = sdf.parse(str);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		int week = calendar.get(Calendar.WEEK_OF_MONTH);
		return week;
		
	}
	
	public static int getYear(){
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		
		return year;
	}
	public static int getMonth(){
		Calendar calendar = Calendar.getInstance();
		int Month = calendar.get(Calendar.MONTH);
		
		return Month+1;
	}
}
