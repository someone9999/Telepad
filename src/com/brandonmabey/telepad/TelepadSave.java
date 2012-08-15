package com.brandonmabey.telepad;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

public class TelepadSave {

	private boolean status = false;
	
	public static void saveHomes(File f, HashMap<String, PlayerHomes> homes) throws Exception {
		if (f == null || homes == null) {
			return;
		}
		
		BufferedWriter fw = new BufferedWriter(new FileWriter(f));
		fw.write(homes.size() + "\n");
		
		
		for (String playerKey : homes.keySet()) {
			fw.write(SaveFileConstants.PLAYER_DELIMETER);
			fw.write(playerKey);
			fw.write(SaveFileConstants.PLAYER_DELIMETER);
			fw.write(homes.get(playerKey).getSaveString());
			fw.write("\n");
		}
		
		
		
		fw.flush();
				return;
		
	}
	
	public static void saveTelepads(File f, HashMap<String, Teleporter> telepads) throws Exception {
		if (f == null || telepads == null) {
			return;
		}
		
		BufferedWriter fw = new BufferedWriter(new FileWriter(f));
		
		fw.write(telepads.size() + "\n");
		
		for (String telepadName : telepads.keySet()) {
			Teleporter tp = telepads.get(telepadName);
			
			fw.write(telepadName + SaveFileConstants.TELEPAD_DELIMETER);
			fw.write(tp.getWorldName() + SaveFileConstants.TELEPAD_DELIMETER);
			fw.write(tp.getEnterX() + SaveFileConstants.TELEPAD_DELIMETER);
			fw.write(tp.getEnterY() + SaveFileConstants.TELEPAD_DELIMETER);
			fw.write(tp.getEnterZ() + SaveFileConstants.TELEPAD_DELIMETER);
			fw.write(tp.getSendX() + SaveFileConstants.TELEPAD_DELIMETER);
			fw.write(tp.getSendY() + SaveFileConstants.TELEPAD_DELIMETER);
			fw.write(tp.getSendZ() + SaveFileConstants.TELEPAD_DELIMETER);
			fw.write(tp.getSendYaw() + "\n");
		}
		
		fw.flush();
		return;
	}
	
	public boolean getStatus() {
		return this.status;
	}
	
}
