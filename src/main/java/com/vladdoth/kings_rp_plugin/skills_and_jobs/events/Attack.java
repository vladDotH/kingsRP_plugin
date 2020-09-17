package com.vladdoth.kings_rp_plugin.skills_and_jobs.events;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.configs.Values;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Jobs;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.util.RandomGenerator;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class Attack implements Listener {
    private static final String succes = ChatColor.GREEN + "Попадание",
            fail = ChatColor.RED + "Промах";

    @EventHandler
    public void attack(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player))
            return;

        Player player = (Player) event.getDamager();

        if (player.getGameMode() == GameMode.CREATIVE)
            return;

        if (event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
            UserData userData = Plugin.getInstance().getUsers()
                    .get(event.getDamager().getName());

            double attack = userData.getSkills().getSkill(Skills.ATTACK);
            double bonus = 0;
            if (userData.getJobData().getJob() == Jobs.HUNTER)
                bonus = Values.JOB_BONUS.HUNTER_ATTACK;

            double hitChance = Values.BASE_CHANCE.ATTACK + bonus + Values.CHANCE_PER_LVL.ATTACK * attack;

            double exp = 0;
            if (!RandomGenerator.roll(hitChance)) {
                ActionBarAPI.sendActionBar(player, fail, 20);
                event.setCancelled(true);
                exp = Values.MISS_EXP.ATTACK;
            } else {
                ActionBarAPI.sendActionBar(player, succes, 20);
                exp = Values.ATTACK_EXP;
            }

            userData.getSkills().updAndInfo(player, Skills.ATTACK, exp);
        }
    }
}
