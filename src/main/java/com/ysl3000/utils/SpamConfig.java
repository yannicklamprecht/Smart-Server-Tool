package com.ysl3000.utils;

import java.util.List;

/**
 * @author yannicklamprecht
 */
public class SpamConfig implements ISpamConfig {

    private List<String> spammessages;


    @Override
    public boolean isSpam(String value) {
        for (String s : spammessages) {
            if (value.startsWith(s) || value.contains(s)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getSpammessages() {
        return spammessages;
    }

    public void setSpammessages(List<String> spammessages) {
        this.spammessages = spammessages;
    }
}
