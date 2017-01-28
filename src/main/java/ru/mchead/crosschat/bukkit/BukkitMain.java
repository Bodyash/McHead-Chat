package ru.mchead.crosschat.bukkit;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ru.mchead.crosschat.bukkit.socket.SocketWorker;
import ru.mchead.crosschat.bukkit.utils.ConfigUtil;

public class BukkitMain extends JavaPlugin {
	ConfigUtil config;
	SocketWorker sw;
	@Override
	public void onEnable() {
		config = new ConfigUtil(this.getDataFolder());
		sw = new SocketWorker(config);
		Bukkit.getLogger().log(Level.INFO, config.getLogo() + "Trying to create new connection");
		
		
	}

}
