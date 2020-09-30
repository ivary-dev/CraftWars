package com.ivary.commands;

import com.ivary.main.CraftWars;
import com.ivary.methods.Methods;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CraftWarsCMD implements CommandExecutor {

    Methods m = new Methods();

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        /*if (!(sender instanceof Player)) {
            sender.sendMessage("Only executable by Players");
            return true;
        }*/
        //Player p = (Player) sender;
        if (args.length == 0) {
            sender.sendMessage(m.cC("&7&m-----------------------\n&bAuthor - &eivary\n&bDiscord - &ejayy#0254\n&bVersion - &e1.0\n&bAliases - /craftwars & /cw\n&7&m-----------------------"));
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                sender.sendMessage(m.cC("&8[&bCW&8] &bPlugin is being reloaded.\n&8[&bCW&8] &bTo see results: check console"));
                CraftWars.instance.loadConfig();
            }
            if (args[0].equalsIgnoreCase("start")) {
                int i = 0;
                for (Player onlineP : Bukkit.getOnlinePlayers()) {
                    i++;
                }
                if (i>=3) {
                    sender.sendMessage(m.cC("&8[&bCW&8] &74 people is the minimum for CraftWars!"));
                    return true;
                }
                m.startCraftWars();
                Bukkit.broadcastMessage(m.cC("&8[&bCW&8] &7CraftWars was manually started by " + sender.getName()));
            }
            if (args[0].equalsIgnoreCase("stop")) {
                m.stopCraftWarsManual();
                sender.sendMessage(m.cC("&8[&bCW&8] &bCraftWars has been stopped"));
                return true;
            }
        }
        sender.sendMessage(m.cC("&8[&bCW&8] &bInvalid Command"));
        return true;
    }

}
