package com.ysl3000.config.settings.messages.commands;

import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
public class ServerInfoCommandMessage extends CommandConfig {

  private String serverOnlineMessage= "Current memoryuse {total_memory}/{max_memory} mb\nThis host has: {core}\nThe seed is : {world_seed}\nServer mode is  {server_online_mode{&aonline:&4offline}}\nOnline ({online_players}/{max_players})";

  public ServerInfoCommandMessage(){

    super("serverinfo", "get Serverinfo",
        "/serverinfo", "sst.info");
  }

}
