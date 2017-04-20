package com.electro2560.dev.AntiTNTChain.updater;

import java.beans.ConstructorProperties;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.electro2560.dev.AntiTNTChain.AntiTNTChain;
import com.electro2560.dev.AntiTNTChain.utils.Perms;
import com.electro2560.dev.AntiTNTChain.utils.Utils;

public class UpdateListener implements Listener {
	private final AntiTNTChain plugin;

	@ConstructorProperties({ "plugin" })
	public UpdateListener(AntiTNTChain plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (e.getPlayer().hasPermission(Perms.canCheckForUpdates) && Utils.isCheckForUpdates()) {
			UpdateUtil.sendUpdateMessage(e.getPlayer(), plugin);
		}
	}
}
