package com.ysl3000.utils;


public class SmartController {

	private static SmartController smc;

	private final HashMapController hsc;

	private SmartController() {
		this.hsc = new HashMapController();
	}

	public static SmartController getSmartControler() {
		if (smc == null) {
			smc = new SmartController();
		}
		return smc;
	}

	public HashMapController getHashmaps() {
		return hsc;
	}


}
