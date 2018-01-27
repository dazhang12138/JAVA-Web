package com.yyjz.icop.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	/**
	 * 时间转换为字符类型
	 * @param date 时间为yyyy-MM-dd HH:mm:ss格式
	 * @return yyyy-MM-dd HH:mm:ss格式的时间
	 */
	public static String DateOfString(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	/**
	 * 时间转换为字符类型
	 * @param date 时间为yyyy-MM-dd格式
	 * @return yyyy-MM-dd格式的时间
	 */
	public static String DateOfStringD(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	/**
	 * 字符型时间转换为时间类型
	 * @param date yyyy-MM-dd格式的时间
	 * @return yyyy-MM-dd格式格式的字符
	 * @throws ParseException 意外异常抛出
	 */
	public static Date StringOfDate(String date) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(date);
	}
}
