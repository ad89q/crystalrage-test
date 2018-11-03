package com.ad89q.verwaltung.data;

import com.ad89q.verwaltung.Verwaltung;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerData {

    private Verwaltung plugin;
    private static PlayerData instance;

    public PlayerData(Verwaltung plugin) {
        this.plugin = plugin;
        instance = this;
    }

    public static PlayerData getInstance() {
        return instance;
    }

    private File getFile(Player player) {
        return new File(plugin.getDataFolder() + "/playerdata", player.getUniqueId().toString() + ".yml");
    }

    private File getFile(OfflinePlayer offlinePlayer) {
        return new File(plugin.getDataFolder() + "/playerdata", offlinePlayer.getUniqueId().toString() + ".yml");
    }

    public FileConfiguration getConfig(Player player) {
        File file = this.getFile(player);
        return YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig(OfflinePlayer offlinePlayer) {
        File file = this.getFile(offlinePlayer);
        return YamlConfiguration.loadConfiguration(file);
    }

    public void initializePlayer(Player player) {
        File file = this.getFile(player);
        FileConfiguration config = this.getConfig(player);
        config.addDefault("coins", 1000.0D);
        config.options().copyDefaults(true);
        try {
            config.save(file);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void createFolder() {
        File file = new File(plugin.getDataFolder(), "playerdata");
        if(!file.exists()) {
            file.mkdirs();
        }
    }
   public void setDouble(Player player, String path, Double value) {
        File file = this.getFile(player);
        FileConfiguration config = this.getConfig(player);
        config.set(path, value);
        try {
            config.save(file);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
   }

   public void setDouble(OfflinePlayer offlinePlayer, String path, Double value) {
        File file = this.getFile(offlinePlayer);
        FileConfiguration config = this.getConfig(offlinePlayer);
        UUID uuid = this.getConfig(offlinePlayer).get((UUID) offlinePlayer.getName());
        config.set(path, value);
        try {
            config.save(file);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
   }

}
