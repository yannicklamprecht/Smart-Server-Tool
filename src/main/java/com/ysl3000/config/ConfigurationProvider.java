package com.ysl3000.config;

import com.ysl3000.config.data.WorldSpawnLocation;
import com.ysl3000.config.settings.SmartSettings;
import com.ysl3000.config.spam.ISpamConfig;
import com.ysl3000.config.spam.SpamConfig;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by ysl3000
 */
public class ConfigurationProvider {

    private File datafolder;

    public ConfigurationProvider(File datafolder) {
        this.datafolder = datafolder;
    }


    public SmartSettings getSmartSettings() throws IOException {
        return dumpAndLoad(new SmartSettings(), createIfNotExist("settings.yml"));
    }

    private <T> T dumpAndLoad(T element, File file) throws IOException {

        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        Yaml yaml = new Yaml(dumperOptions);

        T loadedData = (T) yaml.loadAs(new FileReader(file), element.getClass());


        if (loadedData == null) {
            yaml.dump(element, new FileWriter(file));
        }

        loadedData = (T) yaml.loadAs(new FileReader(file), element.getClass());

        return loadedData;
    }

    private File createIfNotExist(String fileName) throws IOException {
        File file = new File(datafolder, fileName);
        file.createNewFile();
        return file;
    }

    public WorldSpawnLocation getWorldSpawnLocation() throws IOException {
        return dumpAndLoad(new WorldSpawnLocation(), createIfNotExist("worlds.yml"));
    }

    public ISpamConfig getSpamConfig() throws IOException {
        return dumpAndLoad(new SpamConfig(), createIfNotExist("spam.yml"));
    }

}
