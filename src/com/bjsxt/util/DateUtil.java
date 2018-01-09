package com.bjsxt.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ���ڹ�����
 * @author ͷ���ţ�IT�߾���ԭ��
 *
 */
public class DateUtil {
	/**
	 * ������ת��ָ���ĸ�ʽ�ַ���
	 */
	public static String formatStr(Date date,String style){
		return new SimpleDateFormat(style).format(date);		
	}
	/**
	 * ������ת��ָ����Ĭ�ϸ�ʽ�ַ���
	 */
	public static String formatStr(Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);		
	}
	
	/**
	 * ������ת��ָ����Ĭ�ϸ�ʽ�ַ���
	 */
	public static String formatStr(long date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(date));		
	}
	/**
	 * ������ת��ָ����ʱ������ַ���
	 */
	public static String formatStrOnlyHms(Date date){
		return new SimpleDateFormat("HH:mm:ss").format(date);		
	}	
	/**
	 * ������ת��ָ����ʱ������ַ���
	 */
	public static String formatStrOnlyHms(long date){
		return new SimpleDateFormat("HH:mm:ss").format(new Date(date));		
	}	
}
