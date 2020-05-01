package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */

public class ServerInfoCommandMessage extends CommandConfig {

  private String serverOnlineMessage = "Current memoryuse {total_memory}/{max_memory} mb\nThis host has: {core}\nThe seed is : {world_seed}\nServer mode is {server_online_mode{&aonline:&4offline}}\n&rOnline ({online_players}/{max_players})";

  public ServerInfoCommandMessage() {

    super("serverinfo", "get Serverinfo",
        "/serverinfo", "sst.info");
  }

  public String getServerOnlineMessage() {
    return serverOnlineMessage;
  }

  public void setServerOnlineMessage(String serverOnlineMessage) {
    this.serverOnlineMessage = serverOnlineMessage;
  }
}
