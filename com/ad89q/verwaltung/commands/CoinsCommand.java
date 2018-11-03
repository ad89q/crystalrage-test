package com.ad89q.verwaltung.commands;

import com.ad89q.verwaltung.Verwaltung;
import com.ad89q.verwaltung.data.Coins;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CoinsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = Verwaltung.getBankPrefix();
        Coins co = Coins.getInstance();

        if(label.equalsIgnoreCase("coins")) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                if(p.hasPermission("verwaltung.coins")) {
                    if(args.length == 0) {
                        Double coins = co.getCoins(p);
                        p.sendMessage(prefix + ChatColor.GRAY + "Deine Coins: " + ChatColor.GOLD + coins);
                    } else if(args.length == 1) {
                        if(p.hasPermission("verwaltung.coins.other")) {
                            String arg = args[0];
                            Player t = Bukkit.getPlayer(arg);
                            if(arg.length() > 16) {
                                p.sendMessage(prefix + ChatColor.GRAY + "Der Spielername darf nicht mehr als 16 Zeichen enthalten!");
                            } else if(t == p) {
                                Double coins = co.getCoins(p);
                                p.sendMessage(prefix + ChatColor.GRAY + "Deine Coins: " + ChatColor.GOLD + coins + " Coins");
                            } else if(t != null) {
                                Double coins = co.getCoins(t);
                                p.sendMessage(prefix + ChatColor.GRAY + "Coins von " + ChatColor.GOLD + t.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.GOLD + coins + " Coins");
                            } else {
                                OfflinePlayer offlineTarget = Bukkit.getOfflinePlayer(arg);
                                if(offlineTarget != null) {
                                    Double coins = co.getCoins(offlineTarget);
                                    p.sendMessage(prefix + ChatColor.GRAY + "Coins von " + ChatColor.GOLD + arg + ChatColor.GRAY + ": " + ChatColor.GOLD + coins + " Coins");
                                } else {
                                    p.sendMessage(prefix + ChatColor.GRAY + "Der Spieler " + ChatColor.GOLD + arg + ChatColor.GRAY + " war noch nie online!");
                                }
                            }
                        } else {
                            p.sendMessage(prefix + ChatColor.GRAY + "Du hast keinen Zugriff auf diesen Befehl!");
                        }
                    } else {
                        p.sendMessage(prefix + ChatColor.GRAY + "Benutzung: " + ChatColor.GOLD + "/coins <Spieler>");
                    }
                } else {
                    p.sendMessage(prefix + ChatColor.GRAY + "Du hast keinen Zugriff auf diesen Befehl!");
                }
            } else {
                ConsoleCommandSender c = (ConsoleCommandSender) sender;
            }
        }
        return true;
    }
}
