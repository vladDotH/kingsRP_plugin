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

public class LogBreak implements Listener {
    private static final String succes = ChatColor.GREEN + "Удачно срублено",
            fail = ChatColor.RED + "Неудача";

    private static final Material[] failDrop = {Material.STICK};

    @EventHandler
    public void logBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode().equals(GameMode.CREATIVE))
            return;

        if (event.getBlock().hasMetadata(Values.NOT_NATURAL))
            return;

        if (BlockTypes.isWood(event.getBlock().getType())) {
            UserData userData = Plugin.getInstance().getUsers()
                    .get(player.getName());

            double lumbering = userData.getSkills().getSkill(Skills.LUMBERING);
            double bonus = 0;
            if (userData.getJobData().getJob() == Jobs.LUMBER)
                bonus = Values.JOB_BONUS.LUMBER;

            double brokeChance = Values.BASE_CHANCE.LUMBER + bonus + Values.CHANCE_PER_LVL.LUMBER * lumbering;

            double exp;
            if (RandomGenerator.roll(brokeChance)) {
                ActionBarAPI.sendActionBar(player, succes, 20);
                exp = Config.getDouble(event.getBlock().getType().toString());
            } else {
                ActionBarAPI.sendActionBar(player, fail, 20);
                event.setDropItems(false);
                FailDrop.failRandomDrop(event.getPlayer().getWorld(), event.getBlock().getLocation(), failDrop);
                exp = Values.MISS_EXP.LUMBER;
            }

            userData.getSkills().updAndInfo(player, Skills.LUMBERING, exp);
        }
    }
}
