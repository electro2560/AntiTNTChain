package com.electro2560.dev.AntiTNTChain.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;
import org.mcstats.MetricsLite;

import com.electro2560.dev.AntiTNTChain.AntiTNTChain;
import com.electro2560.dev.AntiTNTChain.bstats.Metrics;

public class Utils {

	public static ArrayList<TNTPrimed> getNearbyTNT(Location l, int radius){
		int chunkRadius = radius < 16 ? 1 : (radius - (radius % 16)) / 16;
		HashSet<Entity> radiusEntites = new HashSet<Entity>();
		for (int chX = 0 - chunkRadius; chX <= chunkRadius; chX++) {
			for(int chZ = 0 - chunkRadius; chZ <= chunkRadius; chZ++){
				int x = (int) l.getX(), y = (int) l.getY(), z = (int) l.getZ();
				for(Entity e : new Location(l.getWorld(), x + (chX * 16), y, z + (chZ * 16)).getChunk().getEntities()){
					if(e.getLocation().distance(l) <= radius && e.getLocation().getBlock() != l.getBlock()) radiusEntites.add(e);
				}
			}
		}
		
		Entity[] list = radiusEntites.toArray(new Entity[radiusEntites.size()]);
		ArrayList<TNTPrimed> TNT = new ArrayList<TNTPrimed>();
		
		for(Entity entity : list) if(entity instanceof TNTPrimed) TNT.add((TNTPrimed) entity);
		
		return TNT;
		
	}
	
	public static boolean isCheckForUpdates(){
		return AntiTNTChain.get().getConfig().getBoolean("checkForUpdates", true);
	}
	
	public static String getVersion(){
		return AntiTNTChain.get().getVersion();
	}
	
	public static void startMetrics(){
		try {
	        MetricsLite metrics = new MetricsLite(AntiTNTChain.get());
	        metrics.start();
	    } catch (IOException e) {}
		
		@SuppressWarnings("unused")
		Metrics bstats = new Metrics(AntiTNTChain.get());
	}
	
}
