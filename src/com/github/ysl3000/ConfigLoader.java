package com.github.ysl3000;

public class ConfigLoader {

	private SmartServerTool plugin;
	private static boolean messageing;
	private static String firstjoin;
	private static String joinmessage;
	private static String leftmessage;
	private static int timezone;
	private static String timemessage;
	private static String coremessage;
	private static String whitelistmessage;
	private static String banmessage;
	private static String fullmessage;
	private static boolean maintenance;
	private static String maintenance_message;
	private static boolean advert;
	private static long adverttime;
	private static String advertmessage;
	private static boolean glass;
	private static boolean glasspanes;
	private static boolean diamond;
	private static boolean appleDrop;
	private static boolean commandlogger;
	
	
	private static boolean Bbuild;
	private static boolean Blockbreak;
	private static boolean Bcreeper;
	private static boolean Bender;
	private static boolean xpsave;
	private static boolean tntsave;
	private static int dropchance;
	private static int diamondDropChance;
	private static boolean blockburn;
	private static boolean lavaspread;
	private static boolean normalspread;
	private static boolean flint_and_steal_spread;
	private static boolean lightning_spread;
	
	
	public ConfigLoader(SmartServerTool smartServerTool) {
		this.plugin = smartServerTool;
		firstjoin = this.plugin.getConfig().getString("firstjoin");
		joinmessage = this.plugin.getConfig().getString("message");
		leftmessage = this.plugin.getConfig().getString("leftmessage");
		timezone = this.plugin.getConfig().getInt("timezone");
		timemessage = this.plugin.getConfig().getString("timemessage");
		whitelistmessage = this.plugin.getConfig().getString(
				"whitelist-message");
		banmessage = this.plugin.getConfig().getString("banmessage");
		fullmessage = this.plugin.getConfig().getString("serverfullmessage");
		maintenance = this. plugin.getConfig().getBoolean("maintenance_mode");
		maintenance_message = this.plugin.getConfig().getString(
				"maintenance_message");

		advert = this.plugin.getConfig().getBoolean("plugin-advert");
		adverttime = this.plugin.getConfig().getLong("time-between-adverts");
		advertmessage = this.plugin.getConfig().getString("advert-message");
		diamondDropChance = this.plugin.getConfig().getInt("diamond-drop-rate");
		glass = this.plugin.getConfig().getBoolean("glass-sand-drop");
		glasspanes = this.plugin.getConfig().getBoolean("glasspane-drop");
		diamond = this.plugin.getConfig().getBoolean("diamond-ore-drop");
		appleDrop = this.plugin.getConfig().getBoolean("golden-apple-drop");
		messageing = this.plugin.getConfig().getBoolean("enable-Messages");
		commandlogger = this.plugin.getConfig().getBoolean("commandLogger");

		
		Bbuild = this.plugin.getConfig().getBoolean("disallowbuild");
		Blockbreak = this.plugin.getConfig().getBoolean("disablebreak");
		Bcreeper = this.plugin.getConfig().getBoolean("Blockcreeper");
		Bender = this.plugin.getConfig().getBoolean("Blockender");
		xpsave = this.plugin.getConfig().getBoolean("xpsave");
		tntsave = this.plugin.getConfig().getBoolean("prevent-tnt");
		dropchance = this.plugin.getConfig().getInt("normal-drop-rate");
		blockburn = this.plugin.getConfig().getBoolean("prevent-fire-spread");
		lavaspread = this.plugin.getConfig().getBoolean("prevent-lava-spread");
		normalspread = this.plugin.getConfig().getBoolean("general-spread");
		flint_and_steal_spread = this.plugin.getConfig().getBoolean(
				"flint-and-steal-spread");
		lightning_spread = this.plugin.getConfig().getBoolean("strike-spread");
	}
	
	public static boolean getCommandLogger(){
		return commandlogger;
	}
	public static boolean getappleDrop() {
		return appleDrop;
	}
	public static boolean getDiamondDrop() {
		return diamond;
	}
	public static boolean getadvert() {
		return advert;

	}
	public static long getAdvertTime() {
		return adverttime;
	}
	public static String getAdvertMessage() {
		return advertmessage;
	}
	public static boolean getGlasspaneDrop() {
		return glasspanes;
	}
	public static boolean isMessaging() {
		return messageing;
	}
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
	public static String getCoremessage(){
		return coremessage;
	}
	public static String getWhitelistmessage(){
		return whitelistmessage;
	}
	public static String getBanmessage(){
		return banmessage;
	}
	public static String getFullmessage(){
		return fullmessage;
	}
	public static boolean getMaintenance(){
		return maintenance;
	}
	public static String getMaintenanceMessage(){
		return maintenance_message;
	}
	public static boolean getGlassSandDrop(){
		return glass;
	}
	public static int getDiamondDropChance(){
		return diamondDropChance;
	}

	
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
	public static boolean isXpsave() {
		return xpsave;
	}
	public static boolean isTntsave() {
		return tntsave;
	}
	public static int getDropchance() {
		return dropchance;
	}
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
	public static void setMaintenance(boolean maintenanc){
		maintenance = maintenanc;
		
	}
	
}
