/**
 * God.java
 * <p>
 * Created on , 18:41:01 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package com.ysl3000.commands;


import com.ysl3000.utils.HashMapController;
import com.ysl3000.utils.SmartPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 *
 */
public class God extends CustomCommand {


    public God() {
        super("god", "Toggle godmode", "/god", "sst.god");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] strings) {
        if (!(sender instanceof Player))
            return false;

        Player p = (Player) sender;
        if (p.hasPermission(this.getPermission())) {

            // todo inject smartPlayer
            if (!HashMapController.getHashMapControler()
                    .getSmartPLayers().containsKey(p.getUniqueId())) {
                HashMapController.getHashMapControler()
                        .getSmartPLayers()
                        .put(p.getUniqueId(),
                                new SmartPlayer(p));
            }


            if (HashMapController.getHashMapControler().getSmartPLayers().get(p.getUniqueId())
                    .isGod()) {
                HashMapController.getHashMapControler().getSmartPLayers().get(p.getUniqueId())
                        .setGod(false);
            } else {
                HashMapController.getHashMapControler().getSmartPLayers().get(p.getUniqueId())
                        .setGod(true);
            }
            p.sendMessage("Godmode set to "
                    + (HashMapController.getHashMapControler().getSmartPLayers()
                    .get(p.getUniqueId()).isGod() ? "True" : "False"));

        } else {
            sender.sendMessage(this.getPermissionMessage());
        }

        return true;
    }
}
