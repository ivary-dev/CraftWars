package com.ivary.main;

import com.google.common.base.Enums;
import com.ivary.commands.CraftWarsCMD;
import com.ivary.listeners.Crafting;
import com.ivary.listeners.Damaging;
import com.ivary.listeners.Joining;
import com.ivary.methods.Methods;
import com.ivary.methods.Teams;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CraftWars extends JavaPlugin {

    Methods m = new Methods();
    public static CraftWars instance;
    List<String> configList;
    List<Material> craftableItems = new ArrayList<Material>();
    Location startLoc;
    Location joinLoc;
    FileConfiguration config = getConfig();
    File cfile;
    HashMap<UUID, Material> playerCraft = new HashMap<UUID, Material>();

    public void onEnable() {
        instance = this;
        getCommands();
        registerListeners();

        config.options().copyDefaults(true);
        saveConfig();
        cfile = new File(getDataFolder(), "config.yml");
        loadConfig();
        this.getServer().getScheduler().runTaskLater(this, new Runnable() {
            public void run() {
                Teams.instance.initTeams();
            }
        }, 0);
    }


    public void onDisable() {
        instance = null;
    }


    public void getCommands() {
        this.getServer().getPluginCommand("craftwars").setExecutor(new CraftWarsCMD());
    }

    public void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new Damaging(), this);
        this.getServer().getPluginManager().registerEvents(new Crafting(), this);
        this.getServer().getPluginManager().registerEvents(new Joining(), this);
    }


    public void loadConfig() {
        config = YamlConfiguration.loadConfiguration(cfile);
        if (getConfig().getStringList("craftable-items") == null) {
            System.out.println("There is an issue with your config, and has caused this plugin to break.\nERROR: Could not find config\nIf this issue continues, please contact jayy#0254 on discord for further assistance.");
            this.getPluginLoader().disablePlugin(this);
            return;
        }
        configList = (List<String>) getConfig().getList("craftable-items");
        int i = 3;
        for (String str : configList) {
            String s;
            s = str.toUpperCase();
            s = s.replace(" ", "_");
            i++;
            Material mat = Enums.getIfPresent(Material.class, s).isPresent() ? Material.valueOf(s) : null;
            if (mat == null) {
                System.out.println("There is an issue with your config, and has caused this plugin to break.\nERROR: Could not translate to a Material: Invalid string\nError line: " + i + "\nExtra Detail: " + str + " was translated to Material." + s);
                this.getPluginLoader().disablePlugin(this);
                return;
            }
            craftableItems.add(mat);
        }
        if (m.stringToLoc(config.getString("join-location")) == null) {
            System.out.println("There is an issue with your config, however, it is not fatal\nERROR: Your joim-location is invalid, and must be provided in the format\n\"x,y,z world\", with them spacings. If a player joins: there will be an NPE in console and they will be teleported to the worlds spawn.");
        }
        if (m.stringToLoc(config.getString("start-location")) == null) {
            System.out.println("There is an issue with your config, however, it is not fatal\nERROR: Your start-location is invalid, and must be provided in the format\\n\\\"x,y,z world\\\", with them spacings. If a game starts: there will be an NPE in console and they will not be teleported");
        }
        startLoc = m.stringToLoc(config.getString("start-location"));
        joinLoc = m.stringToLoc(config.getString("join-location"));

    }

    public List<Material> getCraftingList() {
        return craftableItems;
    }

    public HashMap getPlayerCraft(){
        return playerCraft;
    }

    public Location getStartLoc() {
        return startLoc;
    }

    public Location getJoinLoc() {
        return joinLoc;
    }
}
