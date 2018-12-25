package com.ysl3000.config.settings;

/**
 * Created by ysl3000
 */
public class AdvertisingSettings {

    private boolean enabled = true;
    private int timeBetweenAdverts = 300;
    private String advertMessage = "SmartServerTool";
    private String advertPrefix = "[NEWS]";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getTimeBetweenAdverts() {
        return timeBetweenAdverts;
    }

    public void setTimeBetweenAdverts(int timeBetweenAdverts) {
        this.timeBetweenAdverts = timeBetweenAdverts;
    }

    public String getAdvertMessage() {
        return advertMessage;
    }

    public void setAdvertMessage(String advertMessage) {
        this.advertMessage = advertMessage;
    }

    public String getAdvertPrefix() {
        return advertPrefix;
    }

    public void setAdvertPrefix(String advertPrefix) {
        this.advertPrefix = advertPrefix;
    }
}
