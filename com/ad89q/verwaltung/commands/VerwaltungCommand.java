package com.ad89q.verwaltung.commands;

import com.ad89q.verwaltung.Verwaltung;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class VerwaltungCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = Verwaltung.getPrefix();

        if(label.equalsIgnoreCase("verwaltung")) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                if(p.hasPermission("verwaltung.verwaltung")) {
                    if(args.length == 0) {
                        p.sendMessage(prefix + ChatColor.GRAY + "Benutzung: ");
                        p.sendMessage(prefix + ChatColor.RED + "/verwaltung version");
                    } else if(args.length == 1) {
                        String arg = args[0];
                        if(arg.equalsIgnoreCase("version")) {
                            if(p.hasPermission("verwaltung.verwaltung.version")) {
                                p.sendMessage(prefix + ChatColor.GRAY + "Version: " + ChatColor.RED + "1.0-SNAPSHOT");
                            } else {
                                p.sendMessage(prefix + ChatColor.GRAY + "Du hast keinen Zugriff auf diesen Befehl!");
                            }
                        } else {
                            p.sendMessage(prefix + ChatColor.GRAY + "Benutzung: ");
                            p.sendMessage(prefix + ChatColor.RED + "/verwaltung version");
                        }
                    } else {
                        p.sendMessage(prefix + ChatColor.GRAY + "Benutzung: ");
                        p.sendMessage(prefix + ChatColor.RED + "/verwaltung version");
                    }
                } else {
                    p.sendMessage(prefix + ChatColor.GRAY + "Du hast keinen Zugriff auf diesen Befehl!");
                }
            } else {
                ConsoleCommandSender c = (ConsoleCommandSender) sender;
                if(args.length == 0) {
                    c.sendMessage(prefix + ChatColor.GRAY + "Benutzung: ");
                    c.sendMessage(prefix + ChatColor.RED + "/verwaltung version");
                } else if(args.length == 1) {
                    String arg = args[0];
                    if(arg.equalsIgnoreCase("version")) {
                        c.sendMessage(prefix + ChatColor.GRAY + "Version: " + ChatColor.RED + "1.0-SNAPSHOT");
                    } else {
                        c.sendMessage(prefix + ChatColor.GRAY + "Benutzung: ");
                        c.sendMessage(prefix + ChatColor.RED + "/verwaltung version");
                    }
                } else {
                    c.sendMessage(prefix + ChatColor.GRAY + "Benutzung: ");
                    c.sendMessage(prefix + ChatColor.RED + "/verwaltung version");
                }
            }
        }
        return true;
    }
}
