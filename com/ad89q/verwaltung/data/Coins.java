package com.ad89q.verwaltung.data;

import com.ad89q.verwaltung.Verwaltung;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.io.File;

public class Coins {

    private Verwaltung plugin;
    private static Coins instance;

    public Coins(Verwaltung plugin) {
        this.plugin = plugin;
        instance = this;
    }

    public static Coins getInstance() {
        return instance;
    }

    public Double getCoins(Player player) {
        return PlayerData.getInstance().getConfig(player).getDouble("coins");
    }

    public Double getCoins(OfflinePlayer offlinePlayer) {
        return PlayerData.getInstance().getConfig(offlinePlayer).getDouble("coins");
    }

    public void setCoins(Player player, Double coins) {
        PlayerData.getInstance().setDouble(player, "coins", coins);
    }

    public void addCoins(Player player, Double coins) {
        PlayerData.getInstance().setDouble(player, "coins", getCoins(player) - coins);
    }

    public boolean hasCoins(Player player, Double coins) {
        if(this.getCoins(player) > coins) {
            return true;
        } else return false;
    }
}
