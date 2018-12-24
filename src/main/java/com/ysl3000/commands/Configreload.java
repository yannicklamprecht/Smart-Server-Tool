package com.ysl3000.commands;

import com.ysl3000.utils.ConfigFactorizer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Configreload extends CustomCommand {

    public Configreload() {
        super("smartreload", "Reloads SmartServerTool config", "/smartreload",
                "sst.reload");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {

        // todo refactor
        ConfigFactorizer.createAndReturn(
                (JavaPlugin) Bukkit.getServer()
                        .getPluginManager()
                        .getPlugin("SmartServerTool"))
                .loadConfig();


        return false;
    }
}
