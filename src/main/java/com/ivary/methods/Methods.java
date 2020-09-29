package com.ivary.methods;

import com.ivary.main.CraftWars;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Random;

public class Methods {

    Random random = new Random();

    public String cC(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public void startCraftWars() {

        for (Player p : Bukkit.getOnlinePlayers()) {
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
            Teams.instance.getNotCrafted().addEntry(p.getName());
            p.setGlowing(true);
            Material mat = CraftWars.instance.getCraftingList().get(this.random.nextInt(CraftWars.instance.getCraftingList().size()));
            CraftWars.instance.getPlayerCraft().put(p.getUniqueId(), mat);
            p.teleport(CraftWars.instance.getStartLoc());
            p.sendTitle(cC("&c" + mat), cC("&cGo craft as quickly as you can!"), 20, 60, 20);
        }

    }

    public void stopCraftWars(Player winner) {

        for (Player p : Bukkit.getOnlinePlayers()) {
            Teams.instance.getCrafted().removeEntry(p.getName());
            Teams.instance.getNotCrafted().removeEntry(p.getName());
            CraftWars.instance.getPlayerCraft().clear();
            p.setGlowing(false);
            p.sendTitle(cC("&aThe Winner is &e" + winner + "&a!"), cC("&7Congratulations:)"), 20, 60, 20);
        }
        Bukkit.broadcastMessage(cC("&8[&bCW&8] CraftWars is over!"));
    }

    public Location stringToLoc(String s){
        String[] str = s.split(",");
        String[] worldPlusZ = str[2].split(" ");
        Location loc = new Location(Bukkit.getWorld(worldPlusZ[1]), Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(worldPlusZ[0]));
        return loc;
    }
}
