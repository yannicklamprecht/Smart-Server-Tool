package com.ysl3000.utils;

import org.bukkit.permissions.Permission;

public class Permissions {

  public static final Permission GAMEMODE_OTHER = new Permission(
      "sst.gamemodeo");
  public static final Permission GM_LOOKUP_OTHER = new Permission("sst.gmo");
  public static final Permission TELEPORT_OTHER = new Permission("sst.tpo");
  public static final Permission FLY_OTHER = new Permission("sst.flyo");
  public static final Permission VISIBLE_OTHER = new Permission("sst.visibleo");
  public static final Permission PLAYER_INFO = new Permission("sst.info");
  public static final Permission OVERRIDE_VISIBILITY = new Permission("sst.cansee");
  public static final Permission MODIFY_BLOCK = new Permission("sst.create");
  public static final Permission JOIN_FULL = new Permission("sst.joinfull");
  public static final Permission JOIN_SERVICE = new Permission("sst.joinservice");
  public static final Permission MOVE = new Permission("sst.move");
  public static final Permission CAN_CAHNGE_SPAWNER_TYPE = new Permission("sst.changetype");
  public static final Permission HOME_OTHER = new Permission("sst.homeo");
  public static final Permission OPEN_ANY_CHEST = new Permission("sst.openanychest");
  public static final Permission OPEN_CHEST = new Permission("sst.openchest");
  public static final Permission OPEN_VIRTUAL_ENDER_CHEST = new Permission("sst.vechest");
  public static final Permission OPEN_VIRTUAL_WORKBENCH = new Permission("sst.vwbench");
  public static final Permission OBEN_VIRTUAL_ENDCHANTING_TABLE = new Permission("sst.vetable");
  public static final Permission INTERACT = new Permission("sst.interact");
  public static final Permission CHAT = new Permission("sst.chat");

  private Permissions() {
  }
}
