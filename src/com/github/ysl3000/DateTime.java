package com.github.ysl3000;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {

	public static String getRealTime(String format, long timemillis){
		long yourmilliseconds = timemillis;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		 //MMM dd,yyyy HH:mm
		Date resultdate = new Date(yourmilliseconds);
		return sdf.format(resultdate);
	}
	
}
