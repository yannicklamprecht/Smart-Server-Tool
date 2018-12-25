package com.ysl3000.config;

import com.ysl3000.config.data.WorldSpawnLocation;
import com.ysl3000.config.settings.SmartSettings;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

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

        Assert.assertEquals("Message of first join is wrong", "It's the first time", smartSettings.getMessages().getPlayer().getFirstJoin());
    }

    @Test
    public void testWorldSettings() throws IOException {
        WorldSpawnLocation worldSpawnLocation = configurationProvider.getWorldSpawnLocation();

        worldSpawnLocation.getSpawnpointForWorld("world");

    }


}
