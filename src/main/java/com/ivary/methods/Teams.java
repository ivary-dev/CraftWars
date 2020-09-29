package com.ivary.methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class Teams {

    public static Teams instance;

    public void TeamMethods(){
        instance = this;
    }

    ScoreboardManager manager = Bukkit.getScoreboardManager();
    Scoreboard board = manager.getNewScoreboard();
    Team crafted = board.registerNewTeam("Crafted");
    Team notCrafted = board.registerNewTeam("Not Crafted");

    public void initTeams(){
        crafted.setAllowFriendlyFire(true);
        crafted.setColor(ChatColor.GREEN);

        notCrafted.setColor(ChatColor.RED);
        notCrafted.setAllowFriendlyFire(false);
    }

    public Team getCrafted() {
        return crafted;
    }

    public Team getNotCrafted() {
        return notCrafted;
    }
}
