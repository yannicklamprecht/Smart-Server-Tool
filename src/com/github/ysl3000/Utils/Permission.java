package com.github.ysl3000.Utils;

import org.bukkit.entity.Player;

public class Permission {

	public  boolean hasvisible(Player s) {
		return ((Player) s).hasPermission("sst.visible");
	}

	public  boolean hascansee(Player s) {
		return s.hasPermission("sst.cansee");
	}

	public  boolean hasjoinChat(Player s) {
		return s.hasPermission("sst.jc");
	}

	public  boolean hasclearEntities(Player s) {
		return s.hasPermission("sst.al");
	}

	public  boolean hasCgamemode(Player s) {
		return s.hasPermission("sst.gamemode");
	}

	public  boolean hasCgamemodeO(Player s) {
		return s.hasPermission("sst.gamemodeo");
	}

	public  boolean hasLgamemode(Player s) {
		return s.hasPermission("sst.gm");
	}

	public  boolean hasLgamemodeO(Player s) {
		return s.hasPermission("sst.gmo");
	}

	public  boolean hasFly(Player s) {
		return s.hasPermission("sst.fly");
	}

	public  boolean hasFlyO(Player s) {
		return s.hasPermission("sst.flyo");
	}

	public  boolean hasGod(Player s) {
		return s.hasPermission("sst.god");
	}

	public  boolean hasKm(Player s) {

		return s.hasPermission("sst.km");
	}

	public  boolean hasKill(Player s) {
		return s.hasPermission("sst.kill");
	}

	public  boolean hasHeal(Player s) {
		return s.hasPermission("sst.kill");
	}

	public  boolean hasMem(Player s) {
		return s.hasPermission("sst.mem");
	}

	public  boolean hasIp(Player s) {
		return s.hasPermission("sst.ip");
	}

	public  boolean hasInvite(Player s) {
		return s.hasPermission("sst.invite");
	}

	public  boolean hasItem(Player s) {
		return s.hasPermission("sst.item");
	}

	public  boolean hasClearInventory(Player s) {
		return s.hasPermission("sst.ci");
	}

	public  boolean hasRepair(Player s) {
		return s.hasPermission("sst.repair");
	}

	public  boolean hasKickAll(Player s) {
		return s.hasPermission("sst.kickall");
	}

	public  boolean hasAnswerQ(Player s) {
		return s.hasPermission("sst.answer");
	}

	public  boolean hasSetSpawn(Player s) {
		return s.hasPermission("sst.setsp");
	}

	public  boolean hasUseSpawn(Player s) {
		return s.hasPermission("sst.spawn");
	}

	public  boolean hasCanTp(Player s) {
		return s.hasPermission("sst.tp");
	}

	public  boolean hasCanTpO(Player s) {
		return s.hasPermission("sst.tpo");
	}

	public  boolean hasCanSwitch(Player s) {
		return s.hasPermission("sst.switch");
	}

	public  boolean hasHome(Player s) {
		return s.hasPermission("sst.home");
	}

	public  boolean hasHomeO(Player s) {
		return s.hasPermission("sst.homeo");
	}

	public  boolean hasSetTime(Player s) {
		return s.hasPermission("sst.time");
	}

	public  boolean hasGetTime(Player s) {
		return s.hasPermission("sst.t");
	}

	public  boolean hasMod(Player s) {
		return s.hasPermission("sst.mod");
	}

	public  boolean hasAdmin(Player s) {
		return s.hasPermission("sst.admin");
	}

	public  boolean hasWeather(Player s) {
		return s.hasPermission("sst.weather");
	}

	public  boolean hasGetWeather(Player s) {
		return s.hasPermission("sst.wg");
	}

	// commandpermission

	public  boolean hasInfo(Player s) {
		return s.hasPermission("sst.info");
	}

	public  boolean hasCreate(Player s) {
		return s.hasPermission("sst.create");
	}

	public  boolean hasAllowChat(Player s) {
		return s.hasPermission("sst.chat");
	}

	public  boolean hasAllowMove(Player s) {
		return s.hasPermission("sst.move");
	}

	public  boolean hasChangeSpawnerType(Player s) {
		return s.hasPermission("sst.changetype");
	}

	// Playerlistener

	public  boolean hasJoinFull(Player s) {
		return s.hasPermission("sst.joinfull");
	}

	public  boolean hasJoinService(Player s) {
		return s.hasPermission("sst.joinservice");
	}

	public  boolean hasAutoFly(Player s) {
		return s.hasPermission("sst.autofly");
	}
	public  boolean hasVWorbench(Player s){
		return s.hasPermission("sst.vwbench");
	}
	public  boolean hasVEnderchest(Player s){
		return s.hasPermission("sst.vechest");
	}
	public  boolean hasVEnchantingTable(Player s){
		return s.hasPermission("sst.vetable");
	}
	public  boolean hasCraftMagmaConatainer(Player s){
		return s.hasPermission("sst.cMc");
	}
	public boolean hasFreeze(Player s){
		return s.hasPermission("sst.freeze");
	}
	public boolean hasBukkitCommandPlugins(Player s){
		return s.hasPermission("bukkit.command.plugins");
	}
	public boolean hasOpenanyChest(Player s){
		return s.hasPermission("sst.ignoreProtection");
	}
}
