/**
 * SpamConfigLoader.java
 * 
 * Created on 02.09.2013, 20:48:36 by @author yannicklamprecht
 */
package com.ysl3000.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.ysl3000.plugin.SmartServerTool;


/**
 * @author yannicklamprecht
 * 
 */
public class SpamConfigLoader {
	private String fileName = "spam.yml";
	private FileConfiguration config;
	private ArrayList<String> spamlist;
//TODO Cast to my Configformat
	public SpamConfigLoader() {
		this.config = YamlConfiguration.loadConfiguration(new File(
				SmartServerTool.mainDirectory, fileName));
		ArrayList<String> defaults = new ArrayList<String>();
		defaults.add("Reached end of stream");

		if (this.config.getStringList("spammessage") == null) {
			config.set("spammessage", defaults);
			this.config.options().copyDefaults(true);
			this.saveConfigurationFile();
		}
		initSpamList();
	}

	private void initSpamList() {
		spamlist = (ArrayList<String>) this.config.getStringList("spammessage");

	}

	public ArrayList<String> getSpamList() {
		return spamlist;
	}

	public boolean isSpam(String value) {
		for (String s : spamlist) {
			if (value.startsWith(s) || value.contains(s)) {
				return true;
			}
		}
		return false;
	}

	public void saveConfigurationFile() {
				
		try {
			this.config.save(new File(SmartServerTool.mainDirectory, fileName));
		} catch (IOException e) {
			System.out.println("FAILED");
		}
	}
}
