package com.fourmc.aiassister.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.fourmc.aiassister.Main;

public class CollectingCommand implements CommandExecutor {
	
	private Main plugin;
	
	public CollectingCommand(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginCommand("toggleCollecting").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		plugin.collecting = !plugin.collecting;
		return false;
	}
	
	

}
