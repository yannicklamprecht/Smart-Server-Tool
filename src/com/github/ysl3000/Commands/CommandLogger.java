package com.github.ysl3000.Commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.ysl3000.SmartServerTool;

public class CommandLogger {

	private static BufferedWriter bw;

	private static FileWriter fw;

	public static void commandToLog(CommandSender sender, String command,
			String[] args, Command cmd) throws Exception {

			if (sender instanceof Player) {

				Player player = (Player) sender;

				if (player.hasPermission("sst.nolog")) {

				} else if (!player.hasPermission("sst.nolog")) {
					if (fw == null) {

						fw = new FileWriter((SmartServerTool.getMainDirectory()
								+ "/CommandLog/" + "log.yml"), true);
						bw = new BufferedWriter(fw);

						bw.newLine();
						bw.write("["
								+ new SimpleDateFormat("YYYY:MM:DD")
										.format(new Date()) + "]");
						bw.write("["
								+ new SimpleDateFormat("HH:mm:ss")
										.format(new Date()) + "]");
						bw.write("\t" + player.getName());
						bw.write("\t" + command);
						for (int i = 0; i < args.length; i++) {
							bw.write("\t" + args[i]);
						}

						bw.close();
						fw.close();

					} else {

						fw = new FileWriter((SmartServerTool.getMainDirectory()
								+ "/CommandLog/" + "log.yml"), true);
						bw = new BufferedWriter(fw);

						bw.newLine();
						bw.write("["
								+ new SimpleDateFormat("YYYY:MM:DD")
										.format(new Date()) + "]");
						bw.write("["
								+ new SimpleDateFormat("HH:mm:ss")
										.format(new Date()) + "]");
						bw.write("\t<" + player.getName() + ">");
						bw.write("\t/" + command);
						for (int i = 0; i < args.length; i++) {
							bw.write("\t" + args[i]);
						}

						bw.close();
						fw.close();

					}
				}
			} else {
				return;
			}

	}
}
