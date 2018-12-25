package com.ysl3000.commands;

import com.ysl3000.plugin.SmartPlayers;
import com.ysl3000.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;

import java.lang.reflect.Field;

/**
 * Created by ysl3000
 */
public class CommandRegistry {

    private SimpleCommandMap commandMap;
    private SmartPlayers smartPlayers;
    private Utility utility = new Utility();

    public CommandRegistry(SmartPlayers smartPlayers) {
        this.smartPlayers = smartPlayers;

        try {
            Class<?> server = Bukkit.getServer().getClass();
            Field commandMapField = server.getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            this.commandMap = ((SimpleCommandMap) commandMapField.get(Bukkit.getServer()));
        } catch (Exception ignored) {
        }

    }

    public void registerCommands(){

        register(
                new AdminCommand(),
                new BackCommand(smartPlayers),
                new CheckCurrentGamemode(),
                new CreativeGamemode(),
                new SurvivalGamemode(),
                new DoneCommand(smartPlayers),
                new FlyMode(),
                new FlySpeed(),
                new TimeDay(),
                new TimeNight(),
                new Sun(),
                new Storm(),
                new GetWeather(),
                new KillMe(),
                new Kill(),
                new Heal(),
                new HealMe(),
                new ServerInfo(),
                new PlayerLookUpIp(),
                new SwitchLocation(),
                new SetSpawn(),
                new Spawn(),
                new Home(),
                new Walkspeed(),
                new God(smartPlayers),
                new Online(utility),
                new Seen(utility),
                new Freeze(smartPlayers),
                new RealTime(utility),
                new ModCommand(smartPlayers)
        );

    }

    private void register(Command... commands) {
        for (Command command : commands) {
            command.register(commandMap);
        }

    }


    private void unregister(Command command) {
        command.unregister(commandMap);
    }

}
