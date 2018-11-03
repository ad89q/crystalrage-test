package com.ad89q.verwaltung;

import com.ad89q.verwaltung.commands.CoinsCommand;
import com.ad89q.verwaltung.commands.HealCommand;
import com.ad89q.verwaltung.commands.VerwaltungCommand;
import com.ad89q.verwaltung.data.Coins;
import com.ad89q.verwaltung.data.PlayerData;
import com.ad89q.verwaltung.data.UUIDData;
import com.ad89q.verwaltung.listener.PlayerDataListener;

import com.ad89q.verwaltung.listener.UUIDListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Verwaltung extends JavaPlugin {

    // Start
    public void onEnable() {
        getCommand("verwaltung").setExecutor(new VerwaltungCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("coins").setExecutor(new CoinsCommand());

        new Coins(this);
        new PlayerData(this);
        new UUIDData(this);

        getServer().getPluginManager().registerEvents(new PlayerDataListener(), this);
        getServer().getPluginManager().registerEvents(new UUIDListener(), this);

        PlayerData.getInstance().createFolder();
        UUIDData.getInstance().setupConfig();

    }

    // Stopp
    public void onDisable() {

    }

    // Präfix
    public static String getPrefix() {
        return ChatColor.GRAY + "[" + ChatColor.RED + "Verwaltung" + ChatColor.GRAY + "] " + ChatColor.RESET;
    }

    // Job-Präfix
    public static String getJobPrefix() {
        return ChatColor.GRAY + "[" + ChatColor.BLUE + "Jobs" + ChatColor.GRAY + "] " + ChatColor.RESET;
    }

    // Bank-Präfix
    public static String getBankPrefix() {
        return ChatColor.GRAY + "[" + ChatColor.GOLD + "Bank" + ChatColor.GRAY + "] " + ChatColor.RESET;
    }
}
