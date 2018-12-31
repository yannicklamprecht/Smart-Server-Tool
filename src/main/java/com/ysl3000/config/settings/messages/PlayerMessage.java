package com.ysl3000.config.settings.messages;

/**
 * Created by ysl3000
 */
public class PlayerMessage {

  private String privateJoinMessage = "Online({online_players}/{max_players}): {list_players} ";
  private String firstJoin = "It's the first time";
  private String joinMessage = "Welcome {player_display_name} on {server_name} running {minecraft_version} {bukkit_version} on {core}! \n It's {time}!";
  private String leftMessage = "Player {player_display_name} left {server_name}!";
  private String tryingToJoinMessage = "{player_name} {login_result} trying to join";

  private FreezeMessage freezeMessage = new FreezeMessage();


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

  public String getTryingToJoinMessage() {
    return tryingToJoinMessage;
  }

  public void setTryingToJoinMessage(String tryingToJoinMessage) {
    this.tryingToJoinMessage = tryingToJoinMessage;
  }

  public FreezeMessage getFreezeMessage() {
    return freezeMessage;
  }

  public void setFreezeMessage(FreezeMessage freezeMessage) {
    this.freezeMessage = freezeMessage;
  }
}
