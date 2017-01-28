package ru.mchead.crosschat.bukkit.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigUtil {
	//Variables
	private String logo = "[McHead Chat]";
	
	//Connection
	private String connection = "Connection.";

	private String connectionIp = "127.0.0.1";
	private String connectionIpPath = String.valueOf(this.connection) + "ip";

	private int connectionPort = 12345;
	private String connectionPortPath = String.valueOf(this.connection) + "port";

	
	//FILES
	private File configFile;
	private YamlConfiguration config;

	public ConfigUtil(File dataFolder) {
		this.configFile = new File(dataFolder, "config.yml");
		this.config = YamlConfiguration.loadConfiguration(configFile);
		this.startup();
	}

	private void startup() {
		if (!this.configFile.exists()) {
			Bukkit.getLogger().log(Level.WARNING, this.logo + "... Starting config creation ...");
			this.createConfig();
		} else {
			Bukkit.getLogger().log(Level.WARNING, this.logo + "... Starting config L O A D I N G ...");
			this.loadConfig();
		}
	}

	private void loadConfig() {
		
		if (config.getString(connectionIpPath).isEmpty()){
			Bukkit.getLogger().log(Level.WARNING, "Some problems when loading config /ConnectionIp/");
			this.connectionIp = "127.0.0.1";
		}else{
			this.connectionIp = config.getString(connectionIpPath);
		}
		
		if (config.getString(connectionPortPath).isEmpty()){
			Bukkit.getLogger().log(Level.WARNING, "Some problems when loading config /ConnectionPort/");
			this.connectionPort = 12345;
		}else{
			try {
				this.connectionPort = Integer.valueOf(config.getString(connectionIpPath));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

	public String getConnectionIp() {
		return connectionIp;
	}

	public int getConnectionPort() {
		return connectionPort;
	}

	private void createConfig() {
		this.config.options().header("First Version of McHead Chat Plugin");
		this.config.set(connectionIpPath, connectionIp);
		this.config.set(connectionPortPath, connectionPort);
		this.saveConfig();
	}
	
	private void saveConfig() {
		try {
            this.config.save(this.configFile);
            System.out.println("... Finished config creation!");
        }
        catch (IOException e) {
            System.err.println("Can't create config file, see info below:");
            e.printStackTrace();
        }
	}

	public String getLogo() {
		return null;
	}

}
