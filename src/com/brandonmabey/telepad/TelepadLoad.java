package com.brandonmabey.telepad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.logging.Logger;

public class TelepadLoad {

	public static HashMap<String, PlayerHomes> getLoadMap(File f, Logger log) {
		
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
	
}
