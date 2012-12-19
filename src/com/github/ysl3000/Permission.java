package com.github.ysl3000;

import org.bukkit.entity.Player;

public class Permission {

	public static boolean hasvisible(Player s) {
		return ((Player) s).hasPermission("sst.visible");
	}

	public static boolean hascansee(Player s) {
		return s.hasPermission("sst.cansee");
	}

	public static boolean hasjoinChat(Player s) {
		return s.hasPermission("sst.jc");
	}

	public static boolean hasclearEntities(Player s) {
		return s.hasPermission("sst.al");
	}

	public static boolean hasCgamemode(Player s) {
		return s.hasPermission("sst.gamemode");
	}

	public static boolean hasCgamemodeO(Player s) {
		return s.hasPermission("sst.gamemodeo");
	}

	public static boolean hasLgamemode(Player s) {
		return s.hasPermission("sst.gm");
	}

	public static boolean hasLgamemodeO(Player s) {
		return s.hasPermission("sst.gmo");
	}

	public static boolean hasFly(Player s) {
		return s.hasPermission("sst.fly");
	}

	public static boolean hasFlyO(Player s) {
		return s.hasPermission("sst.flyo");
	}

	public static boolean hasGod(Player s) {
		return s.hasPermission("sst.god");
	}

	public static boolean hasKm(Player s) {

		return s.hasPermission("sst.km");
	}

	public static boolean hasKill(Player s) {
		return s.hasPermission("sst.kill");
	}

	public static boolean hasHeal(Player s) {
		return s.hasPermission("sst.kill");
	}

	public static boolean hasMem(Player s) {
		return s.hasPermission("sst.mem");
	}

	public static boolean hasIp(Player s) {
		return s.hasPermission("sst.ip");
	}

	public static boolean hasInvite(Player s) {
		return s.hasPermission("sst.invite");
	}

	public static boolean hasItem(Player s) {
		return s.hasPermission("sst.item");
	}

	public static boolean hasClearInventory(Player s) {
		return s.hasPermission("sst.ci");
	}

	public static boolean hasRepair(Player s) {
		return s.hasPermission("sst.repair");
	}

	public static boolean hasKickAll(Player s) {
		return s.hasPermission("sst.kickall");
	}

	public static boolean hasAnswerQ(Player s) {
		return s.hasPermission("sst.answer");
	}

	public static boolean hasSetSpawn(Player s) {
		return s.hasPermission("sst.setsp");
	}

	public static boolean hasUseSpawn(Player s) {
		return s.hasPermission("sst.spawn");
	}

	public static boolean hasCanTp(Player s) {
		return s.hasPermission("sst.tp");
	}

	public static boolean hasCanTpO(Player s) {
		return s.hasPermission("sst.tpo");
	}

	public static boolean hasCanSwitch(Player s) {
		return s.hasPermission("sst.switch");
	}

	public static boolean hasHome(Player s) {
		return s.hasPermission("sst.home");
	}

	public static boolean hasHomeO(Player s) {
		return s.hasPermission("sst.homeo");
	}

	public static boolean hasSetTime(Player s) {
		return s.hasPermission("sst.time");
	}

	public static boolean hasGetTime(Player s) {
		return s.hasPermission("sst.t");
	}

	public static boolean hasMod(Player s) {
		return s.hasPermission("sst.mod");
	}

	public static boolean hasAdmin(Player s) {
		return s.hasPermission("sst.admin");
	}

	public static boolean hasWeather(Player s) {
		return s.hasPermission("sst.weather");
	}

	public static boolean hasGetWeather(Player s) {
		return s.hasPermission("sst.wg");
	}

	// commandpermission

	public static boolean hasInfo(Player s) {
		return s.hasPermission("sst.info");
	}

	public static boolean hasCreate(Player s) {
		return s.hasPermission("sst.create");
	}

	public static boolean hasAllowChat(Player s) {
		return s.hasPermission("sst.chat");
	}

	public static boolean hasAllowMove(Player s) {
		return s.hasPermission("sst.move");
	}

	public static boolean hasChangeSpawnerType(Player s) {
		return s.hasPermission("sst.changetype");
	}

	// Playerlistener

	public static boolean hasJoinFull(Player s) {
		return s.hasPermission("sst.joinfull");
	}

	public static boolean hasJoinService(Player s) {
		return s.hasPermission("sst.joinservice");
	}

	public static boolean hasAutoFly(Player s) {
		return s.hasPermission("sst.autofly");
	}
	public static boolean hasPlugins(Player s){
		return s.hasPermission("sst.plugins");
	}

	// else
}
