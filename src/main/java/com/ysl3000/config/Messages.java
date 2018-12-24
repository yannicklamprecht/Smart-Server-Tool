package com.ysl3000.config;

/**
 * Created by ysl3000
 */
public class Messages {

    private boolean enable = true;
    private boolean enablbeRandomChatColor = true;
    private String timeformat = "MM dd, yyyy HH%dpmm";
    private Service service = new Service();
    private PlayerMessage player = new PlayerMessage();


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isEnablbeRandomChatColor() {
        return enablbeRandomChatColor;
    }

    public void setEnablbeRandomChatColor(boolean enablbeRandomChatColor) {
        this.enablbeRandomChatColor = enablbeRandomChatColor;
    }

    public String getTimeformat() {
        return timeformat;
    }

    public void setTimeformat(String timeformat) {
        this.timeformat = timeformat;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public PlayerMessage getPlayer() {
        return player;
    }

    public void setPlayer(PlayerMessage player) {
        this.player = player;
    }
}
