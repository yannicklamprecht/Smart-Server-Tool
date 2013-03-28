package com.github.ysl3000.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {

	public String getRealTime(String format, long timemillis){
		long yourmilliseconds = timemillis;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		 //MMM dd,yyyy HH:mm
		Date resultdate = new Date(yourmilliseconds);
		return sdf.format(resultdate);
	}
	
}
