package com.ysl3000.utils;

import lib.ResourceYamlConfigLoader;

import org.bukkit.plugin.java.JavaPlugin;

public class RecipeConfigloader{

	private static RecipeConfig recipe;
	
	public static RecipeConfig createAndReturnRecipeConfig(JavaPlugin plugin){
		if(recipe==null){
			recipe = new RecipeConfig(plugin);
		}
		return recipe;
	}
}
class RecipeConfig extends ResourceYamlConfigLoader{

	public RecipeConfig(JavaPlugin plugin) {
		super("SmartServerTool/", "recipe.yml", true, plugin);
	}
	public boolean isCraftingGlowStone(){
		return this.config.getBoolean("Furnace.GlowStone");
	}
	public boolean isFurnaceIronIngot(){
		return this.config.getBoolean("Furnace.IronIngot");
	}
	
	public boolean isFurnaceGoldIngot(){
		return this.config.getBoolean("Furnace.GoldIngot");
	}
	public boolean isFurnaceDiamondIngot(){
		return this.config.getBoolean("Furnace.Diamond");
	}
	public boolean isFurnaceNetherBrick(){
		return this.config.getBoolean("Furnace.NetherBrick");
	}
	public boolean isFurnaceSponge(){
		return this.config.getBoolean("Furnace.Sponge");
	}
	public boolean isCraftingEnchantmentTable(){
		return this.config.getBoolean("Crafting.EntchantmentTable");
	}
	public boolean isCraftingEgg(){
		return this.config.getBoolean("Crafting.SpawnEggs");
	}
	public boolean isCrafttingWeed(){
		return this.config.getBoolean("Crafting.Wheat");
	}
	public boolean isCraftingGrass(){
		return this.config.getBoolean("Crafting.Grass");
	}
	public boolean isCraftingCobblestone(){
		return this.config.getBoolean("Crafting.CobbleStone");
	}
	public boolean isCraftingNet(){
		return this.config.getBoolean("Crafting.Net");
	}
	public boolean isCraftingSadle(){
		return this.config.getBoolean("Crafting.Saddle");
	}
	public boolean isCraftingLongGrass(){
		return this.config.getBoolean("Crafting.LongGrass");
	}
	public boolean isCraftingChainHelmet(){
		return this.config.getBoolean("Crafting.Chain.Helmet");
	}
	public boolean isCraftingChainChest(){
		return this.config.getBoolean("Crafting.Chain.Chest");
	}
	public boolean isCraftingChainLegg(){
		return this.config.getBoolean("Crafting.Chain.Legg");
	}
	public boolean isCraftingChainFeet(){
		return this.config.getBoolean("Crafting.Chain.Feet");
	}
	public boolean isCraftingCommandBlock(){
		return this.config.getBoolean("Crafting.CommandBlock");
	}
	
}
