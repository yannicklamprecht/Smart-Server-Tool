package com.ysl3000.commands;

import com.ysl3000.plugin.SmartServerTool;
import org.bukkit.command.CommandSender;

public class Configreload extends CustomCommand {

  private SmartServerTool smartServerTool;

  public Configreload(SmartServerTool smartServerTool) {
    super("smartreload", "Reloads SmartServerTool config", "/smartreload",
        "sst.reload");
    this.smartServerTool = smartServerTool;
  }

  @Override
  public boolean execute(CommandSender commandSender, String s, String[] strings) {
    smartServerTool.reloadConfig();
    return true;
  }
}
