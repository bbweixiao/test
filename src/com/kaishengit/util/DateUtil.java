package com.kaishengit.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getNow() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return sdf.format(date);
	}
	
}
