package com.vladdoth.kings_rp_plugin.skills_and_jobs.events;

import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.configs.Values;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Jobs;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.util.RandomGenerator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.util.Vector;

public class BowShoot implements Listener {
    @EventHandler
    public void bowShoot(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getEntity();
        UserData userData = Plugin.getInstance().getUsers().get(player.getName());

        double disp =
                Values.MAX_DISP - ((Values.MAX_DISP - Values.MIN_DISP) / Values.MAX_LVL) * userData.getSkills().getSkill(Skills.SHOOTING);

        if (userData.getJobData().getJob() == Jobs.HUNTER)
            disp /= Values.JOB_BONUS.HUNTER_SHOOT;

        Vector dispVec = RandomGenerator.dispersion(disp);

        event.getProjectile().setVelocity(event.getProjectile().getVelocity().add(dispVec));

        userData.getSkills().updSkill(Skills.SHOOTING, Values.SHOOT_MISS_EXP);
    }
}
