package com.fourmc.aiassister.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fourmc.aiassister.Main;
import com.fourmc.aiassister.listeners.PlayerMoveListener;

public class SetLocationCommand implements CommandExecutor {
	
	private Main plugin;
	
	public SetLocationCommand(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginCommand("setLocation").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			Location ploc = p.getLocation();
			if (args.length == 0) {
				p.sendMessage(ChatColor.RED+"Correct usage: /setLocation <name>");
			} else {
				if (plugin.locations.containsKey(args[0])) {
					p.sendMessage(ChatColor.RED+"Sorry! A location with this name already exists!");
				} else {
					plugin.locations.put(args[0], ploc);
					p.sendMessage(ChatColor.GREEN+"Succesfully created new location "+args[0]);
					PlayerMoveListener.executePost(plugin.serverURL+"/createLocation", args[0]+";"+Double.toString(ploc.getX())+";"+Double.toString(ploc.getY())+";"+Double.toString(ploc.getZ()));
				}
			}
		}
		return false;
	}

}
