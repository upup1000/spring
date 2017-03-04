package edu.ldcollege.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static ThreadLocal<DateFormat> daySDF = new ThreadLocal<DateFormat>() {
		protected synchronized DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};
	public static String getLogYMDStringDate(Date date) {
		return daySDF.get().format(date);
	}
}
