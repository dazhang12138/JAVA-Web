package com.yyjz.icop.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String DateOfString(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(date);
	}
}
