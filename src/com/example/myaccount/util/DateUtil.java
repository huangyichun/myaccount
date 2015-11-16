package com.example.myaccount.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ��ȡһ���еĵڼ�������
 * 
 * @author Administrator
 * 
 */
public class DateUtil {
	
	//�жϱ����Ƿ�Ϊ���գ���Ϊ�������Ϊ����
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
	// ��ȡ���ܵĵ�һ������һ��
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
				return "0"+ month + "��" + "0"+start+"��" + "-" +"0"+ month + "��" +"0"+ end+"��";
			}else if(start < 10 && end < 10){
				return  month + "��" +"0"+ start+"��" + "-" + month + "��" + "0"+end+"��";
			}else if(start<10 && month <10){
				return  "0"+month + "��" + "0"+start+"��" + "-" + "0"+month + "��" + end+"��";
			}else if(end<10 && month <10){
				return  "0"+month + "��" + start+"��" + "-" + "0"+month + "��" + "0"+end+"��";
			}else if(start<10){
				return  month + "��" + "0"+start+"��" + "-" + month + "��" + end+"��";
			}else if(end < 10 ){
				return  month + "��" + start+"��" + "-" + month + "��" +"0"+ end+"��";
			}else if(month <10){
				return  "0"+month + "��" + start+"��" + "-" + month + "��" + end+"��";
			}
			return  month + "��" + start+"��" + "-" + month + "��" + end+"��";
		}
		
	}
	//�жϱ��µ����һ��
	public static int getLastDay(int year ,int month){
		//����
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

	// ��ȡ����Ϊһ���µĵڼ���
	public static int getWeek(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		Date date = sdf.parse(str);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		int week = calendar.get(Calendar.WEEK_OF_MONTH);

		return week;

	}

	// ��ȡΪ���ڼ�
	public static String getDayOfWeek() {
		String weekDay = null;
		Calendar calendar = Calendar.getInstance();

		int week = calendar.get(Calendar.DAY_OF_WEEK);

		switch (week) {
		case 1:
			weekDay = "����";
			break;
		case 2:
			weekDay = "��һ";
			break;
		case 3:
			weekDay = "�ܶ�";
			break;
		case 4:
			weekDay = "����";
			break;
		case 5:
			weekDay = "����";
			break;
		case 6:
			weekDay = "����";
			break;
		case 7:
			weekDay = "����";
			break;

		}
		return weekDay;

	}

	// ��ȡ�ܼ�
	public static String getDayOfWeek(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		Date date = sdf.parse(str);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String weekDay = null;

		int week = calendar.get(Calendar.DAY_OF_WEEK);

		switch (week) {
		case 1:
			weekDay = "����";
			break;
		case 2:
			weekDay = "��һ";
			break;
		case 3:
			weekDay = "�ܶ�";
			break;
		case 4:
			weekDay = "����";
			break;
		case 5:
			weekDay = "����";
			break;
		case 6:
			weekDay = "����";
			break;
		case 7:
			weekDay = "����";
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

	// ��ȡ�����������ת����ʽΪ2015-11-03
	public static String getDate() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = sDateFormat.format(new java.util.Date());
		return date;
	}
}
