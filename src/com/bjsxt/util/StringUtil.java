package com.bjsxt.util;
/**
 * 
 * @author ͷ���ţ�IT�߾���ԭ��
 *
 */
public class StringUtil {
	/**
	 * �ַ����մ���
	 * 1�����Ϊnull,���ؿմ� ��������� NullPointerException
	 * 2������������ݣ�����ȥ��ǰ��ո����ַ���
	 */
	public static String nullBlank(String str){
		return null==str?"":str.trim();
	}
	/**
	 * �ж��Ƿ�Ϊ��
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str){
		return null==str || "".equals(str.trim());
	}
	
	
	
}
