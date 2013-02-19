package com.github.ysl3000;

public class ConfigLoader {

	private SmartServerTool plugin;
	
	//enable/disable Block Player joining
	
	private static boolean playerJoining;

	// enable/disable chat-message
	private static boolean messageing;
	

	// chat messages
	private static String firstjoin;
	private static String joinmessage;
	private static String privateJoinMessage;
	private static String leftmessage;
	private static String userAmountMessage;
	private static int timezone;
	private static String timemessage;
	private static boolean chatcolor;
	private static String timeformattype;
	
	
	//advert
	private static boolean advert;
	private static long adverttime;
	private static String advertmessage;
	private static String advertPrefix;

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
	private static boolean appleshear;
	// dropchance
	private static int diamondDropChance;
	private static int appleDropChance;
	private static int glassSandDropChance;
	private static int glassPaneDropChance;

	// commandlogger
	private static boolean commandlogger;

	// world setting
	private static boolean Bcreeper;
	private static boolean Bender;
	private static boolean tntsave;

	// spread
	private static boolean blockburn;
	private static boolean lavaspread;
	private static boolean normalspread;
	private static boolean lightning_spread;

	// Nice Feature
	private static boolean PlayerPressPlate;
	private static boolean sleepingIgnored;
	private static String ap;

	
	private static int defaultstack;

	// saving time
	private static int savetime;
	
	//unlocking
	private static String answer;
	private static String dgn;
	private static String question;
	
	//physics
	private static boolean physicsSand;
	private static boolean physicsGravel;
	private static boolean physicsTorch;
	private static boolean physicsTrapdoor;
	
	//non-permission-user
	private static boolean nopermission_needed;
	

	public ConfigLoader(SmartServerTool smartServerTool) {
		this.plugin = smartServerTool;

		//enable/disable Player Joining
		
		playerJoining = this.plugin.getConfig().getBoolean("Join.Player");
		
		// enable/disable chat-message
		messageing = this.plugin.getConfig().getBoolean(
				"message.enable-Messages");

		// chat messages
		firstjoin = this.plugin.getConfig().getString("message.firstjoin");
		joinmessage = this.plugin.getConfig().getString("message.message");
		privateJoinMessage = this.plugin.getConfig().getString("message.privatejoinmessage");
		leftmessage = this.plugin.getConfig().getString("message.leftmessage");
		timezone = this.plugin.getConfig().getInt("timezone");
		timemessage = this.plugin.getConfig().getString("message.timemessage");
		chatcolor = this.plugin.getConfig().getBoolean("message.enable-random-chatcolor");
		

		// Advertising
		advert = this.plugin.getConfig()
				.getBoolean("advertising.plugin-advert");
		adverttime = this.plugin.getConfig().getLong(
				"advertising.time-between-adverts");
		advertmessage = this.plugin.getConfig().getString(
				"advertising.advert-message");
		advertPrefix = this.plugin.getConfig().getString("advertising.advert-prefix");

		// no connect message
		whitelistmessage = this.plugin.getConfig().getString(
				"message.whitelist-message");
		banmessage = this.plugin.getConfig().getString("message.banmessage");
		fullmessage = this.plugin.getConfig().getString(
				"message.serverfullmessage");
		maintenance = this.plugin.getConfig().getBoolean(
				"message.maintenance_mode");
		maintenance_message = this.plugin.getConfig().getString(
				"message.maintenance_message");
		timeformattype = this.plugin.getConfig().getString("message.timeformat");

		// commandlogger
		commandlogger = this.plugin.getConfig().getBoolean("commandLogger");

		// world setting
		Bcreeper = this.plugin.getConfig().getBoolean(
				"world-setting.Blockcreeper");
		Bender = this.plugin.getConfig().getBoolean("world-setting.Blockender");

		tntsave = this.plugin.getConfig().getBoolean(
				"world-setting.prevent-tnt");

		// drop
		glass = this.plugin.getConfig().getBoolean("drops.glass-sand-drop");
		glassPane = this.plugin.getConfig().getBoolean("drops.glassPane-drop");
		diamond = this.plugin.getConfig().getBoolean("drops.diamond-ore-drop");
		appleDrop = this.plugin.getConfig().getBoolean("drops.apple-drop");
		xpsave = this.plugin.getConfig().getBoolean("world-setting.xpsave");
		appleshear = this.plugin.getConfig().getBoolean("drops.goldenapple-shear");
		
		// dropChance
		diamondDropChance = this.plugin.getConfig().getInt(
				"chance.diamond-drop-rate");
		appleDropChance = this.plugin.getConfig().getInt(
				"chance.apple-drop-rate");
		glassSandDropChance = this.plugin.getConfig().getInt(
				"chance.glass-sand-drop-rate");
		glassPaneDropChance = this.plugin.getConfig().getInt(
				"chance.glassPane-drop-rate");

		// spread
		blockburn = this.plugin.getConfig().getBoolean(
				"world-setting.prevent-fire-spread");
		lavaspread = this.plugin.getConfig().getBoolean(
				"world-setting.prevent-lava-spread");
		normalspread = this.plugin.getConfig().getBoolean(
				"world-setting.general-spread");
		
		lightning_spread = this.plugin.getConfig().getBoolean(
				"world-setting.strike-spread");

		// Nice features
		PlayerPressPlate = this.plugin.getConfig().getBoolean(
				"Misc.Save-Player-PressPlate");
		sleepingIgnored = this.plugin.getConfig().getBoolean("Misc.Sleeping-Ignored");
		ap = this.plugin.getConfig().getString("Misc.adminchat-password");
		
		defaultstack = this.plugin.getConfig().getInt("Misc.item-amount");
		// saving time
		savetime = this.plugin.getConfig().getInt("config-save-interval");
		//unlocking
		answer = this.plugin.getConfig().getString("Question.answer");
		dgn = this.plugin.getConfig().getString("Question.unlockGroupName");
		question = this.plugin.getConfig().getString("Question.question");
		
		//physics
		physicsGravel = this.plugin.getConfig().getBoolean("disable-physics.Gravel");
		physicsSand = this.plugin.getConfig().getBoolean("disable-physics.Sand");
		physicsTorch = this.plugin.getConfig().getBoolean("disable-physics.Torch");
		physicsTrapdoor = this.plugin.getConfig().getBoolean("disable-physics.Trapdoor");
		nopermission_needed = this.plugin.getConfig().getBoolean("disable.permission");
	}

