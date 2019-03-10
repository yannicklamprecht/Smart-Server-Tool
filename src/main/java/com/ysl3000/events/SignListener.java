package com.ysl3000.events;

import com.ysl3000.events.signcommands.DelSignCommand;
import com.ysl3000.events.signcommands.FreeSignCommand;
import com.ysl3000.events.signcommands.JoinSignCommand;
import com.ysl3000.events.signcommands.SignCommand;
import com.ysl3000.events.signcommands.SignCommand.SignWrapper;
import java.util.HashMap;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignListener implements Listener {


  private HashMap<String, SignCommand> signCommandHashMap = new HashMap<>();


  SignListener() {
    register(new FreeSignCommand());
    register(new DelSignCommand());
    register(new JoinSignCommand());

  }


  private void register(SignCommand signCommand) {
    signCommandHashMap.put(signCommand.getKey(), signCommand);
  }


  @EventHandler
  public void onSignChange(SignChangeEvent e) {
    SignCommand signCommand = signCommandHashMap.get(e.getLine(1));
    if (signCommand != null) {
      signCommand.executeOnCreation(wrap(e));
    }
  }

  @EventHandler
  public void rclickSign(PlayerInteractEvent e) {
    if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock()
        .getState() instanceof Sign) {

      Sign s = (Sign) e.getClickedBlock().getState();

      SignCommand signCommand = signCommandHashMap.get(s.getLine(1));
      if (signCommand != null) {
        e.setCancelled(true);
        signCommand.executeOnClick(e.getPlayer(), s);
      }
    }

  }

  private SignWrapper wrap(SignChangeEvent signChangeEvent) {
    return new SignWrapper() {
      @Override
      public String getLine(int index) {
        return signChangeEvent.getLine(index);
      }

      @Override
      public void setLine(int index, String line) {
        signChangeEvent.setLine(index, line);
      }

      @Override
      public Player getPlayer() {
        return signChangeEvent.getPlayer();
      }

      @Override
      public void setCancelled(boolean canceled) {
        signChangeEvent.setCancelled(canceled);
      }

      @Override
      public void breakNaturally() {
          signChangeEvent.getBlock().breakNaturally();
      }
    };
  }
}
