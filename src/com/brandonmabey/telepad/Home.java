package com.brandonmabey.telepad;

import java.util.logging.Logger;

import org.bukkit.World;

public class Home {
	private String worldName;
	private double locX;
	private double locY;
	private double locZ;
	
	public Home(String worldName, double x, double y, double z) {
		this.worldName = worldName;
		this.locX = x;
		this.locY = y;
		this.locZ = z;
	}
	
	public Home(String loadString, Logger log) {
//		log.info("Home got string " + loadString);
		
		try {
			
			String infoString[] = loadString.split("\\Q" + SaveFileConstants.HOME_INFO_DELIMETER + "\\E");
			
			this.worldName = infoString[1];
			this.locX = Double.valueOf(infoString[2]);
			this.locY = Double.valueOf(infoString[3]);
			this.locZ = Double.valueOf(infoString[4]);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public double getX() {
		return this.locX;
	}
	
	public double getY() {
		return this.locY;
	}
	
	public double getZ() {
		return this.locZ;
	}
	
	public boolean verifyWorld(World world) {
		return world.getName().equalsIgnoreCase(this.worldName);
	}

	public String getWorldName() {
		return worldName;
	}

	public String getSaveString() {
		String del = SaveFileConstants.HOME_INFO_DELIMETER;
		
		return del + this.worldName + del + this.locX + del + this.locY + del + this.locZ + del;
	}
}
