package com.fourmc.aiassister.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fourmc.aiassister.Main;

public class RemoveLocationCommand implements CommandExecutor {
	
	private Main plugin;
	
	public RemoveLocationCommand(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginCommand("removeLocation").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				p.sendMessage(ChatColor.RED+"Correct usage: /removeLocation <name>");
			} else {
				if (plugin.locations.containsKey(args[0])) {
					plugin.locations.remove(args[0]);
					p.sendMessage(ChatColor.GREEN+"Successfully deleted location "+args[0]);
				} else {
					p.sendMessage(ChatColor.RED+"Sorry! This location doesn't exist!");
				}
			}
		}
		return false;
	}
	

}
