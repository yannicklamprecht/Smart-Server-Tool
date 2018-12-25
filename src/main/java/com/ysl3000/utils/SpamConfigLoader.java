package com.ysl3000.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


/**
 * @author yannicklamprecht
 * 
 */
public class SpamConfigLoader {
	private FileConfiguration config;

	private File spamConfigFile;


	public SpamConfigLoader(File directory) {
		this.spamConfigFile = new File(directory,"spam.yml");

		this.config = YamlConfiguration.loadConfiguration(spamConfigFile);

	}

	public boolean isSpam(String value) {
		for (String s : Optional.ofNullable(config.getStringList("spammessage")).orElse(new ArrayList<>())) {
			if (value.startsWith(s) || value.contains(s)) {
				return true;
			}
		}
		return false;
	}

	public void saveConfigurationFile() {
		try {
			this.config.save(spamConfigFile);
		} catch (IOException e) {
			System.out.println("FAILED");
		}
	}
}
