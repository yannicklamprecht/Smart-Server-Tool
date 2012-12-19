package com.github.ysl3000;

public class Commands {

	public static boolean getJC(String cml) {

		return cml.equalsIgnoreCase("jc");
	}

	public static boolean getAL(String cml) {
		return cml.equalsIgnoreCase("al");
	}

	public static boolean getGMC(String cml) {
		return cml.equalsIgnoreCase("gmc");
	}

	public static boolean getGMS(String cml) {
		return cml.equalsIgnoreCase("gms");
	}

	public static boolean getGM(String cml) {
		return cml.equalsIgnoreCase("gm");
	}

	public static boolean getFly(String cml) {
		return cml.equalsIgnoreCase("fly");
	}

	public static boolean getFS(String cml) {
		return cml.equalsIgnoreCase("fs");
	}

	public static boolean getWS(String cml) {
		return cml.equalsIgnoreCase("ws");
	}

	public static boolean getGOD(String cml) {
		return cml.equalsIgnoreCase("god");
	}

	public static boolean getKM(String cml) {
		return cml.equalsIgnoreCase("km");
	}

	public static boolean getHeal(String cml) {
		return cml.equalsIgnoreCase("heal");
	}

	public static boolean getHide(String cml) {
		return cml.equalsIgnoreCase("hide");
	}

	public static boolean getShow(String cml) {
		return cml.equalsIgnoreCase("show");
	}

	public static boolean getMem(String cml) {
		return cml.equalsIgnoreCase("/mem");
	}

	public static boolean getIp(String cml) {
		return cml.equalsIgnoreCase("/ip");
	}

	public static boolean getCpu(String cml) {
		return cml.equalsIgnoreCase("/cpu");
	}

	public static boolean getSSTV(String cml) {
		return cml.equalsIgnoreCase("sstv");
	}

	public static boolean getInvite(String cml) {
		return cml.equalsIgnoreCase("invite");
	}

	public static boolean getI(String cml) {
		return cml.equalsIgnoreCase("i");
	}

	public static boolean getCI(String cml) {
		return cml.equalsIgnoreCase("ci");

	}

	public static boolean getRepair(String cml) {
		return cml.equalsIgnoreCase("repair");
	}

	public static boolean getKick(String cml) {
		return cml.equalsIgnoreCase("kick");
	}

	public static boolean getNick(String cml) {
		return cml.equalsIgnoreCase("nick");
	}

	public static boolean getAnswer(String cml) {
		return cml.equalsIgnoreCase("answer");
	}

	public static boolean getSetSpawn(String cml) {
		return cml.equalsIgnoreCase("setsp");
	}

	public static boolean getSpawn(String cml) {
		return cml.equalsIgnoreCase("spawn");
	}

	public static boolean getTP(String cml) {
		return cml.equalsIgnoreCase("tpt");
	}

	public static boolean getTPO(String cml) {
		return cml.equalsIgnoreCase("tpo");
	}

	public static boolean getBack(String cml) {
		return cml.equalsIgnoreCase("back");
	}

	public static boolean getHome(String cml) {
		return cml.equalsIgnoreCase("home");
	}

	public static boolean getTd(String cml) {
		return cml.equalsIgnoreCase("td");
	}

	public static boolean getTn(String cml) {
		return cml.equalsIgnoreCase("tn");
	}

	public static boolean getT(String cml) {
		return cml.equalsIgnoreCase("t");
	}

	public static boolean getMod(String cml) {
		return cml.equalsIgnoreCase("mod");
	}

	public static boolean getDone(String cml) {
		return cml.equalsIgnoreCase("done");
	}

	public static boolean getAdmin(String cml) {
		return cml.equalsIgnoreCase("/admin");
	}

	public static boolean getSun(String cml) {
		return cml.equalsIgnoreCase("sun");
	}

	public static boolean getStorm(String cml) {
		return cml.equalsIgnoreCase("storm");
	}

	public static boolean getWeather(String cml) {
		return cml.equalsIgnoreCase("wg");
	}

	public static boolean getHelp(String cml) {
		return cml.equalsIgnoreCase("ssth");
	}

	public static boolean getCommandWorkbench(String cml) {
		return cml.equalsIgnoreCase("wb");
	}

	public static boolean getCommandEchantTable(String cml) {
		return cml.equalsIgnoreCase("en");
	}

	public static boolean getEnderChest(String cml) {
		return cml.equalsIgnoreCase("ec");
	}

	public static boolean getOnlinemode(String cml) {
		return cml.equalsIgnoreCase("mode");
	}
	public static boolean getListPlayers(String cml){
		return (cml.equalsIgnoreCase("list")|| cml.equalsIgnoreCase("online"));
	}
	public static boolean getPluginsCommand(String cml){
		return cml.equalsIgnoreCase("plugins");
	}
	public static boolean getSeenPlayer(String cml){
		return cml.equalsIgnoreCase("seen");
	}
	public static boolean getRealTime(String cml){
		return cml.equalsIgnoreCase("rt");
	}

}
