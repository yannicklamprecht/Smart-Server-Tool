package com.ysl3000.events;

import com.ysl3000.config.settings.SmartSettings;
import com.ysl3000.config.settings.WorldSettings;
import com.ysl3000.events.blockbreakcommands.BlockBreakCommand;
import com.ysl3000.events.blockbreakcommands.DiamondPickaxeDropCommand;
import com.ysl3000.events.blockbreakcommands.EnderChestDropCommand;
import com.ysl3000.events.blockbreakcommands.GlassToSandDropCommand;
import com.ysl3000.events.blockbreakcommands.GoldenAppleDropCommand;
import com.ysl3000.events.blockbreakcommands.StainedGlassDropCommand;
import com.ysl3000.events.vectormodifier.DxModifier;
import com.ysl3000.events.vectormodifier.DyModifier;
import com.ysl3000.events.vectormodifier.VectorModifier;
import com.ysl3000.utils.Permissions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.block.data.type.Bed;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class BlockListener implements Listener {


  public static final Tag<Material> STAINED_GLASS = new Tag<Material>() {
    @Override
    public boolean isTagged(Material material) {
      return getValues().contains(material);
    }

    @Override
    public Set<Material> getValues() {
      Set<Material> v = new HashSet<>();
      v.add(Material.BLACK_STAINED_GLASS);
      v.add(Material.RED_STAINED_GLASS);
      v.add(Material.GREEN_STAINED_GLASS);
      v.add(Material.GRAY_STAINED_GLASS);
      v.add(Material.BLUE_STAINED_GLASS);
      v.add(Material.YELLOW_STAINED_GLASS);
      v.add(Material.LIME_STAINED_GLASS);
      v.add(Material.ORANGE_STAINED_GLASS);
      v.add(Material.BROWN_STAINED_GLASS);
      v.add(Material.CYAN_STAINED_GLASS);
      v.add(Material.LIGHT_BLUE_STAINED_GLASS);
      v.add(Material.MAGENTA_STAINED_GLASS);
      v.add(Material.PURPLE_STAINED_GLASS);
      v.add(Material.WHITE_STAINED_GLASS);
      v.add(Material.PINK_STAINED_GLASS);
      return v;
    }

    @Override
    public NamespacedKey getKey() {
      return NamespacedKey.minecraft("stained_glass");
    }
  };

  private final SmartSettings smartSettings;
  private final WorldSettings worldSettings;

  private final Set<BlockBreakCommand> blockBreakCommands = new HashSet<>();
  private final List<VectorModifier> vectorModifiers = new ArrayList<>();

  BlockListener(SmartSettings smartSettings) {
    this.smartSettings = smartSettings;
    this.worldSettings = smartSettings.getWorldSettings();

    blockBreakCommands.add(new DiamondPickaxeDropCommand(smartSettings));
    blockBreakCommands.add(new GoldenAppleDropCommand(smartSettings));
    blockBreakCommands.add(new StainedGlassDropCommand(smartSettings));
    blockBreakCommands.add(new GlassToSandDropCommand(smartSettings));
    blockBreakCommands.add(new EnderChestDropCommand());

    vectorModifiers.add(new DxModifier());
    vectorModifiers.add(new DyModifier());
  }

  @EventHandler
  public void onbreak(BlockBreakEvent event) {
    event.setCancelled((!event.getPlayer().hasPermission(
        Permissions.MODIFY_BLOCK) && !smartSettings.isNoPermissionsNeeded() || event
        .isCancelled()));
  }

  @EventHandler(priority = EventPriority.LOW)
  public void onbuild(BlockPlaceEvent event) {
    event.setCancelled((!event.getPlayer().hasPermission(
        Permissions.MODIFY_BLOCK) && !smartSettings.isNoPermissionsNeeded()) || event
        .isCancelled());
  }

  @EventHandler
  public void onPhysics(BlockPhysicsEvent e) {
    if (e.getBlock().getType().equals(Material.TORCH)) {
      e.setCancelled(smartSettings.isPhysicsTorch());
    }
    if (e.getBlock().getType().equals(Material.SAND)) {
      e.setCancelled(smartSettings.isPhysicsSand());
    }
    if (e.getBlock().getType().equals(Material.GRAVEL)) {
      e.setCancelled(smartSettings.isPhysicsGravel());
    }
    if (e.getBlock().getType().equals(Material.DIAMOND_BLOCK)) {
      if (e.getBlock().getBlockPower() == 1) {
        e.getBlock()
            .getWorld()
            .getBlockAt(e.getBlock().getLocation().getBlockX(),
                e.getBlock().getLocation().getBlockY() + 5,
                e.getBlock().getLocation().getBlockZ())
            .setType(Material.WATER);
      } else {
        e.getBlock()
            .getWorld()
            .getBlockAt(e.getBlock().getLocation().getBlockX(),
                e.getBlock().getLocation().getBlockY() + 5,
                e.getBlock().getLocation().getBlockZ())
            .setType(Material.AIR);
      }
    }

  }

  @EventHandler
  public void spawny(BlockDispenseEvent e) {
    if (e.getItem().getType().equals(Material.MINECART)
        || e.getItem().getType().equals(Material.CHEST_MINECART) && e.getBlock()
        .getState() instanceof Dispenser) {
      Dispenser dp = (Dispenser) e.getBlock().getState();
      dp.getInventory().addItem(e.getItem());
    }
  }

  @EventHandler
  public void onblockburn(BlockBurnEvent event) {
    event.setCancelled(worldSettings.isPreventFireSpread());
  }

  @EventHandler
  public void onblockig(BlockIgniteEvent event) {
    switch (event.getCause()) {
      case LAVA:
        event.setCancelled(worldSettings.isPreventLavaSpread());
        break;
      case LIGHTNING:
        event.setCancelled(worldSettings.isStrikeSpread());
        break;
      case SPREAD:
        event.setCancelled(worldSettings.isGeneralSpread());
        break;
      default:
    }
  }

  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    if (!event.isCancelled()) {
      if (event.getPlayer().getInventory().getItemInMainHand().getEnchantments()
          .containsKey(Enchantment.LOOT_BONUS_BLOCKS)) {
        return;
      }

      blockBreakCommands.stream().filter(blockBreakCommand -> blockBreakCommand
          .isConditionFullfilled(event.getPlayer(), event.getBlock())).findFirst().ifPresent(
          blockBreakCommand -> blockBreakCommand
              .execute(event.getPlayer(), event.getBlock(), event));

    }

  }

  @EventHandler
  public void onRclickChest(PlayerInteractEvent e) {
    if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
      if (!(e.getClickedBlock().getType().equals(Material.CHEST)
          || e.getClickedBlock().getType()
          .equals(Material.ENDER_CHEST) || e
          .getClickedBlock().getType().equals(Material.CRAFTING_TABLE))) {
        return;
      }
      if (e.getPlayer().hasPermission(Permissions.INTERACT)) {
        return;
      }
      e.setCancelled(true);
      e.getPlayer().sendMessage(
          ChatColor.RED + "Access permitted" + ChatColor.RESET);
    }
  }

  @EventHandler
  public void openBenches(PlayerInteractEvent e) {

    if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {

      if (e.getItem().getType().equals(Material.CRAFTING_TABLE)
          && e.getPlayer().hasPermission(Permissions.OPEN_VIRTUAL_WORKBENCH)) {
        e.getPlayer().openWorkbench(e.getPlayer().getLocation(), true);
      } else if (e.getItem().getType().equals(Material.ENDER_CHEST)
          && e.getPlayer().hasPermission(Permissions.OPEN_VIRTUAL_ENDER_CHEST)) {
        e.getPlayer().openInventory(e.getPlayer().getEnderChest());
      } else if (e.getItem().getType().equals(Material.ENCHANTING_TABLE)
          && e.getPlayer().hasPermission(
          Permissions.OBEN_VIRTUAL_ENDCHANTING_TABLE)) {
        e.getPlayer().openEnchanting(e.getPlayer().getLocation(), true);

      }
    }
  }

  @EventHandler
  public void spongejump(PlayerMoveEvent ev) {

    if (getBlockUnderFeet(ev.getPlayer(), 1.0D).getType() == Material.SPONGE
        && !ev.getPlayer().isSneaking()) {

      ItemStack boots = ev.getPlayer().getInventory().getBoots();

      if (boots != null && boots.hasItemMeta()) {
        ItemMeta itemMeta = boots.getItemMeta();

        if (itemMeta.hasDisplayName() && itemMeta.getDisplayName().equalsIgnoreCase("Jump")) {

          Vector direction = new Vector(0, 2.4D, 0);

          vectorModifiers.forEach(vectorModifier -> vectorModifier
              .execute(getBlockUnderFeet(ev.getPlayer(), vectorModifier.depth()), direction));
          ev.getPlayer().setVelocity(direction);
        }
      }
    }
  }

  private Block getBlockUnderFeet(Player p,
      double minusY) {
    return new Location(p.getWorld(), p.getLocation().getX() - (double) 0, p
        .getLocation().getY() - minusY,
        p.getLocation().getZ() - (double) 0, p.getLocation().getPitch(), p
        .getLocation().getYaw()).getBlock();

  }

  @EventHandler
  public void onplayerrBed(PlayerInteractEvent event) {
    if (event.getPlayer().hasPermission(Permissions.MODIFY_BLOCK)
        && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)
        && (event.getClickedBlock().getBlockData() instanceof Bed)) {

      event.setCancelled(true);
      event.getPlayer().setPlayerTime(42000, true);
      event.getPlayer().setBedSpawnLocation(
          event.getClickedBlock().getLocation());
      event.getPlayer().sendMessage(
          ChatColor.BLUE + "Bedspawn location set!");
      event.setCancelled(false);
      event.getPlayer().resetPlayerTime();
    }
  }

  @EventHandler
  public void bedenter(PlayerBedEnterEvent e) {
    e.setCancelled(false);
  }
}
