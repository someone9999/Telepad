package com.brandonmabey.telepad;

import org.bukkit.Location;
import org.bukkit.World;

public class Teleporter {
	private int enterX;
	private int enterY;
	private int enterZ;
	private int sendX;
	private int sendY;
	private int sendZ;
	private float sendYaw = 0F;
	private String worldName;
	
	public Teleporter(Location enterLocation, Location sendLocation, String worldName) {
		enterX = (int) enterLocation.getX();
		enterY = (int) enterLocation.getY();
		enterZ = (int) enterLocation.getZ();
		sendX = (int) sendLocation.getX();
		sendY = (int) sendLocation.getY();
		sendZ = (int) sendLocation.getZ();
		this.worldName = worldName;
	}
	
	public Teleporter(String worldName, int enterX, int enterY, int enterZ, int sendX, int sendY, int sendZ, float sendYaw) {
		this.worldName = worldName;
		this.enterX = enterX;
		this.enterY = enterY;
		this.enterZ = enterZ;
		this.sendX = sendX;
		this.sendY = sendY;
		this.sendZ = sendZ;
		this.sendYaw = sendYaw;
	}
	
	public int getEnterX() { return this.enterX; }
	public int getEnterY() { return this.enterY; }
	public int getEnterZ() { return this.enterZ; }
	public int getSendX() { return this.sendX; }
	public int getSendY() { return this.sendY; }
	public int getSendZ() { return this.sendZ; }
	public float getSendYaw() { return this.sendYaw; }
	public String getWorldName() { return this.worldName; }
	
	public void setSendYaw(float yaw) { this.sendYaw = yaw; }

	public boolean validateWorld(World world) {
		return world.getName().equalsIgnoreCase(this.worldName);
	}
	
}
