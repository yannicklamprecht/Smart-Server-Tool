package com.github.ysl3000;

import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.material.SpawnEgg;
import org.bukkit.plugin.java.JavaPlugin;

public class Recipes extends JavaPlugin {

	private SmartServerTool plugin;

	public Recipes(SmartServerTool plugin) {
		this.plugin = plugin;
		registerRecipes();
	}

	public void registerRecipes() {

		ShapedRecipe sr = new ShapedRecipe(new ItemStack(
				Material.ENCHANTMENT_TABLE, 1))
				.shape(new String[] { "   ", " b ", "www" })
				.setIngredient('b', Material.BOOKSHELF)
				.setIngredient('w', Material.WOOD);

		ShapedRecipe glows1 = new ShapedRecipe(
				new ItemStack(Material.GLOWSTONE)).shape(
				new String[] { "rr ", "rr ", "   " }).setIngredient('r',
				Material.REDSTONE_TORCH_ON);
		ShapedRecipe glows2 = new ShapedRecipe(
				new ItemStack(Material.GLOWSTONE)).shape(
				new String[] { " rr", " rr", "   " }).setIngredient('r',
				Material.REDSTONE_TORCH_ON);
		ShapedRecipe glows3 = new ShapedRecipe(
				new ItemStack(Material.GLOWSTONE)).shape(
				new String[] { "   ", "rr ", "rr " }).setIngredient('r',
				Material.REDSTONE_TORCH_ON);
		ShapedRecipe glows4 = new ShapedRecipe(
				new ItemStack(Material.GLOWSTONE)).shape(
				new String[] { "   ", " rr", " rr" }).setIngredient('r',
				Material.REDSTONE_TORCH_ON);

		ShapelessRecipe wheat = new ShapelessRecipe(new ItemStack(
				Material.SEEDS, 3)).addIngredient(Material.WHEAT);

		ShapedRecipe grass1 = new ShapedRecipe(new ItemStack(Material.GRASS, 2))
				.shape(new String[] { "ee ", "ee ", "   " }).setIngredient('e',
						Material.DIRT);
		ShapedRecipe grass2 = new ShapedRecipe(new ItemStack(Material.GRASS, 2))
				.shape(new String[] { " ee", " ee", "   " }).setIngredient('e',
						Material.DIRT);
		ShapedRecipe grass3 = new ShapedRecipe(new ItemStack(Material.GRASS, 2))
				.shape(new String[] { "   ", " ee", " ee" }).setIngredient('e',
						Material.DIRT);
		ShapedRecipe grass4 = new ShapedRecipe(new ItemStack(Material.GRASS, 2))
				.shape(new String[] { "   ", "ee ", "ee " }).setIngredient('e',
						Material.DIRT);

		ShapedRecipe oldcobble1 = new ShapedRecipe(new ItemStack(
				Material.MOSSY_COBBLESTONE, 1))
				.shape(new String[] { "g  ", "c  ", "   " })
				.setIngredient('g', Material.GRASS)
				.setIngredient('c', Material.COBBLESTONE);
		ShapedRecipe oldcobble2 = new ShapedRecipe(new ItemStack(
				Material.MOSSY_COBBLESTONE, 1))
				.shape(new String[] { " g ", " c ", "   " })
				.setIngredient('g', Material.GRASS)
				.setIngredient('c', Material.COBBLESTONE);
		ShapedRecipe oldcobble3 = new ShapedRecipe(new ItemStack(
				Material.MOSSY_COBBLESTONE, 1))
				.shape(new String[] { "  g", "  c", "   " })
				.setIngredient('g', Material.GRASS)
				.setIngredient('c', Material.COBBLESTONE);
		ShapedRecipe oldcobble4 = new ShapedRecipe(new ItemStack(
				Material.MOSSY_COBBLESTONE, 1))
				.shape(new String[] { "   ", "g  ", "c  " })
				.setIngredient('g', Material.GRASS)
				.setIngredient('c', Material.COBBLESTONE);
		ShapedRecipe oldcobble5 = new ShapedRecipe(new ItemStack(
				Material.MOSSY_COBBLESTONE, 1))
				.shape(new String[] { "   ", " g ", " c " })
				.setIngredient('g', Material.GRASS)
				.setIngredient('c', Material.COBBLESTONE);
		ShapedRecipe oldcobble6 = new ShapedRecipe(new ItemStack(
				Material.MOSSY_COBBLESTONE, 1))
				.shape(new String[] { "   ", "  g", "  c" })
				.setIngredient('g', Material.GRASS)
				.setIngredient('c', Material.COBBLESTONE);

		ShapedRecipe villager = new ShapedRecipe(
				new SpawnEgg((byte) 120).toItemStack(1))
				.shape(new String[] { "rcr", "rer", "rlr" })
				.setIngredient('r', Material.EMERALD)
				.setIngredient('c', Material.WORKBENCH)
				.setIngredient('l', Material.LEATHER)
				.setIngredient('e', Material.EGG);

		ShapedRecipe wolf = new ShapedRecipe(
				new SpawnEgg((byte) 95).toItemStack(1))
				.shape(new String[] { "rbr", "rer", "rcr" })
				.setIngredient('r', Material.EMERALD)
				.setIngredient('b', Material.BONE)
				.setIngredient('c', Material.ROTTEN_FLESH)
				.setIngredient('e', Material.EGG);

		ShapedRecipe schwein = new ShapedRecipe(
				new SpawnEgg((byte) 90).toItemStack(1))
				.shape(new String[] { "rbr", "rer", "rcr" })
				.setIngredient('r', Material.EMERALD)
				.setIngredient('b', Material.BONE)
				.setIngredient('c', Material.PORK)
				.setIngredient('e', Material.EGG);

		ShapedRecipe schaf = new ShapedRecipe(
				new SpawnEgg((byte) 91).toItemStack(1))
				.shape(new String[] { "rbr", "rer", "rcr" })
				.setIngredient('r', Material.EMERALD)
				.setIngredient('b', Material.BONE)
				.setIngredient('c', Material.WOOL)
				.setIngredient('e', Material.EGG);

		ShapedRecipe kuh = new ShapedRecipe(
				new SpawnEgg((byte) 92).toItemStack(1))
				.shape(new String[] { "rbr", "rer", "rcr" })
				.setIngredient('r', Material.EMERALD)
				.setIngredient('b', Material.BONE)
				.setIngredient('c', Material.RAW_BEEF)
				.setIngredient('e', Material.EGG);

		ShapedRecipe huhn = new ShapedRecipe(
				new SpawnEgg((byte) 93).toItemStack(1))
				.shape(new String[] { "rbr", "rer", "rcr" })
				.setIngredient('r', Material.EMERALD)
				.setIngredient('b', Material.BONE)
				.setIngredient('c', Material.RAW_CHICKEN)
				.setIngredient('e', Material.EGG);

		ShapelessRecipe netz1 = new ShapelessRecipe(new ItemStack(
				Material.STRING, 3)).addIngredient(Material.WEB);

		ShapedRecipe netz2 = new ShapedRecipe(new ItemStack(Material.WEB, 1))
				.shape(new String[] { "s s", " s ", "s s" }).setIngredient('s',
						Material.STRING);

		ShapelessRecipe sattel1 = new ShapelessRecipe(new ItemStack(
				Material.LEATHER, 3)).addIngredient(Material.SADDLE);

		ShapedRecipe sattel2 = new ShapedRecipe(new ItemStack(Material.SADDLE,
				1)).shape(new String[] { "l l", "lll", "   " }).setIngredient(
				'l', Material.LEATHER);

		ShapedRecipe sattel3 = new ShapedRecipe(new ItemStack(Material.SADDLE,
				1)).shape(new String[] { "   ", "l l", "lll" }).setIngredient(
				'l', Material.LEATHER);

		ShapelessRecipe LongGrass = new ShapelessRecipe(new ItemStack(
				Material.LONG_GRASS, 3, (short) 1))
				.addIngredient(Material.GRASS);

		ShapedRecipe chain_helm1 = new ShapedRecipe(new ItemStack(
				Material.CHAINMAIL_HELMET, 1))
				.shape(new String[] { "gdg", "g g", "   " })
				.setIngredient('g', Material.GLASS)
				.setIngredient('d', Material.DIAMOND);

		ShapedRecipe chain_helm2 = new ShapedRecipe(new ItemStack(
				Material.CHAINMAIL_HELMET, 1))
				.shape(new String[] { "   ", "gdg", "g g" })
				.setIngredient('g', Material.GLASS)
				.setIngredient('d', Material.DIAMOND);

		ShapedRecipe chain_brust = new ShapedRecipe(new ItemStack(
				Material.CHAINMAIL_CHESTPLATE, 1))
				.shape(new String[] { "g g", "gdg", "ggg" })
				.setIngredient('g', Material.GLASS)
				.setIngredient('d', Material.DIAMOND);

		ShapedRecipe chain_bein = new ShapedRecipe(new ItemStack(
				Material.CHAINMAIL_LEGGINGS, 1))
				.shape(new String[] { "gdg", "g g", "g g" })
				.setIngredient('g', Material.GLASS)
				.setIngredient('d', Material.DIAMOND);

		ShapedRecipe chain_fuss1 = new ShapedRecipe(new ItemStack(
				Material.CHAINMAIL_BOOTS, 1)).shape(
				new String[] { "g g", "g g", "   " }).setIngredient('g',
				Material.GLASS);

		ShapedRecipe chain_fuss2 = new ShapedRecipe(new ItemStack(
				Material.CHAINMAIL_BOOTS, 1)).shape(
				new String[] { "   ", "g g", "g g" }).setIngredient('g',
				Material.GLASS);
		ShapedRecipe commandBlock = new ShapedRecipe(new ItemStack(
				Material.COMMAND, 1))
				.shape(new String[] { "nmn", "mdm", "nmn" })
				.setIngredient('n', Material.NOTE_BLOCK)
				.setIngredient('m', Material.JUKEBOX)
				.setIngredient('d', Material.DIAMOND);

		FurnaceRecipe sponge = new FurnaceRecipe(new ItemStack(Material.SPONGE,
				1), Material.SANDSTONE);
		FurnaceRecipe ironingot1 = new FurnaceRecipe(new ItemStack(
				Material.IRON_INGOT, 1), Material.IRON_PICKAXE);
		FurnaceRecipe ironingot2 = new FurnaceRecipe(new ItemStack(
				Material.IRON_INGOT, 1), Material.IRON_AXE);
		FurnaceRecipe ironingot3 = new FurnaceRecipe(new ItemStack(
				Material.IRON_INGOT, 1), Material.IRON_HOE);
		FurnaceRecipe ironingot4 = new FurnaceRecipe(new ItemStack(
				Material.IRON_INGOT, 1), Material.IRON_SWORD);
		FurnaceRecipe goldingot1 = new FurnaceRecipe(new ItemStack(
				Material.GOLD_INGOT, 1), Material.GOLD_PICKAXE);
		FurnaceRecipe goldingot2 = new FurnaceRecipe(new ItemStack(
				Material.GOLD_INGOT, 1), Material.GOLD_AXE);
		FurnaceRecipe goldingot3 = new FurnaceRecipe(new ItemStack(
				Material.GOLD_INGOT, 1), Material.GOLD_HOE);
		FurnaceRecipe goldingot4 = new FurnaceRecipe(new ItemStack(
				Material.GOLD_INGOT, 1), Material.GOLD_SWORD);
		FurnaceRecipe diamond1 = new FurnaceRecipe(new ItemStack(
				Material.DIAMOND, 1), Material.DIAMOND_PICKAXE);
		FurnaceRecipe diamond2 = new FurnaceRecipe(new ItemStack(
				Material.DIAMOND, 1), Material.DIAMOND_AXE);
		FurnaceRecipe diamond3 = new FurnaceRecipe(new ItemStack(
				Material.DIAMOND, 1), Material.DIAMOND_HOE);
		FurnaceRecipe diamond4 = new FurnaceRecipe(new ItemStack(
				Material.DIAMOND, 1), Material.DIAMOND_SWORD);
		FurnaceRecipe nbrick = new FurnaceRecipe(new ItemStack(
				Material.NETHER_BRICK, 1), Material.NETHERRACK);

		this.plugin.getServer().addRecipe(sponge);
		this.plugin.getServer().addRecipe(sr);
		this.plugin.getServer().addRecipe(glows1);
		this.plugin.getServer().addRecipe(glows2);
		this.plugin.getServer().addRecipe(glows3);
		this.plugin.getServer().addRecipe(glows4);
		this.plugin.getServer().addRecipe(ironingot1);
		this.plugin.getServer().addRecipe(ironingot2);
		this.plugin.getServer().addRecipe(ironingot3);
		this.plugin.getServer().addRecipe(ironingot4);
		this.plugin.getServer().addRecipe(goldingot1);
		this.plugin.getServer().addRecipe(goldingot2);
		this.plugin.getServer().addRecipe(goldingot3);
		this.plugin.getServer().addRecipe(goldingot4);
		this.plugin.getServer().addRecipe(diamond1);
		this.plugin.getServer().addRecipe(diamond2);
		this.plugin.getServer().addRecipe(diamond3);
		this.plugin.getServer().addRecipe(diamond4);
		this.plugin.getServer().addRecipe(wheat);
		this.plugin.getServer().addRecipe(grass1);
		this.plugin.getServer().addRecipe(grass2);
		this.plugin.getServer().addRecipe(grass3);
		this.plugin.getServer().addRecipe(grass4);
		this.plugin.getServer().addRecipe(nbrick);
		this.plugin.getServer().addRecipe(oldcobble1);
		this.plugin.getServer().addRecipe(oldcobble2);
		this.plugin.getServer().addRecipe(oldcobble3);
		this.plugin.getServer().addRecipe(oldcobble4);
		this.plugin.getServer().addRecipe(oldcobble5);
		this.plugin.getServer().addRecipe(oldcobble6);
		this.plugin.getServer().addRecipe(villager);
		this.plugin.getServer().addRecipe(wolf);
		this.plugin.getServer().addRecipe(huhn);
		this.plugin.getServer().addRecipe(kuh);
		this.plugin.getServer().addRecipe(schaf);
		this.plugin.getServer().addRecipe(schwein);
		this.plugin.getServer().addRecipe(netz1);
		this.plugin.getServer().addRecipe(netz2);
		this.plugin.getServer().addRecipe(sattel1);
		this.plugin.getServer().addRecipe(sattel2);
		this.plugin.getServer().addRecipe(sattel3);
		this.plugin.getServer().addRecipe(LongGrass);
		this.plugin.getServer().addRecipe(chain_helm1);
		this.plugin.getServer().addRecipe(chain_helm2);
		this.plugin.getServer().addRecipe(chain_brust);
		this.plugin.getServer().addRecipe(chain_bein);
		this.plugin.getServer().addRecipe(chain_fuss1);
		this.plugin.getServer().addRecipe(chain_fuss2);
		this.plugin.getServer().addRecipe(commandBlock);
	}

}
