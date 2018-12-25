package com.ysl3000.config.settings;

/**
 * Created by ysl3000
 */
public class PlayerMessage {

    private String privateJoinMessage = "%online%";
    private String firstJoin = "It's the first time";
    private String joinMessage = "%user% on %server% running %v% %b% on %core% ! %n% It's time% !";
    private String leftMessage = "Player %user% left %server% !";


    public String getPrivateJoinMessage() {
        return privateJoinMessage;
    }

    public void setPrivateJoinMessage(String privateJoinMessage) {
        this.privateJoinMessage = privateJoinMessage;
    }

    public String getFirstJoin() {
        return firstJoin;
    }

    public void setFirstJoin(String firstJoin) {
        this.firstJoin = firstJoin;
    }

    public String getJoinMessage() {
        return joinMessage;
    }

    public void setJoinMessage(String joinMessage) {
        this.joinMessage = joinMessage;
    }

    public String getLeftMessage() {
        return leftMessage;
    }

    public void setLeftMessage(String leftMessage) {
        this.leftMessage = leftMessage;
    }
}
