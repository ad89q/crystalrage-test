package com.ad89q.verwaltung.listener;

import com.ad89q.verwaltung.data.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerDataListener implements Listener {

    @EventHandler
    public void handlePlayerData(PlayerLoginEvent event) {
        Player p = event.getPlayer();
        PlayerData.getInstance().initializePlayer(p);
    }

}
