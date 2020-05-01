package com.ysl3000.config;

import com.ysl3000.config.data.WorldSpawnLocation;
import com.ysl3000.config.settings.SmartSettings;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;
import org.yaml.snakeyaml.representer.Representer;

/**
 * Created by ysl3000
 */
public class ConfigurationProvider {

  private static final Logger LOGGER = Logger.getLogger(ConfigurationProvider.class.getName());

  private final File datafolder;
  private final ClassLoader classLoader;

  public ConfigurationProvider(File datafolder, ClassLoader classLoader) {
    this.datafolder = datafolder;
    this.classLoader = classLoader;
  }


  public SmartSettings loadSmartSettings() {
    return dumpAndLoad(SmartSettings.class, new SmartSettings(),
        new File(datafolder, "settings.yml"));
  }

  public void saveSmartSettings(SmartSettings smartSettings) throws IOException {
    save(smartSettings, new File(datafolder, "settings.yml"));
  }

  public void saveWorldSpawns(WorldSpawnLocation worldSpawnLocation) throws IOException {
    save(worldSpawnLocation, new File(datafolder, "worlds.yml"));
  }


  private <T> T dumpAndLoad(Class<T> tClass, T defaultValue, File file) {

    if (!file.exists()) {
      try {
        save(defaultValue, file);
      } catch (IOException e) {
        LOGGER.throwing(ConfigurationProvider.class.getName(), "safe", e);
      }
    }

    T loadedData;
    try {
      loadedData = load(tClass, file);

    } catch (FileNotFoundException e) {
      LOGGER.throwing(ConfigurationProvider.class.getName(), "load", e);
      loadedData = defaultValue;
    }

    return loadedData;
  }

  public WorldSpawnLocation getWorldSpawnLocation() {
    return dumpAndLoad(WorldSpawnLocation.class, new WorldSpawnLocation(),
        new File(datafolder, "worlds.yml"));
  }


  private <T> Yaml build(Class<T> tClass) {
    DumperOptions dumperOptions = new DumperOptions();
    dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
    return new Yaml(new CustomClassLoaderConstructor(tClass, classLoader), new Representer(),
        dumperOptions);
  }

  private <T> T load(Class<T> tClass, File file) throws FileNotFoundException {
    Yaml yaml = build(tClass);
    return yaml.loadAs(new FileReader(file), tClass);
  }

  private <T> void save(T config, File file) throws IOException {
    Yaml yaml = build(config.getClass());
    yaml.dump(config, new FileWriter(file));
  }
}
