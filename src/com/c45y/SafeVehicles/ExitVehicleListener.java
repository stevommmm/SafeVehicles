package com.c45y.SafeVehicles;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleListener;
import org.bukkit.inventory.ItemStack;

public class ExitVehicleListener extends VehicleListener{
	public SafeVehicles plugin;

	public ExitVehicleListener(SafeVehicles instance) {
		plugin = instance;
	}

	public void onVehicleExit(VehicleExitEvent event){
		if (plugin.getConfig().getBoolean("SafeVehicles.OnExit.Removeonly")) {
			event.getVehicle().remove();
			return;
		}
		if (plugin.getConfig().getBoolean("SafeVehicles.OnExit.Drop")) {
			event.getVehicle().remove();
			Location Loc = event.getVehicle().getLocation();
			if ((event.getVehicle() instanceof Boat)) {
				ItemStack Minestack = new ItemStack(Material.BOAT, 1);
				Loc.getWorld().dropItem(Loc,Minestack);
				return;
			}else if ((event.getVehicle() instanceof Minecart)) {
				ItemStack Minestack = new ItemStack(Material.MINECART, 1);
				Loc.getWorld().dropItem(Loc,Minestack);
				return;
			}
			return;
		}
		if (plugin.getConfig().getBoolean("SafeVehicles.OnExit.Inventory")) {
			toInventoryVehicle(event);
		}
	}

	private void toInventoryVehicle(VehicleExitEvent event) {
		Location Loc = event.getVehicle().getLocation();
		Player receiver = null;
		event.getVehicle().remove();
		if (event.getExited() != null ) {
			receiver = (Player) event.getExited();
			if ((event.getVehicle() instanceof Boat)) {
				ItemStack invStack = new ItemStack(Material.BOAT, 1);
				try {
					receiver.getInventory().addItem(invStack);
				} catch(Exception e) {
					Loc.getWorld().dropItem(Loc,invStack);
				}
			} else if ((event.getVehicle() instanceof Minecart)) {
				ItemStack invStack = new ItemStack(Material.MINECART, 1);
				try {
					receiver.getInventory().addItem(invStack);
				} catch(Exception e) {
					Loc.getWorld().dropItem(Loc,invStack);
				}
			}
		}
	}


}
