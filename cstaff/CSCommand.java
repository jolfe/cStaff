package com.veltro.linearlogic.cstaff;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CSCommand implements CommandExecutor {

	private final String prefix = ChatColor.GRAY + "[" + ChatColor.BLUE + "cStaff" + ChatColor.GRAY + "] ";
	private CStaff plugin;

	public CSCommand(CStaff instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			plugin.sendOnlineInfo(sender);
			return true;
		}
		if (args[0].equalsIgnoreCase("version")) {
			sender.sendMessage(prefix + "Running " + ChatColor.DARK_AQUA + "v" +
					plugin.getDescription().getVersion() + ChatColor.GRAY + " by LinearLogic and Developher.");
			return true;
		}
		if (args[0].equalsIgnoreCase("reload")) {
			if (sender instanceof Player && !sender.hasPermission("cstaff.reload"))
				return plugin.msgNoPerms(sender);
			plugin.reloadConfig();
			sender.sendMessage(prefix + ChatColor.GREEN + "Config reloaded!");
			return true;
		}
		if (args[0].equalsIgnoreCase("help") || args[0].equals("?")) {
			sender.sendMessage(prefix + ChatColor.DARK_AQUA + " Commands:\n" + ChatColor.AQUA + "/staff" +
					ChatColor.GRAY + " - lists online staff members and donors\n" + ChatColor.AQUA + "/cstaff " +
					"reload" + ChatColor.GRAY + " - reloads the config, applying color scheme changes\n" +
					ChatColor.AQUA + "/cstaff version" + ChatColor.GRAY + " - displays version and authors");
			return true;
		}
		sender.sendMessage(prefix + "Command not recognized. Type " + ChatColor.AQUA + "/cstaff help" +
				ChatColor.GRAY + " for assistance.");
		return true;
	}
}
