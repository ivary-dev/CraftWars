package com.ivary.listeners;

import com.ivary.main.CraftWars;
import com.ivary.methods.Methods;
import com.ivary.methods.Teams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class Crafting implements Listener {

    Methods m = new Methods();

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        Player p = (Player) e.getWhoClicked();
        if (CraftWars.instance.getPlayerCraft().get(p.getUniqueId()).equals(e.getRecipe().getResult().getType())) {
            CraftWars.instance.getPlayerCraft().remove(p.getUniqueId());
            Teams.instance.getNotCrafted().removeEntry(p.getName());
            Teams.instance.getCrafted().addEntry(p.getName());
            Bukkit.broadcastMessage(m.cC("&a" + p + "&7 has crafted their item! They can now kill!"));
            p.sendTitle(m.cC("&aYou have crafted your item!"), m.cC("&cYou can now kill!"), 20, 60, 20);
        }
    }
}
