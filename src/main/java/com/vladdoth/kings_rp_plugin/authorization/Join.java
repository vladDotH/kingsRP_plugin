package com.vladdoth.kings_rp_plugin.authorization;

import com.vladdoth.kings_rp_plugin.Plugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@Deprecated
public class Join implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerJoin(PlayerJoinEvent event) {
        Plugin.getInstance().getDb().loadPlayer(event.getPlayer());
    }
}
