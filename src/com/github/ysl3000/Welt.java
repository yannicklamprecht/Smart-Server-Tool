package com.github.ysl3000;

public class Welt {

	
	private String weltname;
	private String status;
	public Welt (String name, String status){
		
		setName(name);
		setStatus(status);
		
	}

	public void setName(String weltname) {
		this.weltname = weltname;
		
	}

	public void setStatus(String status) {
		this.status = status;
		
	}

	public String getinfo() {
		
		return status;
	}

	public String getName() {
		
		return weltname;
	}
}
