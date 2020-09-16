package com.vladdoth.kings_rp_plugin.skills_and_jobs.events;

import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.configs.Config;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeath implements Listener {
    @EventHandler
    public void entityDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() == null)
            return;

        double exp = Config.getDouble(event.getEntity().getType().toString());
        UserData user = Plugin.getInstance().getUsers()
                .get(event.getEntity().getKiller().getName());
        double hunting = user.getSkills().getSkill(Skills.ATTACK);
        user.getSkills().updSkill(Skills.ATTACK, exp);
    }
}
