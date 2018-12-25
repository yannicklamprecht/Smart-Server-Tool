package com.ysl3000.plugin;


import com.ysl3000.commands.AdminCommand;
import com.ysl3000.commands.BackCommand;
import com.ysl3000.commands.CheckCurrentGamemode;
import com.ysl3000.commands.CreativeGamemode;
import com.ysl3000.commands.DoneCommand;
import com.ysl3000.commands.FlyMode;
import com.ysl3000.commands.FlySpeed;
import com.ysl3000.commands.Freeze;
import com.ysl3000.commands.GetWeather;
import com.ysl3000.commands.God;
import com.ysl3000.commands.Heal;
import com.ysl3000.commands.HealMe;
import com.ysl3000.commands.Home;
import com.ysl3000.commands.Kill;
import com.ysl3000.commands.KillMe;
import com.ysl3000.commands.ModCommand;
import com.ysl3000.commands.Online;
import com.ysl3000.commands.PlayerLookUpIp;
import com.ysl3000.commands.RealTime;
import com.ysl3000.commands.Seen;
import com.ysl3000.commands.ServerInfo;
import com.ysl3000.commands.SetSpawn;
import com.ysl3000.commands.Spawn;
import com.ysl3000.commands.Storm;
import com.ysl3000.commands.Sun;
import com.ysl3000.commands.SurvivalGamemode;
import com.ysl3000.commands.SwitchLocation;
import com.ysl3000.commands.TimeDay;
import com.ysl3000.commands.TimeNight;
import com.ysl3000.commands.Walkspeed;
import com.ysl3000.events.BlockListener;
import com.ysl3000.events.ChestProtectionListener;
import com.ysl3000.events.EntityListener;
import com.ysl3000.events.MOTD;
import com.ysl3000.events.PlayerConnectionListener;
import com.ysl3000.events.PlayerListener;
import com.ysl3000.events.SignListener;
import com.ysl3000.utils.SpamFilter;
import com.ysl3000.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.logging.Filter;
import java.util.logging.Logger;

public class SmartServerTool extends JavaPlugin {

    public static final String mainDirectory = "plugins/SmartServerTool/";
    private SimpleCommandMap commandMap;
    private Utility utility = new Utility();
    private SmartPlayers smartPlayers;


    @Override
    public void onLoad() {
        Filter f = new SpamFilter();
        Bukkit.getLogger().setFilter(f);
        Logger.getLogger("Minecraft").setFilter(f);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        this.smartPlayers = new SmartPlayers();

        registerEvents(
                new PlayerConnectionListener(smartPlayers),
                new ChestProtectionListener(),
                new BlockListener(this, smartSettings),
                new EntityListener(this),
                new PlayerListener(this, smartPlayers),
                new SignListener(),
                new MOTD(this, utility, smartPlayers)
        );


        for (World i : Bukkit.getWorlds()) {

            // todo refactor spawn config

            if (this.getSpawnConfig().getDouble(i.getName() + ".x") == 0.0
                    && this.getSpawnConfig().getDouble(i.getName() + ".y") == 0.0
                    && this.getSpawnConfig().getDouble(i.getName() + ".z") == 0.0) {

                i.setSpawnLocation(
                        (int) this.getSpawnConfig().getDouble(
                                i.getName() + ".x"), i.getHighestBlockYAt(
                                (int) this.getSpawnConfig().getDouble(
                                        i.getName() + ".x"),
                                (int) this.getSpawnConfig().getDouble(
                                        i.getName() + ".z")), (int) this
                                .getSpawnConfig().getDouble(i.getName() + ".z"));

            } else {
                try {
                    i.setSpawnLocation(
                            (int) this.getSpawnConfig().getDouble(
                                    i.getName() + ".x"),
                            (int) this.getSpawnConfig().getDouble(
                                    i.getName() + ".y"),
                            (int) this.getSpawnConfig().getDouble(
                                    i.getName() + ".z"));
                } catch (Exception ignored) {
                }
            }
        }


        try {
            Class<?> server = Bukkit.getServer().getClass();
            Field commandMapField = server.getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            this.commandMap = ((SimpleCommandMap) commandMapField.get(Bukkit.getServer()));
        } catch (Exception ignored) {
        }

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


    private void registerEvents(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }

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