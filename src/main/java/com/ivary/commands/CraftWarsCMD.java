package com.ivary.commands;

import com.ivary.main.CraftWars;
import com.ivary.methods.Methods;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CraftWarsCMD implements CommandExecutor {

    Methods m = new Methods();

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only executable by Players");
            return true;
        }
        Player p = (Player) sender;
        if (args.length == 0) {
            sender.sendMessage(m.cC("&7&m-----------------------\n&bAuthor - &eivary\n&bDiscord - &ejayy#0254\n&bVersion - &e1.0\n&bAliases - /craftwars & /cw"));
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                p.sendMessage(m.cC("&8[&bCW&8] &bPlugin is being reloaded.\n&8[&bCW&8] &bTo see results: check console"));
                CraftWars.instance.loadConfig();
            }
        }
        return true;
    }

}
