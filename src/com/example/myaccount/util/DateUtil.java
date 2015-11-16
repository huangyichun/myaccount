package com.example.myaccount.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取一月中的第几个星期
 * 
 * @author Administrator
 * 
 */
public class DateUtil {
	
	//判断本周是否为周日，若为周日则归为上周
	public static boolean isSunday(int year,int month, int day) throws ParseException{
		String str = (year + "-" + month + "-" + day);
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		Date date = sdf.parse(str);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int num = calendar.get(Calendar.DAY_OF_WEEK);
		if(num == 1){
			return true;
		}
		return false;
	}
	// 获取本周的第一天和最后一天
	public static String getStWeekEnd(int year, int month, int day, int type)
			throws ParseException {
		String str = (year + "-" + month + "-" + day);
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		Date date = sdf.parse(str);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int start = 0;
		int end = 0;
		int num = calendar.get(Calendar.DAY_OF_WEEK);
		if(num == 1){
			start = day - 6;
			end= day;
			
		}else{
			start = day - num + 2;
			end = day + 8 - num;
		}
		if(start <=  0){
			start =1;
		}
		
		if(type ==1 ){
		return year + "." + month + "." + start + "-" + month + "." + end;
		}else {
			if(start < 10 && end < 10 && month < 10){
				return "0"+ month + "月" + "0"+start+"日" + "-" +"0"+ month + "月" +"0"+ end+"日";
			}else if(start < 10 && end < 10){
				return  month + "月" +"0"+ start+"日" + "-" + month + "月" + "0"+end+"日";
			}else if(start<10 && month <10){
				return  "0"+month + "月" + "0"+start+"日" + "-" + "0"+month + "月" + end+"日";
			}else if(end<10 && month <10){
				return  "0"+month + "月" + start+"日" + "-" + "0"+month + "月" + "0"+end+"日";
			}else if(start<10){
				return  month + "月" + "0"+start+"日" + "-" + month + "月" + end+"日";
			}else if(end < 10 ){
				return  month + "月" + start+"日" + "-" + month + "月" +"0"+ end+"日";
			}else if(month <10){
				return  "0"+month + "月" + start+"日" + "-" + month + "月" + end+"日";
			}
			return  month + "月" + start+"日" + "-" + month + "月" + end+"日";
		}
		
	}
	//判断本月的最后一天
	public static int getLastDay(int year ,int month){
		//闰年
		int num = 0;
		if((year % 4 == 0 && year%100 != 0) || year %400 == 0){
			if(month == 2){
				num = 29;
			}
		}else{
			switch(month){
			case 1:
				num= 31; break;
			case 2:
				num= 28; break;
			case 3:
				num= 31; break;
			case 4:
				num= 30; break;
			case 5:
				num= 31; break;
			case 6:
				num= 30; break;
			case 7:
				num= 31; break;
			case 8:
				num= 31; break;
			case 9:
				num= 30; break;
			case 10:
				num= 31; break;
			case 11:
				num= 30; break;
			case 12:
				num= 31; break;
			}
		}
		
		
		return num;
		
	}

	// 获取今天为一个月的第几周
	public static int getWeek(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		Date date = sdf.parse(str);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		int week = calendar.get(Calendar.WEEK_OF_MONTH);

		return week;

	}

	// 获取为星期几
	public static String getDayOfWeek() {
		String weekDay = null;
		Calendar calendar = Calendar.getInstance();

		int week = calendar.get(Calendar.DAY_OF_WEEK);

		switch (week) {
		case 1:
			weekDay = "周日";
			break;
		case 2:
			weekDay = "周一";
			break;
		case 3:
			weekDay = "周二";
			break;
		case 4:
			weekDay = "周三";
			break;
		case 5:
			weekDay = "周四";
			break;
		case 6:
			weekDay = "周五";
			break;
		case 7:
			weekDay = "周六";
			break;

		}
		return weekDay;

	}

	// 获取周几
	public static String getDayOfWeek(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		Date date = sdf.parse(str);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String weekDay = null;

		int week = calendar.get(Calendar.DAY_OF_WEEK);

		switch (week) {
		case 1:
			weekDay = "周日";
			break;
		case 2:
			weekDay = "周一";
			break;
		case 3:
			weekDay = "周二";
			break;
		case 4:
			weekDay = "周三";
			break;
		case 5:
			weekDay = "周四";
			break;
		case 6:
			weekDay = "周五";
			break;
		case 7:
			weekDay = "周六";
			break;

		}
		return weekDay;

	}

	public static int getYear() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);

		return year;
	}

	public static int getMonth() {
		Calendar calendar = Calendar.getInstance();
		int Month = calendar.get(Calendar.MONTH);

		return Month + 1;
	}

	public static int getDay() {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	// 获取今天的年月日转化格式为2015-11-03
	public static String getDate() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = sDateFormat.format(new java.util.Date());
		return date;
	}
}
