package com.ysl3000.plugin;


import com.ysl3000.commands.*;
import com.ysl3000.events.*;
import com.ysl3000.utils.SpamFilter;
import com.ysl3000.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
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
                new PlayerListener(this),
                new SignListener(),
                new MOTD(this, utility)
        );


        for (World i : Bukkit.getWorlds()) {

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

        register(new AdminCommand());
        register(new BackCommand());
        register(new CheckCurrentGamemode());
        register(new CreativeGamemode());
        register(new SurvivalGamemode());
        register(new DoneCommand());
        register(new FlyMode());
        register(new FlySpeed());
        register(new TimeDay());
        register(new TimeNight());
        register(new Sun());
        register(new Storm());
        register(new GetWeather());
        register(new KillMe());
        register(new Kill());
        register(new Heal());
        register(new HealMe());
        register(new ServerInfo());
        register(new PlayerLookUpIp());
        register(new SwitchLocation());
        register(new SetSpawn());
        register(new Spawn());
        register(new Home());
        register(new Walkspeed());
        register(new God());
        register(new Online());
        register(new Seen(utility));
        register(new Freeze());
        register(new RealTime(utility));
        register(new ModCommand());




    }


    private void registerEvents(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }

    }

    private void register(Command command) {
        command.register(commandMap);
    }


    private void unregister(Command command) {
        command.unregister(commandMap);
    }


}