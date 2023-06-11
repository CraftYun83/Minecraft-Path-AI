package com.fourmc.aiassister.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.fourmc.aiassister.Main;

public class SetServerCommand implements CommandExecutor {
	
	private Main plugin;
	
	public SetServerCommand(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginCommand("setServer").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED+"Correct usage: /setServer <URL>");
		} else {
			plugin.serverURL = args[0];
			sender.sendMessage(ChatColor.GREEN+"Successfully set server URL to "+args[0]);
		}
		return false;
	}

}
