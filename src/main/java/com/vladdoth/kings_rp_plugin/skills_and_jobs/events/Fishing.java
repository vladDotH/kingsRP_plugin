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
import org.bukkit.event.player.PlayerFishEvent;

public class Fishing implements Listener {
    private static final String succes = ChatColor.GREEN + "Удачно поймано",
            fail = ChatColor.RED + "Сорвалась";

    @EventHandler
    public void fishing(PlayerFishEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode().equals(GameMode.CREATIVE))
            return;

        if (event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH))
        {
            UserData user = Plugin.getInstance().getUsers().get(player.getName());
            double fishing = user.getSkills().getSkill(Skills.FISHING);

            double bonus = 0;
            if (user.getJobData().getJob() == Jobs.FISHER)
                bonus = Values.JOB_BONUS.FISHER;

            double fishChance = Values.BASE_CHANCE.FISH + bonus + Values.CHANCE_PER_LVL.FISH * fishing;

            double exp;
            if (RandomGenerator.roll(fishChance)) {
                ActionBarAPI.sendActionBar(player, succes, 20);
                exp = Values.FISH_EXP;
            } else {
                ActionBarAPI.sendActionBar(player, fail, 20);
                event.setCancelled(true);
                exp = Values.MISS_EXP.FISH;
            }

            user.getSkills().updAndInfo(player, Skills.FISHING, exp);
        }
    }
}
