package com.brandonmabey.telepad;


public class TickUpdater implements Runnable {

	private Core core;
	
	public TickUpdater(Core c) {
		this.core = c;
	}
	
	@Override
	public void run() {
//		core.getLogger().info("Updating tick value in tickupdater to " + (core.currentTick + 1));
		core.currentTick++;
		if (core.currentTick >= Integer.MAX_VALUE - 10) {
			core.currentTick = 0;
			core.resetTicks();
		}
		if (core.currentTick % Core.TIME_BETWEEN_SAVES == 0) {
			core.runSave();
		}
	}

}
