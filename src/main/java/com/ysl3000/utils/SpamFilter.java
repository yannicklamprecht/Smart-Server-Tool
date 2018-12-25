package com.ysl3000.utils;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * @author yannicklamprecht
 *
 */
public class SpamFilter implements Filter {

    private SpamConfigLoader spamConfigLoader;

    public SpamFilter(SpamConfigLoader spamConfigLoader) {
        this.spamConfigLoader = spamConfigLoader;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.logging.Filter#isLoggable(java.util.logging.LogRecord)
     */
    @Override
    public boolean isLoggable(LogRecord arg0) {
        return spamConfigLoader.isSpam(arg0.getMessage());
    }

}
