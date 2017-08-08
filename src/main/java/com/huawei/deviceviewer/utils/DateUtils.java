package com.huawei.deviceviewer.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm";
	
	private static SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
	
	public static String now(){
		return sdf.format(new Date());
	}
	

	public static Date parse(String date) throws ParseException{
		return sdf.parse(date);
	}

	public static String format(Date date, String pattern){
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	public static boolean beforeNow(Date date){
		return beforeNow(format(date, DEFAULT_PATTERN));
	}

	public static boolean beforeNow(String date){
		return date.compareTo(now()) <= 0;
	}
}
