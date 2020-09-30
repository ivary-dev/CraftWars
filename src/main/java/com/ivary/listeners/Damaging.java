package com.ivary.listeners;

import com.ivary.methods.Methods;
import com.ivary.methods.Teams;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Damaging implements Listener {

    Methods m = new Methods();

    @EventHandler
    public void pvpListen(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }

        Entity p = e.getDamager();
        Player attacked = (Player) e.getEntity();

        if(Teams.instance.getNotCrafted().hasEntry(p.getName())) {
            e.setCancelled(true);
            return;
        }

        if (20 - e.getDamage() <= 0) {
            for (ItemStack item : attacked.getInventory().getContents()) {
                attacked.getLocation().getWorld().dropItem(attacked.getLocation(), item);
            };
            attacked.setGlowing(false);
            attacked.setGameMode(GameMode.SPECTATOR);
            Teams.instance.getCrafted().removeEntry(attacked.getName());
            Teams.instance.getNotCrafted().removeEntry(attacked.getName());
            Bukkit.broadcastMessage(m.cC("&c" + attacked + "&7 was killed by " + p));
            if (Teams.instance.getNotCrafted().getSize() + Teams.instance.getCrafted().getSize() == 1) {
                if (Teams.instance.getCrafted().getSize() == 1) {
                    for (String uuidToString : Teams.instance.getCrafted().getEntries()) {
                        UUID uuid = UUID.fromString(uuidToString);
                        Player winner = Bukkit.getPlayer(uuid);
                        m.stopCraftWars(winner);
                        return;
                    }
                }
                if (Teams.instance.getNotCrafted().getSize() == 1) {
                    for (String player : Teams.instance.getCrafted().getEntries()) {
                        Player winner = Bukkit.getPlayer(player);
                        m.stopCraftWars(winner);
                        return;
                    }
                }
            }
        }
    }
}