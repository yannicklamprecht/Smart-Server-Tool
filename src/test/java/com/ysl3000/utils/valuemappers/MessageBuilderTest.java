package com.ysl3000.utils.valuemappers;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import com.ysl3000.config.settings.Messages;
import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.Utility;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by ysl3000
 */
public class MessageBuilderTest {

  private MessageBuilder messageBuilder;

  private ServerMock server;

  @Before
  public void setUp() {

    this.server = new SmartServerMock();

    Messages messages = new Messages();
    Utility utility = new Utility(server);
    this.messageBuilder = new MessageBuilder(server, messages, utility);

  }


  @Test
  public void shouldInjectServerName() {
    MessageWrapper messageWrapper = MessageWrapper.of("Hey {server_name}");
    String message = this.messageBuilder.replaceMessageValues(messageWrapper).getMessage();
    Assert.assertEquals("ServerName should be injected successfully", "Hey SmartServer", message);
  }

  @Test
  public void shouldInjectPlayerName() {

    server = MockBukkit.mock();
    Player player = new SmartPlayerMock(server, "SmartServerTool", new UUID(1, 2));

    MessageWrapper messageWrapper = MessageWrapper.of("Hey {player_name}", player);
    String message = this.messageBuilder.replaceMessageValues(messageWrapper).getMessage();
    Assert.assertEquals("DisplayName should be injected successfully", "Hey SmartServerTool",
        message);
  }

  @Test
  public void test() {

    MessageWrapper messageWrapper = MessageWrapper
        .of("Kicked because your're {login_result}", Result.KICK_BANNED);

    String message = this.messageBuilder.replaceMessageValues(messageWrapper).getMessage();

    Assert.assertEquals("LoginResult should be injected successfully",
        "Kicked because your're banned", message);


  }


}
