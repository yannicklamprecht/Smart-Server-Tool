package com.ysl3000.config;

import com.ysl3000.config.data.SpawnLocation;
import com.ysl3000.config.data.WorldSpawnLocation;
import com.ysl3000.config.settings.SmartSettings;
import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by ysl3000
 */
public class ConfigurationProviderTest {

  private ConfigurationProvider configurationProvider;

  @Before
  public void setup() {
    File dataFolder = new File("./src/test/resources/");
    dataFolder.mkdirs();
    this.configurationProvider = new ConfigurationProvider(dataFolder,
        ClassLoader.getSystemClassLoader());
  }


  @Test
  public void testSmartSettings() {
    SmartSettings smartSettings = configurationProvider.loadSmartSettings();
    Assert.assertNotNull(smartSettings);
  }

  @Test
  public void testFirstJoinMessage() {
    SmartSettings smartSettings = configurationProvider.loadSmartSettings();

    Assert.assertEquals("Message of first join is wrong", "It's the first time",
        smartSettings.getMessages().getPlayer().getFirstJoin());
  }

  @Test
  public void testWorldSettings() {
    WorldSpawnLocation worldSpawnLocation = configurationProvider.getWorldSpawnLocation();
    Assert.assertNotNull(worldSpawnLocation);
  }

  @Test
  public void safeSpawn() throws IOException {

    WorldSpawnLocation worldSpawnLocation = new WorldSpawnLocation();
    worldSpawnLocation.getWorldSpawns().add(new SpawnLocation("world", 1337, 13, 14, 0, 0));
    worldSpawnLocation.getWorldSpawns().add(new SpawnLocation("world_nether", 1337, 13, 14, 0, 0));

    configurationProvider.saveWorldSpawns(worldSpawnLocation);

    WorldSpawnLocation spawnLocation =configurationProvider.getWorldSpawnLocation();

    Assert.assertEquals(2,spawnLocation.getWorldSpawns().size());

    Assert.assertTrue(spawnLocation.getWorldSpawns().stream().anyMatch(w -> w.getWorldName().equalsIgnoreCase("world")));
    Assert.assertTrue(spawnLocation.getWorldSpawns().stream().anyMatch(w -> w.getWorldName().equalsIgnoreCase("world_nether")));


  }

}
