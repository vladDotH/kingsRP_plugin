package com.vladdoth.kings_rp_plugin.skills_and_jobs.events;

import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.configs.Config;
import com.vladdoth.kings_rp_plugin.configs.Values;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Jobs;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.util.BlockTypes;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.util.RandomGenerator;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class LogBreak implements Listener {
    @EventHandler
    public void logBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode().equals(GameMode.CREATIVE))
            return;

        if (BlockTypes.isWood(event.getBlock().getType())) {
            UserData user = Plugin.getInstance().getUsers()
                    .get(player.getName());

            double lumbering = user.getSkills().getSkill(Skills.LUMBERING);
            double bonus = 0;
            if (user.getJobData().getJob() == Jobs.LUMBER)
                bonus = Values.JOB_BONUS.LUMBER;

            double brokeChance = Values.BASE_CHANCE.LUMBER + bonus + Values.CHANCE_PER_LVL.LUMBER * lumbering;

//        Plugin.getInstance().getLogger().info("chances: " +
//                "summary=" + brokeChance + " base=" + Base.lumber + " bonus=" + bonus + " skill=" + PerLvl.lumber * lumbering);

            double skillOld = user.getSkills().getSkill(Skills.LUMBERING);

            if (!RandomGenerator.roll(brokeChance)) {
                event.setDropItems(false);
                user.getSkills().updSkill(Skills.LUMBERING, Values.MISS_EXP.LUMBER);
            } else {
                double exp = Config.getDouble(event.getBlock().getType().toString());
                user.getSkills().updSkill(Skills.LUMBERING, exp);
            }

            double skillNew = user.getSkills().getSkill(Skills.LUMBERING);

            if (LevelUp.checkLvl(skillOld, skillNew))
                LevelUp.lvlUpInfo(event.getPlayer(), skillNew, Skills.LUMBERING);
        }
    }
}
