package com.ysl3000.utils;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * @author yannicklamprecht
 *
 */
public class SpamFilter implements Filter {

    private ISpamConfig spamConfig;

    public SpamFilter(ISpamConfig spamConfig) {
        this.spamConfig = spamConfig;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.logging.Filter#isLoggable(java.util.logging.LogRecord)
     */
    @Override
    public boolean isLoggable(LogRecord arg0) {
        return spamConfig.isSpam(arg0.getMessage());
    }

}
