package com.ysl3000.config;

import com.ysl3000.config.data.WorldSpawnLocation;
import com.ysl3000.config.settings.SmartSettings;
import com.ysl3000.utils.ISpamConfig;
import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by ysl3000
 */
public class ConfigProviderTest {

  private ConfigurationProvider configurationProvider;

  @Before
  public void setup() {
    File dataFolder = new File("./src/test/resources/");
    dataFolder.mkdirs();
    this.configurationProvider = new ConfigurationProvider(dataFolder);
  }


  @Test
  public void testSmartSettings() throws IOException {
    SmartSettings smartSettings = configurationProvider.getSmartSettings();
  }

  @Test
  public void testFirstJoinMessage() throws IOException {
    SmartSettings smartSettings = configurationProvider.getSmartSettings();

    Assert.assertEquals("Message of first join is wrong", "It's the first time",
        smartSettings.getMessages().getPlayer().getFirstJoin());
  }

  @Test
  public void testWorldSettings() throws IOException {
    WorldSpawnLocation worldSpawnLocation = configurationProvider.getWorldSpawnLocation();

    worldSpawnLocation.getSpawnpointForWorld("world");

  }


  @Test
  public void nssholeShouldBeaSwearWord() throws IOException {
    ISpamConfig spamConfig = configurationProvider.getSpamConfig();

    Assert.assertTrue("Asshole should be a spam word", spamConfig.isSpam("asshole"));
  }

  @Test
  public void nudesShouldNotBeASwearWord() throws IOException {
    ISpamConfig spamConfig = configurationProvider.getSpamConfig();

    Assert.assertFalse("Nudes should not be a spam word", spamConfig.isSpam("nudes"));
  }

}
