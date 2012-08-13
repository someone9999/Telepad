package com.brandonmabey.telepad;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;

public class PlayerHomes {
	private HashMap<String, Home> pHomes;
	private Logger log;
	
	
	public PlayerHomes(File fLocation, Logger log) {
		if (fLocation == null) {
			this.pHomes = new HashMap<String, Home>(0);
		}
		
		this.log = log;
	}
	
	public boolean addHomeForName(String homeName, Home location) {
		HashMap<String, Home> newHomes;
		if (pHomes.get(homeName) == null) {
			newHomes = new HashMap<String, Home>(1);
		} else {
			newHomes = new HashMap<String, Home>(pHomes.size() + 1);
		}
		newHomes.putAll(pHomes);
		newHomes.put(homeName, location);
		this.pHomes = newHomes;
		return true;
	}
	
	public Home getHome(String homeName) {
//		this.log.info("Attempting to get home with name " + homeName);
		if (pHomes.containsKey(homeName)) {
//			this.log.info("Found house");
			return this.pHomes.get(homeName);
		}
//		this.log.info("Failed");
		return null;
	}
	
	public String getSaveString() {
		
		String returnString = SaveFileConstants.HOME_DELIMETER;
		
		for (String homeKey : pHomes.keySet()) {
			returnString += homeKey;
			returnString += pHomes.get(homeKey).getSaveString();
			returnString += SaveFileConstants.HOME_DELIMETER;
		}
		
		return returnString;
	}
}
