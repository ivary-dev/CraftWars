package com.ivary.listeners;

import com.ivary.main.CraftWars;
import com.ivary.methods.Methods;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Joining implements Listener {

    Methods m = new Methods();

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        e.getPlayer().teleport(CraftWars.instance.getJoinLoc());
        Bukkit.broadcastMessage(m.cC("&a+ &7" + e.getPlayer().getDisplayName()));
    }
}