	//disable/enable PlayerJoining
	
	public static boolean isBloggingPlayerJoin(){
		return playerJoining;
	}
	
	// nice feature Misc

	public static boolean isPlayerPressPlate() {
		return PlayerPressPlate;
	}
	
	public static int getDefaultStack(){
		return defaultstack;
	}
	public static boolean isSleepingIgnored(){
		return sleepingIgnored;
	}
	public static String getAdminpassword(){
		
		return ap;
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
	public static String getPrivatJoinMessage(){
		return privateJoinMessage;
	}
	
	public static String getuserAmountMessage(){
		return userAmountMessage;
	}
	
	public static boolean getRandomColor(){
		return chatcolor;
	}
	public static String getTimeFormat(){
		return timeformattype;
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

	public static String getAdvertPrefix(){
		return advertPrefix;
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

	// commandlogger
	public static boolean getCommandLogger() {
		return commandlogger;
	}

	// world settings
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
	public static boolean isAppleShear(){
		return appleshear;
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

	public static int getGlassPaneDropChance() {
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

	public static boolean isLightning_spread() {
		return lightning_spread;
	}

	// save time

	public static int getSaveTimeInterval() {

		return savetime;
	}
	
	//unlocking
	public static String getAnswer(){
		return answer;
	}
	public static String getDGN(){
		return dgn;
	}
	public static String getQuestion(){
		return question;
	}
	
	//physics
	
	public static boolean getPhysicsSand(){
		return physicsSand;
	}
	public static boolean getPhysicsTorch(){
		return physicsTorch;
	}
	public static boolean getPhysicsTrapdoor(){
		return physicsTrapdoor;
	}
	public static boolean getPhysicsGravel(){
		return physicsGravel;
	}
	
	public static boolean getNonPermission(){
		return nopermission_needed;
	}

}
