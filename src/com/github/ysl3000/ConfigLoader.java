package com.github.ysl3000;

public class ConfigLoader {

	private SmartServerTool plugin;

	// enable/disable chat-message
	private static boolean messageing;

	// chat messages
	private static String firstjoin;
	private static String joinmessage;
	private static String leftmessage;
	private static int timezone;
	private static String timemessage;
	private static boolean advert;
	private static long adverttime;
	private static String advertmessage;

	// no connect message
	private static String whitelistmessage;
	private static String banmessage;
	private static String fullmessage;
	private static boolean maintenance;
	private static String maintenance_message;

	// drop
	private static boolean glass;
	private static boolean glassPane;
	private static boolean diamond;
	private static boolean appleDrop;
	private static boolean xpsave;
	// dropchance
	private static int diamondDropChance;
	private static int appleDropChance;
	private static int glassSandDropChance;
	private static int glassPaneDropChance;

	// commandlogger
	private static boolean commandlogger;

	// world setting
	private static boolean Bbuild;
	private static boolean Blockbreak;
	private static boolean Bcreeper;
	private static boolean Bender;
	private static boolean tntsave;

	// spread
	private static boolean blockburn;
	private static boolean lavaspread;
	private static boolean normalspread;
	private static boolean flint_and_steal_spread;
	private static boolean lightning_spread;
	
	//Nice Feature
	private static boolean PlayerPressPlate;
	
	//saving time
	private static int savetime;

	public ConfigLoader(SmartServerTool smartServerTool) {
		this.plugin = smartServerTool;

		// enable/disable chat-message
		messageing = this.plugin.getConfig().getBoolean("message.enable-Messages");

		// chat messages
		firstjoin = this.plugin.getConfig().getString("message.firstjoin");
		joinmessage = this.plugin.getConfig().getString("message.message");
		leftmessage = this.plugin.getConfig().getString("message.leftmessage");
		timezone = this.plugin.getConfig().getInt("timezone");
		timemessage = this.plugin.getConfig().getString("message.timemessage");

		// Advertising
		advert = this.plugin.getConfig().getBoolean("advertising.plugin-advert");
		adverttime = this.plugin.getConfig().getLong("advertising.time-between-adverts");
		advertmessage = this.plugin.getConfig().getString("advertising.advert-message");

		// no connect message
		whitelistmessage = this.plugin.getConfig().getString(
				"message.whitelist-message");
		banmessage = this.plugin.getConfig().getString("message.banmessage");
		fullmessage = this.plugin.getConfig().getString("message.serverfullmessage");
		maintenance = this.plugin.getConfig().getBoolean("message.maintenance_mode");
		maintenance_message = this.plugin.getConfig().getString(
				"message.maintenance_message");

		// commandlogger
		commandlogger = this.plugin.getConfig().getBoolean("commandLogger");

		// world setting
		Bbuild = this.plugin.getConfig().getBoolean("world-setting.disallowbuild");
		Blockbreak = this.plugin.getConfig().getBoolean("world-setting.disablebreak");
		Bcreeper = this.plugin.getConfig().getBoolean("world-setting.Blockcreeper");
		Bender = this.plugin.getConfig().getBoolean("world-setting.Blockender");

		tntsave = this.plugin.getConfig().getBoolean("world-setting.prevent-tnt");

		// drop
		glass = this.plugin.getConfig().getBoolean("drops.glass-sand-drop");
		glassPane = this.plugin.getConfig().getBoolean("drops.glassPane-drop");
		diamond = this.plugin.getConfig().getBoolean("drops.diamond-ore-drop");
		appleDrop = this.plugin.getConfig().getBoolean("drops.apple-drop");
		xpsave = this.plugin.getConfig().getBoolean("world-setting.xpsave");

		// dropChance
		diamondDropChance = this.plugin.getConfig().getInt("droprates.diamond-drop-rate");
		appleDropChance = this.plugin.getConfig().getInt("droprates.apple-drop-rate");
		glassSandDropChance = this.plugin.getConfig().getInt("droprates.glass-sand-drop-rate");
		glassPaneDropChance = this.plugin.getConfig().getInt("droprates.glassPane-drop-rate");

		// spread
		blockburn = this.plugin.getConfig().getBoolean("world-setting.prevent-fire-spread");
		lavaspread = this.plugin.getConfig().getBoolean("world-setting.prevent-lava-spread");
		normalspread = this.plugin.getConfig().getBoolean("world-setting.general-spread");
		flint_and_steal_spread = this.plugin.getConfig().getBoolean(
				"world-setting.flint-and-steal-spread");
		lightning_spread = this.plugin.getConfig().getBoolean("world-setting.strike-spread");
		
		//Nice features
		PlayerPressPlate = this.plugin.getConfig().getBoolean("Misc.Save-Player-PressPlate");
		
		//saving time
		savetime = this.plugin.getConfig().getInt("config-save-interval");
	}

	//nice feature
	
	public static boolean isPlayerPressPlate(){
		return PlayerPressPlate;
	}
	
	// messaging
	public static boolean isMessaging() {
		return messageing;
	}

	// chat-message

	public static String getFirstJoinMessage() {
		return firstjoin;
	}

	public static String getLeftmessage() {
		return leftmessage;
	}

	public static int getTimezone() {
		return timezone;
	}

	public static String getTimemessage() {
		return timemessage;
	}

	public static String getJoinmessage() {
		return joinmessage;
	}

	// advertising
	public static boolean getadvert() {
		return advert;

	}

	public static long getAdvertTime() {
		return adverttime;
	}

	public static String getAdvertMessage() {
		return advertmessage;
	}

	// no connect message
	public static String getWhitelistmessage() {
		return whitelistmessage;
	}

	public static String getBanmessage() {
		return banmessage;
	}

	public static String getFullmessage() {
		return fullmessage;
	}

	public static boolean getMaintenance() {
		return maintenance;
	}

	public static String getMaintenanceMessage() {
		return maintenance_message;
	}

	public static void setMaintenance(boolean maintenanc) {
		maintenance = maintenanc;

	}

	// commandlogger
	public static boolean getCommandLogger() {
		return commandlogger;
	}

	// world settings
	public static boolean isBbuild() {
		return Bbuild;
	}

	public static boolean isBlockbreak() {
		return Blockbreak;
	}

	public static boolean isBcreeper() {
		return Bcreeper;
	}

	public static boolean isBender() {
		return Bender;
	}

	public static boolean isTntsave() {
		return tntsave;
	}

	// drop
	public static boolean isXpsave() {
		return xpsave;
	}

	public static boolean isappleDrop() {
		return appleDrop;
	}

	public static boolean isDiamondDrop() {
		return diamond;
	}

	public static boolean isGlassPaneDrop() {
		return glassPane;
	}

	public static boolean isGlassSandDrop() {
		return glass;
	}

	// dropchance
	public static int getGlassSandDropChance() {

		return glassSandDropChance;

	}

	public static int getDiamondDropChance() {
		return diamondDropChance;
	}

	public static int getAppleDropChance() {

		return appleDropChance;
	}
	
	public static int getGlassPaneDropChance(){
		return glassPaneDropChance;
	}

	// spread
	public static boolean isBlockburn() {
		return blockburn;
	}

	public static boolean isLavaspread() {
		return lavaspread;
	}

	public static boolean isNormalspread() {
		return normalspread;
	}

	public static boolean isFlint_and_steal_spread() {
		return flint_and_steal_spread;
	}

	public static boolean isLightning_spread() {
		return lightning_spread;
	}
	
	
	//save time
	
	public static int getSaveTimeInterval(){
		
		return savetime;
	}

}
