package com.vladdoth.kings_rp_plugin.jobs.events;

import com.vladdoth.kings_rp_plugin.jobs.JobsManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamaged implements Listener {
    @EventHandler
    public void entityDamaged(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            if (((Player) event.getDamager()).getGameMode() == GameMode.CREATIVE)
                return;

            JobsManager.attack(event);
        }
    }
}
