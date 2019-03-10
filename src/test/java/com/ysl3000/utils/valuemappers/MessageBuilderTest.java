package com.ysl3000.utils.valuemappers;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import com.ysl3000.config.settings.Messages;
import com.ysl3000.stubs.SmartBukkitStub;
import com.ysl3000.stubs.SmartPlayerStub;
import com.ysl3000.stubs.SmartServerStub;
import com.ysl3000.stubs.SmartWorldStub;
import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.Utility;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by ysl3000
 */
public class MessageBuilderTest {

  private MessageBuilder messageBuilder;

  private ServerMock server;
  private WorldMock worldMock;

  @Before
  public void setUp() {
    this.worldMock = new SmartWorldStub();
    this.server = new SmartServerStub(worldMock);

    Messages messages = new Messages();
    Utility utility = new Utility(server);
    this.messageBuilder = new MessageBuilder(server, messages, utility);

  }

  @After
  public void tearDown() {
    SmartBukkitStub.unset();
  }


  @Test
  public void shouldInjectServerName() {
    String message = this.messageBuilder.injectParameter("Hey {server_name}");
    Assert.assertEquals("ServerName should be injected successfully", "Hey SmartServer", message);
  }

  @Test
  public void shouldInjectPlayerName() {

    server = MockBukkit.mock();
    Player player = new SmartPlayerStub(server, "SmartServerTool", new UUID(1, 2));

    MessageWrapper messageWrapper = MessageWrapper.of("Hey {player_name}", player);
    String message = this.messageBuilder.replaceMessageValues(messageWrapper).getMessage();
    Assert.assertEquals("DisplayName should be injected successfully", "Hey SmartServerTool",
        message);

    MockBukkit.unload();
  }

  @Test
  public void test() {

    MessageWrapper messageWrapper = MessageWrapper
        .of("Kicked because your're {login_result}", Result.KICK_BANNED);

    String message = this.messageBuilder.replaceMessageValues(messageWrapper).getMessage();

    Assert.assertEquals("LoginResult should be injected successfully",
        "Kicked because your're banned", message);
  }

  @Test
  public void shouldSelectRainyWeather() {

    worldMock.setThundering(true);

    String message = this.messageBuilder
        .injectParameter("The Weather is {weather{rainy:sunny}}", worldMock);

    Assert.assertEquals("Weather should be injected successfully",
        "The Weather is rainy", message);


  }

  @Test
  public void shouldSelectSunnyWeather() {

    worldMock.setThundering(false);

    String message = this.messageBuilder
        .injectParameter("The Weather is {weather{rainy:sunny}}", worldMock);

    Assert.assertEquals("Weather should be injected successfully",
        "The Weather is sunny", message);


  }

}
