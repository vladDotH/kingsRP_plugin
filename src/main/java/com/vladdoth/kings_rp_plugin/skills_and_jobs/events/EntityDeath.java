package com.vladdoth.kings_rp_plugin.skills_and_jobs.events;

import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.configs.Config;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeath implements Listener {
    @EventHandler
    public void entityDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() == null)
            return;

        Player player = event.getEntity().getKiller();

        double exp = Config.getDouble(event.getEntity().getType().toString());
        UserData userData = Plugin.getInstance().getUsers().get(event.getEntity().getKiller().getName());

        if (event.getEntity().getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
            userData.getSkills().updAndInfo(player, Skills.ATTACK, exp);
        } else if (event.getEntity().getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
            userData.getSkills().updAndInfo(player, Skills.SHOOTING, exp);
        }

//        System.out.println(event.getDrops());
//        for (ItemStack i : event.getDrops()) {
//            i.setAmount(0);
//        }
    }
}
