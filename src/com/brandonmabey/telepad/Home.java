package com.brandonmabey.telepad;

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
