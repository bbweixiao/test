package com.kaishengit.util;

public class StringUtil {

	/**
	 * �ж�һ���ַ��Ƿ�Ϊnull��Ϊ��
	 * @param str
	 * @return 
	 */
	public static boolean isEmpty(String str) {
		return (str == null || "".equals(str));
	}
	
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
}
