package com.fourmc.aiassister.listeners;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.fourmc.aiassister.Main;

public class PlayerMoveListener implements Listener {
	
	private Main plugin;
	private String location = "dskjafjkd j aaj sdkjkd fajkf jdkldafj ksdfj ksl sfadjk";
	
	public PlayerMoveListener(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);;
	}
	
	@EventHandler
	public void playerMoveEvent(PlayerMoveEvent event) throws IOException {
		if (!plugin.collecting) {
			return;
		}
		Location playerloc = event.getPlayer().getLocation().clone();
		playerloc.setY(0);
		
		Enumeration<String> e = plugin.locations.keys();
		
		while (e.hasMoreElements() ) {
			String key = e.nextElement();
			
			Location dest = plugin.locations.get(key);
			dest.setY(0);
			
			if (dest.distance(playerloc) < 1) {
				if (!this.location.equals(key)) {
					this.location = key;
					event.getPlayer().sendMessage(ChatColor.GREEN+"Reached location "+key);
					executePost(plugin.serverURL+"/collect", key);
				}
			}
			
		}
		
	}
	
	public static String executePost(String targetURL, String urlParameters) {
		  HttpURLConnection connection = null;

		  try {
		    //Create connection
		    URL url = new URL(targetURL+"?args="+urlParameters);
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type", 
		        "application/x-www-form-urlencoded");

		    connection.setRequestProperty("Content-Length", 
		        Integer.toString(urlParameters.getBytes().length));
		    connection.setRequestProperty("Content-Language", "en-US");  

		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

		    //Send request
		    DataOutputStream wr = new DataOutputStream (
		        connection.getOutputStream());
		    wr.writeBytes(urlParameters);
		    wr.close();

		    //Get Response  
		    InputStream is = connection.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
		    String line;
		    while ((line = rd.readLine()) != null) {
		      response.append(line);
		      response.append('\r');
		    }
		    rd.close();
		    return response.toString();
		  } catch (Exception e) {
		    e.printStackTrace();
		    return null;
		  } finally {
		    if (connection != null) {
		      connection.disconnect();
		    }
		  }
		}

}
