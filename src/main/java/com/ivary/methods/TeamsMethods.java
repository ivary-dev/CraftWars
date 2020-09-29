package com.ivary.methods;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class TeamsMethods {

    ScoreboardManager manager = Bukkit.getScoreboardManager();
    Scoreboard board = manager.getNewScoreboard();
    Team craftedTeam = board.registerNewTeam("craftedTeam");
    craftedTeam.setAllowFriendlyFire(true);
}
