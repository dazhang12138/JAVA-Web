package com.yyjz.icop.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String DateOfString(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	public static String DateOfStringD(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
}
