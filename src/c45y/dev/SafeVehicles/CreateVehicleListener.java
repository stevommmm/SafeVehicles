package c45y.dev.SafeVehicles;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Minecart;
import org.bukkit.event.vehicle.VehicleListener;
import org.bukkit.event.vehicle.VehicleUpdateEvent;
import org.bukkit.inventory.ItemStack;

public class CreateVehicleListener extends VehicleListener{
	public SafeVehicles plugin;

	public CreateVehicleListener(SafeVehicles instance) {
		plugin = instance;
	}


	public void onVehicleUpdate( VehicleUpdateEvent event) {
		if ((event.getVehicle() instanceof Boat)) {
			Location BoatLoc = event.getVehicle().getLocation();
			Material block = BoatLoc.getWorld().getBlockAt(BoatLoc.getBlockX(), (BoatLoc.getBlockY() - 1), BoatLoc.getBlockZ()).getType();
			if (block != Material.STATIONARY_WATER && block != Material.WATER) {
				block = BoatLoc.getWorld().getBlockAt(BoatLoc).getType();
				if (block != Material.STATIONARY_WATER && block != Material.WATER) {
					if (plugin.getConfig().getBoolean("SaftVehicles.Remove.Minecart")) {
						event.getVehicle().remove();
						plugin.log.info("Boat removed from " + BoatLoc.getBlockX() + "-" +BoatLoc.getBlockY() + "-" +BoatLoc.getBlockZ() + " [" + block.name() + "]");
						if (plugin.getConfig().getBoolean("SaftVehicles.Drop.Minecart")) {
							BoatLoc.getWorld().dropItem(BoatLoc,new ItemStack(Material.BOAT, 1));
						}
					}
				}
			}
		}
		if ((event.getVehicle() instanceof Minecart)) {
			Location CartLoc = event.getVehicle().getLocation();
			Material block = CartLoc.getWorld().getBlockAt(CartLoc.getBlockX(), (CartLoc.getBlockY() - 1), CartLoc.getBlockZ()).getType();
			if (block != Material.RAILS && block != Material.POWERED_RAIL && block != Material.DETECTOR_RAIL) {
				block = CartLoc.getWorld().getBlockAt(CartLoc).getType();
				if (block != Material.RAILS && block != Material.POWERED_RAIL && block != Material.DETECTOR_RAIL) {
					if (plugin.getConfig().getBoolean("SaftVehicles.Remove.Minecart")) {
						event.getVehicle().remove();
						plugin.log.info("Minecart removed from " + CartLoc.getBlockX() + "-" +CartLoc.getBlockY() + "-" +CartLoc.getBlockZ() + " [" + block.name() + "]");
						if (plugin.getConfig().getBoolean("SaftVehicles.Drop.Minecart")) {
							CartLoc.getWorld().dropItem(CartLoc,new ItemStack(Material.MINECART, 1));
						}
					}
					
				}
			}
		}
	}


}
