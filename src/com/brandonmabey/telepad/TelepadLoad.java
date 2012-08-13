package com.brandonmabey.telepad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
		
		String loadString;
		try {
			loadString = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		log.info("Loading hashmaps: " + loadString);
		return null;
		
	}
	
}
