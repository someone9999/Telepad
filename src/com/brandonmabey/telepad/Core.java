package com.brandonmabey.telepad;

import java.io.File;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

	private HashMap<String, PlayerHomes> homeMap;
	private HashMap<String, Integer> lastUseMap;
	int currentTick = 0;

	private static final String SAVE_FILE_NAME = "telepad.sav";
	private static final int TIME_BETWEEN_USES = 10; //seconds
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("home")) {
			return onHomeCommand(sender, command, label, args);
		} else if (command.getName().equalsIgnoreCase("sethome")) {
			return onSetHomeCommand(sender, command, label, args);
		} else if (command.getName().equalsIgnoreCase("listhome")) {
			return onListHomeCommand(sender, command, label, args);
		} else {
			return false;
		}
	}
	
	private boolean onListHomeCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("Command can only be used as a player, not from the console");
			return false;
		}
		
		PlayerHomes playerHomes = getPlayerHomes(sender.getName());
		sender.sendMessage(playerHomes.getHomeStringNames());
		
		return true;
	}

	private boolean onSetHomeCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p;
		if (!(sender instanceof Player)) {
			this.getLogger().warning("Can not set and use homes from console.");
			return false;
		} else {
			p = (Player) sender;
		}
		PlayerHomes ph = getPlayerHomes(sender.getName());
		
		String homeName;
		if (args.length == 0) {
			homeName = "";
			sender.sendMessage(ChatColor.GRAY + "Setting default home!");
		} else {
			homeName = args[0];
			sender.sendMessage(ChatColor.GRAY + "Setting home called: " + homeName);
		}
		
		ph.addHomeForName(homeName, new Home(p.getWorld().getName(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()));
		return true;
	}
	
	private PlayerHomes getPlayerHomes(String s) {
		if (homeMap.containsKey(s)) {
			return homeMap.get(s);
		} else {
			this.getLogger().info("Creating homes mapping for " + s);
			homeMap.put(s, new PlayerHomes(null, this.getLogger()));
			return homeMap.get(s);
		}
	}
	
	private int getLastPlayerCommand(String player) {
		if (lastUseMap.containsKey(player)) {
			return lastUseMap.get(player);
		} else {
			lastUseMap.put(player, 0);
			return -TIME_BETWEEN_USES;
		}
	}
	
	private void setLastPlayerCommand(String player) {
		lastUseMap.put(player, this.currentTick);
	}

	private boolean onHomeCommand(CommandSender sender, Command command,String label, String[] args) {
		Player p;
		if (!(sender instanceof Player)) {
			this.getLogger().warning("Console can not use /home command.");
			return false;
		}
		p = (Player)sender;
		PlayerHomes ph = getPlayerHomes(sender.getName());
		
		String homeName;
		if (args.length == 0) {
			homeName = "";
		} else {
			homeName = args[0];
		}
		
		Home location = ph.getHome(homeName);
		if (location == null) {
			sender.sendMessage("No home set for specified name");
			return true;
		}
		if (homeName != "") {
			sender.sendMessage(ChatColor.GRAY + "Going to your home called: " + homeName);
		} else {
			sender.sendMessage(ChatColor.GRAY + "Going to your default home.");
		}
		
		int lastUse = getLastPlayerCommand(sender.getName());
		if (lastUse > this.currentTick - TIME_BETWEEN_USES) {
			sender.sendMessage(ChatColor.YELLOW + "You can not use /home for another " + (lastUse - (this.currentTick - TIME_BETWEEN_USES)) + " seconds.");
			return true;
		}
		
		this.getLogger().info(sender.getName() + " is teleporting to their home at " + location.getX() + ", " + location.getY() + ", " + location.getZ());
		p.teleport(new Location(p.getWorld(), location.getX(), location.getY(), location.getZ()));
		setLastPlayerCommand(sender.getName());
		return true;
	}

	@Override
	public void onDisable() {
		this.getLogger().info("Telepad disabled");
	
		
		File f = getDataFile(SAVE_FILE_NAME, false);
		
		try {
			new TelepadSave(f, homeMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onEnable() {
		this.getLogger().info("Telepad enabled");
		
		
		File f = getDataFile(SAVE_FILE_NAME, true);
		if (f == null) {
			this.homeMap = new HashMap<String, PlayerHomes>(0);
		} else {
			this.homeMap = TelepadLoad.getLoadMap(f, this.getLogger());
		}
		
		this.lastUseMap = new HashMap<String, Integer>(0);
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new TickUpdater(this), TIME_BETWEEN_USES, TIME_BETWEEN_USES);
	}
	
	public static void main(String args[]) {
		
	}
	
	/**
	 * Initialise the data directory for this plugin.
	 *
	 * @return true if the directory has been created or already exists.
	 */
	private boolean createDataDirectory() {
	    File file = this.getDataFolder();
	    if (!file.isDirectory()){
	        if (!file.mkdirs()) {
	            // failed to create the non existent directory, so failed
	            return false;
	        }
	    }
	    return true;
	}
	 
	/**
	 * Retrieve a File description of a data file for your plugin.
	 * This file will be looked for in the data directory of your plugin, wherever that is.
	 * There is no need to specify the data directory in the filename such as "plugin/datafile.dat"
	 * Instead, specify only "datafile.dat"
	 *
	 * @param filename The name of the file to retrieve.
	 * @param mustAlreadyExist True if the file must already exist on the filesystem.
	 *
	 * @return A File descriptor to the specified data file, or null if there were any issues.
	 */
	private File getDataFile(String filename, boolean mustAlreadyExist) {
	    if (createDataDirectory()) {
	        File file = new File(this.getDataFolder(), filename);
	        if (mustAlreadyExist) {
	            if (file.exists()) {
	                return file;
	            }
	        } else {
	            return file;
	        }
	    }
	    return null;
	}

	public void resetTicks() {
		lastUseMap = new HashMap<String, Integer>(0);
	}
	
	

}
