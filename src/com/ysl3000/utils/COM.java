package com.ysl3000.utils;

public enum COM {

	ADMIN("admin"),
	BACK("back"),
	CHECK_GAMEMODE("check_gamemode"),
	CREATIVE_GAMEMODE("creative_gamemode"),
	DONE("done"),
	FLY("fly"),
	FLY_SPEED("fly_speed"),
	WALK_SPEED("walk_speed"),
	FREEZE("freeze"),
	GET_WEATHER("get_weather"),
	GOD("god"),
	HEAL("heal"),
	HEAL_ME("heal_me"),
	HOME("home"),
	KILL("kill"),
	MOD("mod"),
	ONLINE("online"),
	PLAYER_LOOKUP_IP("player_lookup_ip"),
	REALTIME("realtime"),
	SEEN("seen"),
	SERVER_INFO("serverinfo"),
	SET_SPAWN("set_spawn"),
	SPAWN("spawn"),
	STORM("storm"),
	SUN("sun"),
	SURVIVAL_GAMEMODE("survival_gamemode"),
	SWITCH_LOCATION("switch_location"),
	TIME_DAY("time_day"),
	TIME_NIGHT("time_night");
	private String s;

	COM(String s) {
		this.s = s;
	}
	public String value(){
		return s;
	}
}
