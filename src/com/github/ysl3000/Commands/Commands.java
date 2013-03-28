package com.github.ysl3000.Commands;

public class Commands {

	public  boolean getJC(String cml) {

		return cml.equalsIgnoreCase("jc");
	}

	public  boolean getAL(String cml) {
		return cml.equalsIgnoreCase("al");
	}

	public  boolean getGMC(String cml) {
		return cml.equalsIgnoreCase("gmc");
	}

	public  boolean getGMS(String cml) {
		return cml.equalsIgnoreCase("gms");
	}

	public  boolean getGM(String cml) {
		return cml.equalsIgnoreCase("gm");
	}

	public  boolean getFly(String cml) {
		return cml.equalsIgnoreCase("fly");
	}

	public  boolean getFS(String cml) {
		return cml.equalsIgnoreCase("fs");
	}

	public  boolean getWS(String cml) {
		return cml.equalsIgnoreCase("ws");
	}

	public  boolean getGOD(String cml) {
		return cml.equalsIgnoreCase("god");
	}

	public  boolean getKM(String cml) {
		return cml.equalsIgnoreCase("km");
	}

	public  boolean getHeal(String cml) {
		return cml.equalsIgnoreCase("heal");
	}

	public  boolean getHide(String cml) {
		return cml.equalsIgnoreCase("hide");
	}

	public  boolean getShow(String cml) {
		return cml.equalsIgnoreCase("show");
	}

	public  boolean getMem(String cml) {
		return cml.equalsIgnoreCase("/mem");
	}

	public  boolean getIp(String cml) {
		return cml.equalsIgnoreCase("/ip");
	}

	public  boolean getCpu(String cml) {
		return cml.equalsIgnoreCase("/cpu");
	}

	public  boolean getSSTV(String cml) {
		return cml.equalsIgnoreCase("sstv");
	}

	public  boolean getInvite(String cml) {
		return cml.equalsIgnoreCase("invite");
	}

	public  boolean getI(String cml) {
		return cml.equalsIgnoreCase("i");
	}

	public  boolean getCI(String cml) {
		return cml.equalsIgnoreCase("ci");

	}

	public  boolean getRepair(String cml) {
		return cml.equalsIgnoreCase("repair");
	}

	public  boolean getKick(String cml) {
		return cml.equalsIgnoreCase("kick");
	}

	public  boolean getNick(String cml) {
		return cml.equalsIgnoreCase("nick");
	}

	public  boolean getAnswer(String cml) {
		return cml.equalsIgnoreCase("answer");
	}

	public  boolean getSetSpawn(String cml) {
		return cml.equalsIgnoreCase("setsp");
	}

	public  boolean getSpawn(String cml) {
		return cml.equalsIgnoreCase("spawn");
	}

	public  boolean getTP(String cml) {
		return cml.equalsIgnoreCase("tp");
	}

	public  boolean getTPO(String cml) {
		return cml.equalsIgnoreCase("tpo");
	}

	public  boolean getBack(String cml) {
		return cml.equalsIgnoreCase("back");
	}

	public  boolean getHome(String cml) {
		return cml.equalsIgnoreCase("home");
	}

	public  boolean getTd(String cml) {
		return cml.equalsIgnoreCase("td");
	}

	public  boolean getTn(String cml) {
		return cml.equalsIgnoreCase("tn");
	}

	public  boolean getT(String cml) {
		return cml.equalsIgnoreCase("t");
	}

	public  boolean getMod(String cml) {
		return cml.equalsIgnoreCase("mod");
	}

	public  boolean getDone(String cml) {
		return cml.equalsIgnoreCase("done");
	}

	public  boolean getAdmin(String cml) {
		return cml.equalsIgnoreCase("/admin");
	}

	public  boolean getSun(String cml) {
		return cml.equalsIgnoreCase("sun");
	}

	public  boolean getStorm(String cml) {
		return cml.equalsIgnoreCase("storm");
	}

	public  boolean getWeather(String cml) {
		return cml.equalsIgnoreCase("wg");
	}

	public  boolean getHelp(String cml) {
		return cml.equalsIgnoreCase("ssth");
	}
	public  boolean getOnlinemode(String cml) {
		return cml.equalsIgnoreCase("mode");
	}
	public  boolean getListPlayers(String cml){
		return (cml.equalsIgnoreCase("list")|| cml.equalsIgnoreCase("online"));
	}
	public  boolean getPluginsCommand(String cml){
		return cml.equalsIgnoreCase("plugins");
	}
	public  boolean getSeenPlayer(String cml){
		return cml.equalsIgnoreCase("seen");
	}
	public  boolean getRealTime(String cml){
		return cml.equalsIgnoreCase("rt");
	}
	public boolean getFreeze(String cml){
		return cml.equalsIgnoreCase("freeze");
	}
	public boolean getPrain(String cml){
		return cml.equalsIgnoreCase("prain");
	}
	public boolean getPsun(String cml){
		return cml.equalsIgnoreCase("psun");
	}
	public boolean getPreset(String cml){
		return cml.equalsIgnoreCase("preset");
	}

}
