package com.ysl3000.config.settings;

import com.ysl3000.config.settings.messages.PlayerMessage;
import com.ysl3000.config.settings.messages.Service;
import com.ysl3000.config.settings.messages.commands.CommandConfigContainer;

/**
 * Created by ysl3000
 */
public class Messages {

  private boolean enabled = true;
  private boolean enablbeRandomChatColor = true;
  private String timeformat = "MM dd, yyyy HH:mm";
  private Service service = new Service();
  private PlayerMessage player = new PlayerMessage();
  private CommandConfigContainer commandConfigContainer = new CommandConfigContainer();


  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
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

  public CommandConfigContainer getCommandConfigContainer() {
    return commandConfigContainer;
  }

  public void setCommandConfigContainer(
      CommandConfigContainer commandConfigContainer) {
    this.commandConfigContainer = commandConfigContainer;
  }
}
