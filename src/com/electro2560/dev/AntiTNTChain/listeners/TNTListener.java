package com.electro2560.dev.AntiTNTChain.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import com.electro2560.dev.AntiTNTChain.AntiTNTChain;
import com.electro2560.dev.AntiTNTChain.utils.Utils;

public class TNTListener implements Listener{
	
	@EventHandler
	public void onExplode(EntityExplodeEvent event){
		Entity e = event.getEntity();
		if(!(e instanceof TNTPrimed)) return;
		
		int tnt = 1;
		
		for(Block b : event.blockList()) if(b.getType() == Material.TNT) tnt++;
		
		Location l = e.getLocation();
		ArrayList<TNTPrimed> TP = Utils.getNearbyTNT(l, 7);
		
		if(TP != null) tnt += TP.size();

		if(tnt > AntiTNTChain.get().getConfig().getInt("maxTNT")){
			event.setCancelled(true);
			event.blockList().clear();
			if(TP != null) for(TNTPrimed t : TP) t.remove();
			
			for(Player p : Bukkit.getOnlinePlayers()){
				if(p.getLocation().distance(l) > AntiTNTChain.get().getConfig().getInt("messageDistance")) continue;
				p.sendMessage(AntiTNTChain.get().getConfig().getString("chainMessage").replaceAll("\\{maxTNT\\}", AntiTNTChain.get().getConfig().getString("maxTNT")).replaceAll("&", "§"));
			}
		}
	}
	
}
