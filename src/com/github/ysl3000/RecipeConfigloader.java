package com.github.ysl3000;

public class RecipeConfigloader {

	private SmartServerTool plugin;

	private static boolean enableEnchantmentTable;
	private static boolean enableSponge;
	private static boolean enableEggs;
	private static boolean enableGlowStone;
	private static boolean enableIronIngot;
	private static boolean enableGoldIngot;
	private static boolean enableDiamond;
	private static boolean enableWheat;
	private static boolean enableGrass;
	private static boolean enableNetherBrick;
	private static boolean enableCobbleStone;
	private static boolean enableNet;
	private static boolean enableSaddle;
	private static boolean enableLongGrass;
	private static boolean enableChainHelmet;
	private static boolean enableChainChest;
	private static boolean enableChainLegg;
	private static boolean enableChainFeet;
	private static boolean enableCommandBlock;

	public RecipeConfigloader(SmartServerTool smartServerTool) {
		this.plugin = smartServerTool;

		enableGlowStone = this.plugin.getRecipeConfig().getBoolean("Furnace.GlowStone");
		enableIronIngot = this.plugin.getRecipeConfig().getBoolean("Furnace.IronIngot");
		enableGoldIngot = this.plugin.getRecipeConfig().getBoolean("Furnace.GoldIngot");
		enableDiamond = this.plugin.getRecipeConfig().getBoolean("Furnace.Diamond");
		enableNetherBrick = this.plugin.getRecipeConfig().getBoolean("Furnace.NetherBrick");
		enableSponge = this.plugin.getRecipeConfig().getBoolean("Furnace.Sponge");
		enableEnchantmentTable = this.plugin.getRecipeConfig().getBoolean("Crafting.EntchantmentTable");
		enableEggs = this.plugin.getRecipeConfig().getBoolean("Crafting.SpawnEggs");
		enableWheat = this.plugin.getRecipeConfig().getBoolean("Crafting.Wheat");
		enableGrass = this.plugin.getRecipeConfig().getBoolean("Crafting.Grass");
		enableCobbleStone = this.plugin.getRecipeConfig().getBoolean("Crafting.CobbleStone");
		enableNet = this.plugin.getRecipeConfig().getBoolean("Crafting.Net");
		enableSaddle = this.plugin.getRecipeConfig().getBoolean("Crafting.Saddle");
		enableLongGrass = this.plugin.getRecipeConfig().getBoolean("Crafting.LongGrass");
		enableChainHelmet = this.plugin.getRecipeConfig().getBoolean("Crafting.Chain.Helmet");
		enableChainChest = this.plugin.getRecipeConfig().getBoolean("Crafting.Chain.Chest");
		enableChainLegg = this.plugin.getRecipeConfig().getBoolean("Crafting.Chain.Legg");
		enableChainFeet = this.plugin.getRecipeConfig().getBoolean("Crafting.Chain.Feet");
		enableCommandBlock = this.plugin.getRecipeConfig().getBoolean("Crafting.CommandBlock");
	}
	
	public static boolean getisEntchantmentTable(){
		return enableEnchantmentTable;
	}

	public static boolean getisEggEnabled() {
		return enableEggs;
	}

	public static boolean getisSpongeEnabled() {
		return enableSponge;
	}

	public static boolean getisGlowStoneEnabled() {
		return enableGlowStone;
	}

	public static boolean getisIronIngotEnabled() {
		return enableIronIngot;
	}

	public static boolean getisGoldIngotEnabled() {
		return enableGoldIngot;
	}

	public static boolean getisDiamondEnabled() {
		return enableDiamond;
	}

	public static boolean getisWheatEnabled() {
		return enableWheat;
	}

	public static boolean getisGrassEnabled() {
		return enableGrass;
	}

	public static boolean getisNetherBrickEnabled() {
		return enableNetherBrick;
	}

	public static boolean getisCobbleStoneEnabled() {
		return enableCobbleStone;
	}

	public static boolean getisNetEnabled() {
		return enableNet;
	}

	public static boolean getisSaddleEnabled() {
		return enableSaddle;
	}

	public static boolean getisLongGrassEnabled() {
		return enableLongGrass;
	}

	public static boolean getisChainHelmet() {
		return enableChainHelmet;
	}

	public static boolean getisChainChestEnabled() {
		return enableChainChest;
	}

	public static boolean getisChainLeggEnabled() {
		return enableChainLegg;
	}

	public static boolean getisChainFeetEnabled() {
		return enableChainFeet;
	}

	public static boolean getisCommandBlockEnabled() {
		return enableCommandBlock;
	}
}
