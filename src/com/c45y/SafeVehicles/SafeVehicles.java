package com.c45y.SafeVehicles;

import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SafeVehicles extends JavaPlugin {
	private final ExitVehicleListener ExitVehicleListener = new ExitVehicleListener(this);
	private final CreateVehicleListener CreateVehicleListener = new CreateVehicleListener(this);
	Logger log = Logger.getLogger("Minecraft");

	public void onEnable(){ 
		final FileConfiguration config = this.getConfig();
		loadConfiguration(config);
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.VEHICLE_EXIT, ExitVehicleListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.VEHICLE_UPDATE, CreateVehicleListener, Event.Priority.Normal, this);
		log.info("SafeVehicles enabled.");
	}

	public void onDisable(){ 
		log.info("SafeVehicles disabled.");
	}

	public void loadConfiguration(FileConfiguration config){
		config.options().header("Header to be written");
		config.addDefault("SafeVehicles.Remove.Minecart", false);
		config.addDefault("SafeVehicles.Remove.Boat", false);
		config.addDefault("SafeVehicles.Drop.Minecart", false);
		config.addDefault("SafeVehicles.Drop.Boat", false);
		config.addDefault("SafeVehicles.OnExit.Drop", false);
		config.addDefault("SafeVehicles.OnExit.Removeonly", false);
		config.addDefault("SafeVehicles.OnExit.Inventory", false);
		config.options().copyDefaults(true);
		saveConfig();
	}
}
