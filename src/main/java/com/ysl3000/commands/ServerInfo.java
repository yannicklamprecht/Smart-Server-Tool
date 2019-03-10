package com.ysl3000.commands;


import com.ysl3000.config.settings.messages.commands.ServerInfoCommandMessage;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 */
public class ServerInfo extends CustomCommand {


  private ServerInfoCommandMessage serverInfoCommandMessage;
  private MessageBuilder messageBuilder;

  ServerInfo(ServerInfoCommandMessage commandConfig,
      MessageBuilder messageBuilder) {
    super(commandConfig);
    this.serverInfoCommandMessage=commandConfig;
    this.messageBuilder = messageBuilder;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] strings) {
      if (!(sender instanceof Player)) {
          return false;
      }

    Player player = (Player) sender;
    if (player.hasPermission(this.getPermission())) {
      sender.sendMessage(messageBuilder.injectParameter(serverInfoCommandMessage.getServerOnlineMessage(),player));
    }
    return true;
  }
}
