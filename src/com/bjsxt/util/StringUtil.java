package com.bjsxt.util;
/**
 * 
 * @author 头条号：IT蛇精病原创
 *
 */
public class StringUtil {
	/**
	 * 字符串空处理
	 * 1、如果为null,返回空串 ，避免后期 NullPointerException
	 * 2、如果存在内容，返回去除前后空格后的字符串
	 */
	public static String nullBlank(String str){
		return null==str?"":str.trim();
	}
	/**
	 * 判断是否为空
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str){
		return null==str || "".equals(str.trim());
	}
	
	
	
}
