package com.veltro.linearlogic.cstaff;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CStaff extends JavaPlugin {

	public void onEnable() {
		getLogger().info("Loading config...");
		saveDefaultConfig();

		getLogger().info("Registering listener...");
		getServer().getPluginManager().registerEvents(new CSListener(this), this);

		getLogger().info("Activating command handler...");
		getCommand("cstaff").setExecutor(new CSCommand(this));
		
		getLogger().info("Enabled!");
	}

	public void onDisable() {
		saveConfig();
		getLogger().info("Disabled!");
	}

	public void sendOnlineInfo(CommandSender sender) {
		ArrayList<String> staff = new ArrayList<String>(), donors = new ArrayList<String>();
		for (Player p : getServer().getOnlinePlayers()) {
			if (p.hasPermission("cstaff.staff") || (p.isOp() && getConfig().getBoolean("ops.show-as-staff")))
				staff.add(getConfig().getBoolean("use-displaynames") ? p.getDisplayName() : p.getName());
			if (p.hasPermission("cstaff.donor") || (p.isOp() && getConfig().getBoolean("ops.show-as-donors")))
				staff.add(getConfig().getBoolean("use-displaynames") ? p.getDisplayName() : p.getName());
		}
		ChatColor c1, c2;
		switch (getConfig().getInt("color-scheme")) {
			default:
			case 1:
				c1 = ChatColor.BLUE;
				c2 = ChatColor.DARK_AQUA;
				break;
			case 2:
				c1 = ChatColor.DARK_GREEN;
				c2 = ChatColor.GREEN;
				break;
			case 3:
				c1 = ChatColor.BLACK;
				c2 = ChatColor.DARK_GRAY;
				break;
			case 4:
				c1 = ChatColor.DARK_RED;
				c2 = ChatColor.RED;
				break;
			case 5:
				c1 = ChatColor.DARK_PURPLE;
				c2 = ChatColor.LIGHT_PURPLE;
				break;
			case 6:
				c1 = ChatColor.GOLD;
				c2 = ChatColor.YELLOW;
				break;
		}
		sender.sendMessage(c1 + "----------------------[" + c2 + "cStaff" + c1 + "]----------------------\n\n      --=" +
			c2 + " There are " + c1 + "(" + c2 + getServer().getOnlinePlayers().length + c1 + "/" + c2 +
			getServer().getMaxPlayers() + c1 + ") " + c2 + "users currently online." + c1 + " =--\n\n" +
			(staff.size() == 0 ? c1 + "There are currently no staff members online!" : c2 + "Staff online" + c1 +
			" (" + c2 + staff.size() + c1 + "): " + ChatColor.GRAY + staff.toString()) + "\n\n" + (donors.size() == 0
			? c1 + "There are currently no donors online!" : c2 + "Donors online" + c1 + " (" + c2 + donors.size()
			+ c1 + "):" + ChatColor.GRAY + donors.toString()) + c1 + "\n\n-----------------------------------------" +
			"----------");
	}

	public boolean msgNoPerms(CommandSender sender) {
		sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.BLUE + "cStaff" + ChatColor.GRAY + "] " + ChatColor.RED +
				"Access denied!");
		return true;
	}
}
