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
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerShearEntityEvent;

public class SheepShear implements Listener {
    private static final String succes = ChatColor.GREEN + "Удачно пострижено",
            fail = ChatColor.RED + "Неудача";

    @EventHandler
    public void sheepShear(PlayerShearEntityEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode().equals(GameMode.CREATIVE))
            return;

        if (!(event.getEntity() instanceof Sheep))
            return;

        Sheep sheep = (Sheep) event.getEntity();

        UserData user = Plugin.getInstance().getUsers().get(player.getName());
        double breeding = user.getSkills().getSkill(Skills.BREEDING);

        double bonus = 0;
        if (user.getJobData().getJob() == Jobs.BREEDER)
            bonus = Values.JOB_BONUS.BREEDER;

        double shearChance = Values.BASE_CHANCE.SHEAR + bonus + Values.CHANCE_PER_LVL.SHEAR * breeding;

        double exp;
        if (RandomGenerator.roll(shearChance)) {
            ActionBarAPI.sendActionBar(player, succes, 20);
            exp = Values.SHEAR_EXP;
        } else {
            ActionBarAPI.sendActionBar(player, fail, 20);
            event.setCancelled(true);
            sheep.setSheared(true);
            exp = Values.MISS_EXP.SHEAR;
        }

        user.getSkills().updAndInfo(player, Skills.BREEDING, exp);
    }

}
