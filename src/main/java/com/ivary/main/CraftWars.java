package com.ivary.main;

import com.ivary.commands.CraftWarsCMD;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public class CraftWars extends JavaPlugin {

    public static CraftWars instance;
    List<String> configList;
    List<Material> craftableItems;
    FileConfiguration config = getConfig();
    File cfile;
    public void onEnable() {
        instance = this;
        getCommands();

        config.options().copyDefaults(true);
        saveConfig();
        cfile = new File(getDataFolder(), "config.yml");
        loadConfig();
    }

    public void onDisable() {
        instance = null;
    }

    public void getCommands() {
        this.getServer().getPluginCommand("craftwars").setExecutor(new CraftWarsCMD());
    }

    public void loadConfig() {
        config = YamlConfiguration.loadConfiguration(cfile);
        if (getConfig().getList("craftable-items") == null) {
            System.out.println("There is an issue with your config, and has caused this plugin to break.\nERROR: Could not find config\nIf this issue continues, please contact jayy#0254 on discord for further assistance.");
            this.getPluginLoader().disablePlugin(this);
        }
        configList = (List<String>) getConfig().getList("craftable-items");
        int i = 1;
        for (String s : configList) {
            s = s.toUpperCase();
            s = "Material." + s;
            s = s.replace(" ", "_");
            i++;
            if (Material.getMaterial(s) == null) {
                System.out.println("There is an issue with your config, and has caused this plugin to break.\nERROR: Could not translate to a Material: Invalid string\nError line: " + i);
                this.getPluginLoader().disablePlugin(this);
            }
            craftableItems.add(Material.getMaterial(s));
        }
    }
}
