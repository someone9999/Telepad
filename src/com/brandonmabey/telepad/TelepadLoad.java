package com.brandonmabey.telepad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.logging.Logger;

public class TelepadLoad {

	public static HashMap<String, PlayerHomes> loadHomes(File f, Logger log) {
		
		if (f == null) {
			return null;
		}
		
		BufferedReader br;
		
		try {
			br = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		HashMap<String, PlayerHomes> loadedHashMap;
		try {
			int sizeOfHash = Integer.valueOf(br.readLine());
			loadedHashMap = new HashMap<String, PlayerHomes>(sizeOfHash);
			
			for (int i = 0; i < sizeOfHash; i++) {
				String playerString[] = br.readLine().split("\\Q" + SaveFileConstants.PLAYER_DELIMETER + "\\E");
				String key = playerString[1];
				PlayerHomes value = new PlayerHomes(playerString[2], log);
				loadedHashMap.put(key, value);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return loadedHashMap;
		
	}
	
	public static HashMap<String, Teleporter> loadTelepads(File f, Logger log) {
		
		if (f == null) {
			return null;
		}
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			int telepadNumber = Integer.valueOf(br.readLine());
			
			HashMap<String, Teleporter> telepadMap = new HashMap<String, Teleporter>(telepadNumber);
			
			for (int i = 0; i < telepadNumber; i++) {
				String telepadData[] = br.readLine().split("\\Q" + SaveFileConstants.TELEPAD_DELIMETER + "\\E");
				
				Teleporter tp = new Teleporter(telepadData[1], Integer.valueOf(telepadData[2]), Integer.valueOf(telepadData[3]), Integer.valueOf(telepadData[4]), Integer.valueOf(telepadData[5]), Integer.valueOf(telepadData[6]), Integer.valueOf(telepadData[7]), Float.valueOf(telepadData[8]));
				telepadMap.put(telepadData[0], tp);
				
			}
			
			return telepadMap;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
