package com.ysl3000.config;

/**
 * Created by ysl3000
 */
public class PlayerMessage {

    private String privatejoinmessage = "%online%";
    private String firstjoin = "It's the first time";
    private String joinmessage = "%user% on %server% running %v% %b% on %core% ! %n% It's time% !";
    private String leftmessage = "Player %user% left %server% !";


    public String getPrivatejoinmessage() {
        return privatejoinmessage;
    }

    public void setPrivatejoinmessage(String privatejoinmessage) {
        this.privatejoinmessage = privatejoinmessage;
    }

    public String getFirstjoin() {
        return firstjoin;
    }

    public void setFirstjoin(String firstjoin) {
        this.firstjoin = firstjoin;
    }

    public String getJoinmessage() {
        return joinmessage;
    }

    public void setJoinmessage(String joinmessage) {
        this.joinmessage = joinmessage;
    }

    public String getLeftmessage() {
        return leftmessage;
    }

    public void setLeftmessage(String leftmessage) {
        this.leftmessage = leftmessage;
    }
}
