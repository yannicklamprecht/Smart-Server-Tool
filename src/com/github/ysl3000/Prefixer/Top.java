package com.github.ysl3000.Prefixer;


import java.io.IOException;
import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.PluginNameConversationPrefix;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import com.github.ysl3000.SmartServerTool;
import com.github.ysl3000.Location.SpawnArea;

public class Top {

	

	static HashMap<Player, ItemStack[]> invp = new HashMap<Player, ItemStack[]>();
	static HashMap<Player, Location> locp = new HashMap<Player, Location>();
	

	public static boolean toggleop(Command cmd, CommandSender sender,
			String[] args) throws IOException {

		Player player = (Player) sender;

		if (SmartServerTool.getCommands().getMod(cmd.getName())) {

			if (SmartServerTool.getPermission().hasMod(player)) {

				try {
					mod(player, args);
				} catch (Exception e) {
					
				}
			}

		} else if (SmartServerTool.getCommands().getDone(cmd.getName())) {
			if (SmartServerTool.getPermission().hasMod(player)) {

				try {
					done(player, args);
				} catch (Exception e) {

					player.sendMessage("You aren't in modmode");
				}
			}
		}

		else if (SmartServerTool.getCommands().getAdmin(cmd.getName())) {

			admin(player, args);
		}

		return false;

	}

	public static boolean done(Player player, String[] args) throws Exception {

		if (args.length == 0) {

			doneMe(player);
			Prefix.Pfix(player);

		} else if (args.length == 1) {
			if (SmartServerTool.getPermission().hasMod(player)) {

				Player target = player.getServer().getPlayer(args[0]);

				target.setGameMode(GameMode.SURVIVAL);
				target.getInventory().clear();

				target.getInventory().setContents(invp.get(target));
				target.teleport(locp.get(target));

				player.sendMessage((ChatColor.RED + "modmode disabled for " + target
						.getName()));
				target.sendMessage((ChatColor.RED + "modmode disabled"));

				Prefix.Pfix(target);
				
				SmartServerTool.getHSP().setIsMOD(target);
			}

		}

		return false;

	}

	public static boolean mod(Player player, String[] args) throws Exception {

		
		if (args.length == 0) {

			if (player.getInventory().getContents().length == 0) {

				player.getInventory().addItem(new ItemStack(0, 4));
			}

			invp.put(player, player.getInventory().getContents());

			locp.put(player, player.getLocation());
			player.getInventory().clear();
			player.setOp(true);
			player.setGameMode(GameMode.CREATIVE);
			player.sendMessage((ChatColor.GREEN + "Modmode enabled"));
			
			SmartServerTool.getHSP().setIsMOD(player);

			PluginNameConversationPrefix pre = new PluginNameConversationPrefix(SmartServerTool.plugin, " ", ChatColor.GOLD); 
			player.setDisplayName(pre + player.getDisplayName());

		} else if (args.length == 1) {

			Player target = player.getServer().getPlayer(args[0]);

			invp.put(target, target.getInventory().getContents());
			locp.put(target, target.getLocation());
			target.getInventory().clear();
			target.setGameMode(GameMode.CREATIVE);

			SmartServerTool.getHSP().setIsMOD(target);
			player.sendMessage((ChatColor.GREEN + "modmode enabled for "
					+ ChatColor.GOLD + target.getName()));
			target.sendMessage((ChatColor.GREEN + "modmode enabled"));

			
		PluginNameConversationPrefix pre = new PluginNameConversationPrefix(SmartServerTool.plugin, " ", ChatColor.GOLD); 
			target.setDisplayName(pre + player.getDisplayName());
		}
		return false;

	}

	public static boolean admin(Player player, String[] args) {

		if (args.length == 0) {
			if ((!player.isOp()) && SmartServerTool.getPermission().hasAdmin(player)) {

				player.setOp(true);

				player.sendMessage((ChatColor.GREEN + "Op enabled"));

			} else if ((player.isOp()) && SmartServerTool.getPermission().hasAdmin(player)) {

				player.setOp(false);

				player.sendMessage((ChatColor.RED + "Op disabled"));
			}
		}else if(args.length == 1){
			player.sendMessage("please use /deop <player>");
		}

		
		
		return false;
	}

	public static boolean doneMe(Player player) {

		player.getInventory().clear();

		player.getInventory().setContents(invp.get(player));

		if (!locp.containsKey(player)) {

			Command cmd = new Command("spawn") {

				@Override
				public boolean execute(CommandSender arg0, String arg1,
						String[] arg2) {
					// TODO Auto-generated method stub
					return false;
				}
			};

			try {
				SpawnArea.spawn(player, null, new String[0], cmd );
			} catch (Exception e) {

			}
		} else {

			player.teleport(locp.get(player));

		}

		player.setOp(false);
		player.setGameMode(GameMode.SURVIVAL);
		player.sendMessage((ChatColor.RED + "Modmode disabled"));
		SmartServerTool.getHSP().removeIsMOD(player);
		return false;

	}

	
}
