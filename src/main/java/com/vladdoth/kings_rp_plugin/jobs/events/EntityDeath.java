package com.vladdoth.kings_rp_plugin.jobs.events;

import com.vladdoth.kings_rp_plugin.jobs.JobsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeath implements Listener {
    @EventHandler
    public void entityDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null)
            JobsManager.killedEntity(event);
    }
}
