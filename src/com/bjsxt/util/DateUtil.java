package com.bjsxt.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author 头条号：IT蛇精病原创
 *
 */
public class DateUtil {
	/**
	 * 将日期转成指定的格式字符串
	 */
	public static String formatStr(Date date,String style){
		return new SimpleDateFormat(style).format(date);		
	}
	/**
	 * 将日期转成指定的默认格式字符串
	 */
	public static String formatStr(Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);		
	}
	
	/**
	 * 将日期转成指定的默认格式字符串
	 */
	public static String formatStr(long date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(date));		
	}
	/**
	 * 将日期转成指定的时分秒的字符串
	 */
	public static String formatStrOnlyHms(Date date){
		return new SimpleDateFormat("HH:mm:ss").format(date);		
	}	
	/**
	 * 将日期转成指定的时分秒的字符串
	 */
	public static String formatStrOnlyHms(long date){
		return new SimpleDateFormat("HH:mm:ss").format(new Date(date));		
	}	
}
