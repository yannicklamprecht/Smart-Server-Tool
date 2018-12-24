package com.ysl3000.events;

import com.ysl3000.config.SmartSettings;
import com.ysl3000.utils.Permissions;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.block.data.type.Bed;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class BlockListener implements Listener {


    Tag<Material> STAINED_GLASS = new Tag<Material>() {
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
    };

    private SmartSettings smartSettings;

    public BlockListener(SmartSettings smartSettings) {
        this.smartSettings = smartSettings;
    }

    @EventHandler
    public void onbreak(BlockBreakEvent event) {
        event.setCancelled((!event.getPlayer().hasPermission(
                Permissions.modifyBlock) && !smartSettings.isNoPermissionsNeeded() || event.isCancelled()));
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onbuild(BlockPlaceEvent event) {
        event.setCancelled((!event.getPlayer().hasPermission(
                Permissions.modifyBlock) && !smartSettings.isNoPermissionsNeeded()) || event.isCancelled());
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
                || e.getItem().getType().equals(Material.CHEST_MINECART)) {
            if (e.getBlock().getState() instanceof Dispenser) {
                Dispenser dp = (Dispenser) e.getBlock().getState();
                dp.getInventory().addItem(e.getItem());
            }
        }
    }

    @EventHandler
    public void onblockburn(BlockBurnEvent event) {
        event.setCancelled(smartSettings.isFireSpread());
    }

    @EventHandler
    public void onblockig(BlockIgniteEvent event) {
        event.setCancelled(event.getCause().equals(IgniteCause.LAVA) ? smartSettings.isLavaSpread() : event.getCause()
                .equals(IgniteCause.LIGHTNING) ? smartSettings.isLightningSpread() : event
                .getCause().equals(IgniteCause.SPREAD) && smartSettings.isNormalSpread());
    }

    @EventHandler
    public void BlockDrops(BlockBreakEvent event) {
        if (!event.isCancelled()) {
            if (event.getPlayer().getInventory().getItemInMainHand().getEnchantments()
                    .containsKey(Enchantment.LOOT_BONUS_BLOCKS))
                return;
            Random rando = ThreadLocalRandom.current();
            if (event.getBlock().getType().equals(Material.DIAMOND_ORE)
                    && (rando.nextInt(smartSettings.getChance().getDiamond()) == 1
                    || smartSettings.getChance().getDiamond() == 1)
                    && smartSettings.getDrops().isDiamondOre()) {
                event.getBlock()
                        .getWorld()
                        .dropItem(event.getBlock().getLocation(),
                                new ItemStack(Material.DIAMOND_PICKAXE));
            } else if (Tag.LEAVES.isTagged(event.getBlock().getType())
                    && smartSettings.getDrops().isGoldenAppleShear()
                    && event.getPlayer().getInventory().getItemInMainHand().getType()
                    .equals(Material.SHEARS)) {
                event.getBlock()
                        .getWorld()
                        .dropItem(event.getBlock().getLocation(),
                                new ItemStack(Material.GOLDEN_APPLE, 1));

            } else if ((event.getBlock().getType().equals(Material.GLASS_PANE) || event
                    .getBlock().getType().equals(Material.BLACK_STAINED_GLASS))
                    && (rando.nextInt(smartSettings.getChance().getGlassPane()) == 1
                    || smartSettings.getChance().getGlassPane() == 1)
                    && smartSettings.getDrops().isGlassPane()) {

                if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                    event.getBlock()
                            .getWorld()
                            .dropItem(
                                    event.getBlock().getLocation(),
                                    new ItemStack(event.getBlock().getType(), 1));
                }
            } else if ((event.getBlock().getType().equals(Material.GLASS)
                    || STAINED_GLASS.isTagged(event
                    .getBlock().getType()))
                    && (rando.nextInt(smartSettings.getChance().getGlassSand()) == 1
                    || smartSettings.getChance().getGlassSand() == 1)
                    && smartSettings.getDrops().isGlassSand()) {
                if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                    event.getBlock()
                            .getWorld()
                            .dropItem(event.getBlock().getLocation(),
                                    new ItemStack(Material.SAND, 1));
                }

            } else if (event.getBlock().getType().equals(Material.ENDER_CHEST)) {
                event.setCancelled(true);
                event.getBlock().setType(Material.AIR);
                event.getBlock()
                        .getWorld()
                        .dropItemNaturally(event.getBlock().getLocation(),
                                new ItemStack(Material.ENDER_CHEST));
            }

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
            if (e.getPlayer().hasPermission(Permissions.interact)) {
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
                    && e.getPlayer().hasPermission(Permissions.openVWorkBench)) {
                e.getPlayer().openWorkbench(e.getPlayer().getLocation(), true);
            } else if (e.getItem().getType().equals(Material.ENDER_CHEST)
                    && e.getPlayer().hasPermission(Permissions.openEChest)) {
                e.getPlayer().openInventory(e.getPlayer().getEnderChest());
            } else if (e.getItem().getType().equals(Material.ENCHANTING_TABLE)
                    && e.getPlayer().hasPermission(
                    Permissions.openVEnchantingTable)) {
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
                    double dx;
                    double dy;
                    double dz;
                    dy = 2.4D;

                    if (getBlockUnderFeet(ev.getPlayer(), 2.0D)
                            .getType().equals(Material.IRON_BLOCK)) {
                        dx = 2.4D;
                    } else if (getBlockUnderFeet(ev.getPlayer(), 2.0D)
                            .getType().equals(Material.DIAMOND_BLOCK)) {
                        dx = -2.4D;
                    } else {
                        dx = 0.0D;
                    }


                    if (getBlockUnderFeet(ev.getPlayer(), 3.0D)
                            .getType().equals(Material.IRON_BLOCK) || getBlockUnderFeet(ev.getPlayer(), 3.0D)
                            .getType().equals(Material.DIAMOND_BLOCK)) {
                        dz = 2.4D;
                    } else {
                        dz = 0.0D;
                    }
                    Vector vec = new Vector(dx, dy, dz);
                    ev.getPlayer().setVelocity(vec);
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
        if (event.getPlayer().hasPermission(Permissions.modifyBlock)
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
