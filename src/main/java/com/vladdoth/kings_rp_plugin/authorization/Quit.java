package com.vladdoth.kings_rp_plugin.authorization;

import com.vladdoth.kings_rp_plugin.Plugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@Deprecated
public class Quit implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerQuit(PlayerQuitEvent event) {
        Plugin.getInstance().getDb().uploadPlayer(event.getPlayer());
        Plugin.getInstance().getUsers().remove(event.getPlayer().getName());
    }
}
