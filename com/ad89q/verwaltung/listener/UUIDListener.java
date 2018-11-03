package com.ad89q.verwaltung.listener;

import com.ad89q.verwaltung.data.UUIDData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class UUIDListener implements Listener {

    @EventHandler
    public void handleUUID(PlayerLoginEvent event) {
        UUIDData.getInstance().addPlayerToConfig(event.getPlayer());
    }
}
