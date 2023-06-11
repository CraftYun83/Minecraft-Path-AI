package com.fourmc.aiassister;

import java.util.Hashtable;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import com.fourmc.aiassister.commands.CollectingCommand;
import com.fourmc.aiassister.commands.RemoveLocationCommand;
import com.fourmc.aiassister.commands.SetLocationCommand;
import com.fourmc.aiassister.commands.SetServerCommand;
import com.fourmc.aiassister.listeners.PlayerMoveListener;

public class Main extends JavaPlugin {
	
	public Hashtable<String, Location> locations = new Hashtable<String, Location>();
	public String serverURL = "https://localhost:8080";
	public boolean collecting = false;
	
	@Override
	public void onEnable() {
		new PlayerMoveListener(this);
		new SetLocationCommand(this);
		new RemoveLocationCommand(this);
		new SetServerCommand(this);
		new CollectingCommand(this);
	}

}
