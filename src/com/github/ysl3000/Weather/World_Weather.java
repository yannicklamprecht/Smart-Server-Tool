package com.github.ysl3000.Weather;

public class World_Weather {

	
	private String name;
	private String status;
	public World_Weather(String name, String status) {
		this.name = name;
		this.status = status;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getinfo() {
		return this.status;
	}

	public String getName() {
		return this.name;
	}

}
