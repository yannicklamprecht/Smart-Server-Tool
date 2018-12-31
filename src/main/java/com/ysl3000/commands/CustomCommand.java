package com.ysl3000.commands;


import com.ysl3000.config.settings.CommandConfig;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;

public abstract class CustomCommand
    extends Command {

  public CustomCommand(CommandConfig commandConfig) {
    super(commandConfig.getName(), commandConfig.getDescription(), commandConfig.getUsageMessage(),
        new ArrayList<>());
    setPermission(commandConfig.getPermission());
  }


  public CustomCommand(String name, String description, String usageMessage, List<String> aliases,
      String permission) {
    super(name, description, usageMessage, aliases);
    setPermission(permission);
  }

  public boolean registerAlias(String alias) {
    return getAliases().add(alias);
  }

  public boolean registerAliases(List<String> aliases) {
    return getAliases().addAll(getAliases().size(), aliases);
  }

  public String getHelp() {
    return this.usageMessage + " " + this.description;
  }
}