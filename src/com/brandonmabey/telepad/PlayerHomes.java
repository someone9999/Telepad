package com.brandonmabey.telepad;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.ChatColor;

public class PlayerHomes {
	private HashMap<String, Home> pHomes;
	private Logger log;
	
	
	public PlayerHomes(String loader, Logger log) {

		this.log = log;
		
		if (loader == null) {
			this.pHomes = new HashMap<String, Home>(0);
		} else {
			
			this.loadString(loader);
			
		}
	}
	
	public boolean addHomeForName(String homeName, Home location) {
		this.pHomes.put(homeName, location);
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
		
		String returnString = "";
		
		for (String homeKey : pHomes.keySet()) {
			returnString += SaveFileConstants.HOME_DELIMETER;
			returnString += homeKey;
			returnString += SaveFileConstants.HOME_DELIMETER;
			returnString += pHomes.get(homeKey).getSaveString();
		}
		
		return returnString;
	}
	
	private void loadString(String loadString) {
		try {
			
//			log.info("playerHomes got string: " + loadString);
			
			String homeStrings[] = loadString.split("\\Q" + SaveFileConstants.HOME_DELIMETER + "\\E");
			int numberOfHomes = (homeStrings.length - 1) / 2;
			HashMap<String, Home> loadedHashMap = new HashMap<String, Home>(numberOfHomes);
			
			for (int i = 0; i < numberOfHomes; i++) {
				int homeIndex = i * 2 + 1;
				String key = homeStrings[homeIndex];
				Home value = new Home(homeStrings[homeIndex + 1], log);
				loadedHashMap.put(key, value);
			}
			
			this.pHomes = loadedHashMap;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[] getHomeStringNames() {
		if (pHomes == null || pHomes.size() == 0) {
			return new String[] { ChatColor.GRAY + "No homes set." } ;
		} else {
			String homeStrings[] = new String[pHomes.size()];
			int counter = 0;
			
			for (String homeKey : pHomes.keySet()) {
				String homeName;
				Home h = pHomes.get(homeKey);
				if (homeKey.equalsIgnoreCase("") || homeKey == null) {
					homeName = "Default home";
				} else {
					homeName = homeKey;
				}
				
				homeStrings[counter] = ChatColor.YELLOW + homeName + ChatColor.GRAY + " with coordinates " + ChatColor.YELLOW + "(" + (int)h.getX() + ", " + (int)h.getY() + ", " + (int)h.getZ() + ")" + ChatColor.GRAY + ".";
				counter++;
			}
			
			return homeStrings;
			
		}
	}

	public void removeHome(String string) {
		pHomes.remove(string);
	}
}
