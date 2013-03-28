package com.github.ysl3000.Utils;

import com.github.ysl3000.SmartServerTool;

public class ConfigLoader {

	private SmartServerTool plugin;
	
	//enable/disable Block Player joining
	
	private  boolean playerJoining;

	// enable/disable chat-message
	private  boolean messageing;
	

	// chat messages
	private  String firstjoin;
	private  String joinmessage;
	private  String privateJoinMessage;
	private  String leftmessage;
	private  String userAmountMessage;
	private  int timezone;
	private  String timemessage;
	private  boolean chatcolor;
	private  String timeformattype;
	
	
	//advert
	private  boolean advert;
	private  long adverttime;
	private  String advertmessage;
	private  String advertPrefix;

	// no connect message
	private  String whitelistmessage;
	private  String banmessage;
	private  String fullmessage;
	private  boolean maintenance;
	private  String maintenance_message;

	// drop
	private  boolean glass;
	private  boolean glassPane;
	private  boolean diamond;
	private  boolean xpsave;
	private  boolean appleshear;
	// dropchance
	private  int diamondDropChance;
	private  int glassSandDropChance;
	private  int glassPaneDropChance;

	// commandlogger
	private  boolean commandlogger;

	// world setting
	private  boolean Bcreeper;
	private  boolean Bender;
	private  boolean tntsave;

	// spread
	private  boolean blockburn;
	private  boolean lavaspread;
	private  boolean normalspread;
	private  boolean lightning_spread;

	// Nice Feature
	private  boolean PlayerPressPlate;
	private  boolean sleepingIgnored;
	private  String ap;

	
	private  int defaultstack;

	// saving time
	private  int savetime;
	
	//unlocking
	private  String answer;
	private  String dgn;
	private  String question;
	
	//physics
	private  boolean physicsSand;
	private  boolean physicsGravel;
	private  boolean physicsTorch;
	private  boolean physicsTrapdoor;
	
	//non-permission-user
	private  boolean nopermission_needed;
	

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
		xpsave = this.plugin.getConfig().getBoolean("world-setting.xpsave");
		appleshear = this.plugin.getConfig().getBoolean("drops.goldenapple-shear");
		
		// dropChance
		diamondDropChance = this.plugin.getConfig().getInt(
				"chance.diamond-drop-rate");
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
	
	public  boolean isBloggingPlayerJoin(){
		return playerJoining;
	}
	
	// nice feature Misc

	public  boolean isPlayerPressPlate() {
		return PlayerPressPlate;
	}
	
	public  int getDefaultStack(){
		return defaultstack;
	}
	public  boolean isSleepingIgnored(){
		return sleepingIgnored;
	}
	public  String getAdminpassword(){
		
		return ap;
	}
	

	// messaging
	public  boolean isMessaging() {
		return messageing;
	}

	// chat-message

	public  String getFirstJoinMessage() {
		return firstjoin;
	}

	public  String getLeftmessage() {
		return leftmessage;
	}

	public  int getTimezone() {
		return timezone;
	}

	public  String getTimemessage() {
		return timemessage;
	}

	public  String getJoinmessage() {
		return joinmessage;
	}
	public  String getPrivatJoinMessage(){
		return privateJoinMessage;
	}
	
	public  String getuserAmountMessage(){
		return userAmountMessage;
	}
	
	public  boolean getRandomColor(){
		return chatcolor;
	}
	public  String getTimeFormat(){
		return timeformattype;
	}

	// advertising
	public  boolean getadvert() {
		return advert;

	}

	public  long getAdvertTime() {
		return adverttime;
	}

	public  String getAdvertMessage() {
		return advertmessage;
	}

	public  String getAdvertPrefix(){
		return advertPrefix;
	}
	// no connect message
	public  String getWhitelistmessage() {
		return whitelistmessage;
	}

	public  String getBanmessage() {
		return banmessage;
	}

	public  String getFullmessage() {
		return fullmessage;
	}

	public  boolean getMaintenance() {
		return maintenance;
	}

	public  String getMaintenanceMessage() {
		return maintenance_message;
	}

	// commandlogger
	public  boolean getCommandLogger() {
		return commandlogger;
	}

	// world settings
	public  boolean isBcreeper() {
		return Bcreeper;
	}

	public  boolean isBender() {
		return Bender;
	}

	public  boolean isTntsave() {
		return tntsave;
	}

	// drop
	public  boolean isXpsave() {
		return xpsave;
	}


	public  boolean isDiamondDrop() {
		return diamond;
	}

	public  boolean isGlassPaneDrop() {
		return glassPane;
	}

	public  boolean isGlassSandDrop() {
		return glass;
	}
	public  boolean isAppleShear(){
		return appleshear;
	}

	// dropchance
	public  int getGlassSandDropChance() {

		return glassSandDropChance;

	}

	public  int getDiamondDropChance() {
		return diamondDropChance;
	}


	public  int getGlassPaneDropChance() {
		return glassPaneDropChance;
	}

	// spread
	public  boolean isBlockburn() {
		return blockburn;
	}

	public  boolean isLavaspread() {
		return lavaspread;
	}

	public  boolean isNormalspread() {
		return normalspread;
	}

	public  boolean isLightning_spread() {
		return lightning_spread;
	}

	// save time

	public  int getSaveTimeInterval() {

		return savetime;
	}
	
	//unlocking
	public  String getAnswer(){
		return answer;
	}
	public  String getDGN(){
		return dgn;
	}
	public  String getQuestion(){
		return question;
	}
	
	//physics
	
	public  boolean getPhysicsSand(){
		return physicsSand;
	}
	public  boolean getPhysicsTorch(){
		return physicsTorch;
	}
	public  boolean getPhysicsTrapdoor(){
		return physicsTrapdoor;
	}
	public  boolean getPhysicsGravel(){
		return physicsGravel;
	}
	
	public  boolean getNonPermission(){
		return nopermission_needed;
	}

}
