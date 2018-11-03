package com.ad89q.verwaltung.commands;

import com.ad89q.verwaltung.Verwaltung;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = Verwaltung.getPrefix();

        if(label.equalsIgnoreCase("heal")) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                if(p.hasPermission("verwaltung.heal")) {
                    if(args.length == 0) {
                        p.setHealth(20.0D);
                        p.setFoodLevel(20);
                        p.setFireTicks(0);
                        for(PotionEffect pe : p.getActivePotionEffects()) {
                            p.removePotionEffect(pe.getType());
                        }
                        p.sendMessage(prefix + ChatColor.GRAY + "Du wurdest geheilt.");
                    } else if(args.length == 1) {
                        if(p.hasPermission("verwaltung.heal.other")) {
                            String arg = args[0];
                            Player t = Bukkit.getPlayer(arg);
                            if(arg.length() > 16) {
                                p.sendMessage(prefix + ChatColor.GRAY + "Der Spielername darf nicht mehr als 16 Zeichen enthalten!");
                            } else if(t == p) {
                                p.setHealth(20.0D);
                                p.setFoodLevel(20);
                                p.setFireTicks(0);
                                for(PotionEffect pe : p.getActivePotionEffects()) {
                                    p.removePotionEffect(pe.getType());
                                }
                                p.sendMessage(prefix + ChatColor.GRAY + "Du wurdest geheilt.");
                            } else if(arg.equalsIgnoreCase("*")) {
                                if(p.hasPermission("verwaltung.heal.all")) {
                                    for(Player op : Bukkit.getOnlinePlayers()) {
                                        if (op.getHealth() != 0) {
                                            op.setHealth(20.0D);
                                            op.setFoodLevel(20);
                                            op.setFireTicks(0);
                                            for (PotionEffect pe : op.getActivePotionEffects()) {
                                                op.removePotionEffect(pe.getType());
                                            }
                                            op.sendMessage(prefix + ChatColor.GRAY + "Du wurdest von " + ChatColor.RED + "Gott" + ChatColor.GRAY + " geheilt.");
                                        }
                                        p.sendMessage(prefix + ChatColor.GRAY + "Du hast alle Spieler geheilt.");
                                    }
                                } else {
                                    p.sendMessage(prefix + ChatColor.GRAY +  "Du hast keinen Zugriff auf diesen Befehl!");
                                }
                            } else if(t != null) {
                                if(t.getHealth() != 0.0D) {
                                    t.setHealth(20.0D);
                                    t.setFoodLevel(20);
                                    t.setFireTicks(0);
                                    for(PotionEffect pe : t.getActivePotionEffects()) {
                                        t.removePotionEffect(pe.getType());
                                    }
                                    t.sendMessage(prefix + ChatColor.GRAY + "Du wurdest von " + ChatColor.RED + p.getDisplayName() + ChatColor.GRAY + " geheilt.");
                                    p.sendMessage(prefix + ChatColor.GRAY + "Du hast den Spieler " + ChatColor.RED + t.getDisplayName() + ChatColor.GRAY + " geheilt.");
                                } else {
                                    p.sendMessage(prefix + ChatColor.GRAY + "Der Spieler " + ChatColor.RED + t.getDisplayName() + ChatColor.GRAY + " ist bereits tot!");
                                }
                            } else {
                                p.sendMessage(prefix + ChatColor.GRAY + "Der Spieler " + ChatColor.RED + arg + ChatColor.GRAY + " ist nicht online!");
                            }
                        } else {
                            p.sendMessage(prefix + ChatColor.GRAY +  "Du hast keinen Zugriff auf diesen Befehl!");
                        }
                    } else {
                        p.sendMessage(prefix + ChatColor.GRAY + "Benutzung: " + ChatColor.RED + "/heal <Spieler>");
                    }
                } else {
                    p.sendMessage(prefix + ChatColor.GRAY +  "Du hast keinen Zugriff auf diesen Befehl!");
                }
            } else {
                ConsoleCommandSender c = (ConsoleCommandSender) sender;
                if(args.length == 1) {
                    String arg = args[0];
                    Player t = Bukkit.getPlayer(arg);
                    if(arg.length() > 16) {
                        c.sendMessage(prefix + ChatColor.GRAY + "Der Spielername darf nicht mehr als 16 Zeichen enthalten!");
                    } else if(arg.equalsIgnoreCase("*")) {
                        for(Player op : Bukkit.getOnlinePlayers()) {
                            if(op.getHealth() != 0) {
                                op.setHealth(20.0D);
                                op.setFoodLevel(20);
                                op.setFireTicks(0);
                                for(PotionEffect pe : op.getActivePotionEffects()) {
                                    op.removePotionEffect(pe.getType());
                                }
                                op.sendMessage(prefix + ChatColor.GRAY + "Du wurdest von " + ChatColor.RED + "Gott" + ChatColor.GRAY + " geheilt.");
                            }
                            c.sendMessage(prefix + ChatColor.GRAY + "Du hast alle Spieler geheilt.");
                        }
                    } else if(t != null) {
                        if(t.getHealth() != 0.0D) {
                            t.setHealth(20.0D);
                            t.setFoodLevel(20);
                            t.setFireTicks(0);
                            for(PotionEffect pe : t.getActivePotionEffects()) {
                                t.removePotionEffect(pe.getType());
                            }
                            t.sendMessage(prefix + ChatColor.GRAY + "Du wurdest von " + ChatColor.RED + "Gott" + ChatColor.GRAY + " geheilt.");
                            c.sendMessage(prefix + ChatColor.GRAY + "Du hast den Spieler " + ChatColor.RED + t.getDisplayName() + ChatColor.GRAY + " geheilt.");
                        } else {
                            c.sendMessage(prefix + ChatColor.GRAY + "Der Spieler " + ChatColor.RED + t.getDisplayName() + ChatColor.GRAY + " ist bereits tot!");
                        }
                    } else {
                        c.sendMessage(prefix + ChatColor.GRAY + "Der Spieler " + ChatColor.RED + arg + ChatColor.GRAY + " ist nicht online!");
                    }
                } else {
                    c.sendMessage(prefix + ChatColor.GRAY + "Benutzung: " + ChatColor.RED + "/heal [Spieler]");
                }
            }
        }
        return true;
    }
}
