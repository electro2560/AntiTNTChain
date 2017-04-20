package com.electro2560.dev.AntiTNTChain;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.electro2560.dev.AntiTNTChain.listeners.TNTListener;
import com.electro2560.dev.AntiTNTChain.updater.UpdateListener;
import com.electro2560.dev.AntiTNTChain.utils.Utils;

public class AntiTNTChain extends JavaPlugin{

	private PluginManager pm = Bukkit.getServer().getPluginManager();
	private static AntiTNTChain instance;
	
	public void onEnable(){
		instance = this;
		
		if(!new File(getDataFolder() + File.separator + "config.yml").exists()) saveDefaultConfig();
		
		pm.registerEvents(new TNTListener(), instance);
		pm.registerEvents(new UpdateListener(instance), instance);
		
		if(getConfig().getBoolean("useMetrics", true)) Utils.startMetrics();
	}
	
	public void onDisable(){
		instance = null;
	}
	
	public static AntiTNTChain get(){
		return instance;
	}

	public String getVersion() {
		return this.getDescription().getVersion();
	}
	
}
