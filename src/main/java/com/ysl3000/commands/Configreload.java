package com.ysl3000.commands;

import com.ysl3000.config.settings.messages.commands.CommandConfig;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Configreload extends CustomCommand {

  private JavaPlugin javaPlugin;

  public Configreload(CommandConfig commandConfig, JavaPlugin javaPlugin) {
    super(commandConfig);
    this.javaPlugin = javaPlugin;
  }

  @Override
  public boolean execute(CommandSender commandSender, String s, String[] strings) {
    javaPlugin.reloadConfig();
    return true;
  }
}
