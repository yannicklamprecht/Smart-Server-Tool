/**
 * SpamFilter.java
 * 
 * Created on 02.09.2013, 19:42:36 by @author yannicklamprecht
 */
package com.ysl3000.utils;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * @author yannicklamprecht
 * 
 */
public class SpamFilter implements Filter {

	public SpamFilter() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.logging.Filter#isLoggable(java.util.logging.LogRecord)
	 */
	@Override
	public boolean isLoggable(LogRecord arg0) {
		return !new SpamConfigLoader().isSpam(arg0.getMessage());
	}

}
