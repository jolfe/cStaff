package com.veltro.linearlogic.cstaff;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class CSListener implements Listener {

	private CStaff plugin;

	public CSListener(CStaff instance) {
		plugin = instance;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (plugin.getConfig().getBoolean("show-on-join"))
			plugin.sendOnlineInfo(e.getPlayer());
	}
}
