package com.vladdoth.kings_rp_plugin.skills_and_jobs.events;

import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.configs.Values;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Jobs;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.util.RandomGenerator;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Attack implements Listener {
    @EventHandler
    public void attack(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player))
            return;

        Player p = (Player) event.getDamager();

        if (p.getGameMode() == GameMode.CREATIVE)
            return;

        UserData user = Plugin.getInstance().getUsers()
                .get(event.getDamager().getName());

        double attack = user.getSkills().getSkill(Skills.ATTACK);
        double bonus = 0;
        if (user.getJobData().getJob() == Jobs.HUNTER)
            bonus = Values.JOB_BONUS.HUNTER_ATTACK;

        double hitChance = Values.BASE_CHANCE.ATTACK + bonus + Values.CHANCE_PER_LVL.ATTACK * attack;

//        Plugin.getInstance().getLogger().info("chances: " +
//                "summary=" + hitChance + " base=" + Values.BASE_CHANCE.ATTACK + " bonus=" + bonus + " skill=" + Values.CHANCE_PER_LVL.ATTACK);

        if (!RandomGenerator.roll(hitChance)) {
            event.setCancelled(true);
            user.getSkills().updSkill(Skills.ATTACK, Values.MISS_EXP.ATTACK);

        } else {
            user.getSkills().updSkill(Skills.ATTACK, Values.ATTACK_EXP);
        }
    }
}
