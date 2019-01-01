package com.ysl3000.commands;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import com.ysl3000.SmartPlayers;
import com.ysl3000.config.settings.CommandConfigContainer;
import com.ysl3000.config.settings.Messages;
import com.ysl3000.stubs.SmartBukkitStub;
import com.ysl3000.utils.Utility;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import org.bukkit.entity.Player;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by ysl3000
 */
public class FreezeTest {


  private Freeze freeze;
  private SmartPlayers smartPlayers;
  private MessageBuilder messageBuilder;
  private ServerMock serverMock;
  private CommandConfigContainer commandConfigContainer;

  @Before
  public void setUp() {
    smartPlayers = new SmartPlayers();
    this.serverMock = MockBukkit.mock();
    Messages messages = new Messages();
    commandConfigContainer = new CommandConfigContainer();
    this.messageBuilder = new MessageBuilder(serverMock, messages, new Utility(serverMock));

    this.freeze = new Freeze(commandConfigContainer.getFreeze(), smartPlayers, messageBuilder);
  }

  @After
  public void tearDown() {
    SmartBukkitStub.unset();
  }


  @Test
  public void notAPlayer() {
    Assert.assertFalse(freeze.execute(null, "", new String[]{}));
  }


  @Test
  public void test() {

    Player player = serverMock.addPlayer();
    freeze.execute(player, "", new String[]{"", ""});

  }


}
