package com.vladdoth.kings_rp_plugin.skills_and_jobs.events;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.configs.Config;
import com.vladdoth.kings_rp_plugin.configs.Values;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Jobs;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.util.BlockTypes;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.util.RandomGenerator;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PlantHarvest implements Listener {
    private static final String succes = ChatColor.GREEN + "Удачно собрано",
            fail = ChatColor.RED + "Неудача";

    @EventHandler
    public void plantHarvest(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode().equals(GameMode.CREATIVE))
            return;

        if (event.getBlock().hasMetadata(Values.NOT_NATURAL))
            return;

        if (BlockTypes.isHarvestable(event.getBlock().getType()) && BlockTypes.isRipe(event.getBlock())) {
            UserData userData = Plugin.getInstance().getUsers().get(event.getPlayer().getName());

            double farming = userData.getSkills().getSkill(Skills.FARMING);
            double bonus = 0;
            if (userData.getJobData().getJob() == Jobs.FARMER)
                bonus = Values.JOB_BONUS.FARMER;

            double dropChance = Values.BASE_CHANCE.HARVEST + bonus + Values.CHANCE_PER_LVL.HARVEST * farming;

            double exp = 0;
            if (!RandomGenerator.roll(dropChance)) {
                ActionBarAPI.sendActionBar(player, fail, 20);
                event.setDropItems(false);
                exp = Values.MISS_EXP.HARVEST;
            } else {
                ActionBarAPI.sendActionBar(player, succes, 20);
                exp = Config.getDouble(event.getBlock().getType().toString());
            }

            userData.getSkills().updAndInfo(player, Skills.FARMING, exp);
        }
    }
}
