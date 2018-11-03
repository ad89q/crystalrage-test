package com.ad89q.verwaltung.data;

import com.ad89q.verwaltung.Verwaltung;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class UUIDData {

    private Verwaltung plugin;
    private static UUIDData instance;

    public UUIDData(Verwaltung plugin) {
        this.plugin = plugin;
        instance = this;
    }

    public static UUIDData getInstance() {
        return instance;
    }

    public File getFile() {
        return new File(plugin.getDataFolder(), "uuid.yml");
    }

    public FileConfiguration getConfig() {
        File file = this.getFile();
        return YamlConfiguration.loadConfiguration(file);
    }

    public void setupConfig() {
        File file = this.getFile();
        FileConfiguration config = this.getConfig();
        try {
            config.save(file);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addPlayerToConfig(Player player) {
        File file = this.getFile();
        FileConfiguration config = this.getConfig();
        config.set(player.getName(), player.getUniqueId().toString());
        try {
            config.save(file);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
