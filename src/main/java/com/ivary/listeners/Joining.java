package com.ivary.listeners;

import com.ivary.main.CraftWars;
import com.ivary.methods.Methods;
import com.ivary.methods.Teams;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Joining implements Listener {

    Methods m = new Methods();

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        e.getPlayer().teleport(CraftWars.instance.getJoinLoc());
        e.setJoinMessage(m.cC("&a+ &7" + e.getPlayer().getDisplayName()));
    }

    @EventHandler
    public void playerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage(m.cC("&c- &7" + p.getDisplayName()));
        for (String s : Teams.instance.getCrafted().getEntries()) {
            if (s.equalsIgnoreCase(p.getName())) {
                Teams.instance.getCrafted().removeEntry(p.getName());
            }
        }
        for (String s : Teams.instance.getNotCrafted().getEntries()) {
            if (s.equalsIgnoreCase(p.getName())) {
                Teams.instance.getCrafted().removeEntry(p.getName());
            }
        }
    }
}
