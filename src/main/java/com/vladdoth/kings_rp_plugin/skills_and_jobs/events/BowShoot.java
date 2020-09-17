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
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

public class BowShoot implements Listener {
    private static final String succes = ChatColor.GREEN + "Попадание";

    @EventHandler
    public void bowShoot(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getEntity();

        if (player.getGameMode() == GameMode.CREATIVE)
            return;

        UserData userData = Plugin.getInstance().getUsers().get(player.getName());

        double shooting = userData.getSkills().getSkill(Skills.SHOOTING);
        double disp = Values.MAX_DISP - ((Values.MAX_DISP - Values.MIN_DISP) / Values.MAX_LVL) * shooting;

        if (userData.getJobData().getJob() == Jobs.HUNTER)
            disp /= Values.JOB_BONUS.HUNTER_SHOOT;

        Vector dispVec = RandomGenerator.dispersion(disp);
        event.getProjectile().setVelocity(event.getProjectile().getVelocity().add(dispVec));

        userData.getSkills().updAndInfo(player, Skills.SHOOTING, Values.SHOOT_MISS_EXP);
    }

    @EventHandler
    public void attack(ProjectileHitEvent event) {
        if (!(event.getEntity().getShooter() instanceof Player) || event.getHitEntity() == null)
            return;

        Player player = (Player) event.getEntity().getShooter();

        if (player.getGameMode() == GameMode.CREATIVE)
            return;

        UserData userData = Plugin.getInstance().getUsers().get(player.getName());

        ActionBarAPI.sendActionBar(player, succes, 20);
        userData.getSkills().updAndInfo(player, Skills.SHOOTING, Values.SHOOT_HIT_EXP);
    }
}
