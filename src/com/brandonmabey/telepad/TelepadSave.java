package com.brandonmabey.telepad;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

public class TelepadSave {

	private boolean status = false;
	
	public TelepadSave(File f, HashMap<String, PlayerHomes> homes) throws Exception {
		if (f == null || homes == null) {
			return;
		}
		
		BufferedWriter fw = new BufferedWriter(new FileWriter(f));
		
		
		for (String playerKey : homes.keySet()) {
			fw.write(SaveFileConstants.PLAYER_DELIMETER);
			fw.write(playerKey);
			fw.write(homes.get(playerKey).getSaveString());
			fw.write(SaveFileConstants.PLAYER_DELIMETER);
			fw.write("\n");
		}
		
		
		
		fw.flush();
				return;
		
	}
	
	public boolean getStatus() {
		return this.status;
	}
	
}
