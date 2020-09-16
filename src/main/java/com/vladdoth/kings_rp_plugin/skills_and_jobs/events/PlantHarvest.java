package com.vladdoth.kings_rp_plugin.skills_and_jobs.events;

import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.configs.Config;
import com.vladdoth.kings_rp_plugin.configs.Values;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Jobs;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.util.BlockTypes;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.util.RandomGenerator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PlantHarvest implements Listener {
    @EventHandler
    public void plantHarvest(BlockBreakEvent event) {
        if (BlockTypes.isHarvestable(event.getBlock().getType()) && BlockTypes.isRipe(event.getBlock())) {
            UserData user = Plugin.getInstance().getUsers()
                    .get(event.getPlayer().getName());

            double farming = user.getSkills().getSkill(Skills.FARMING);
            double bonus = 0;
            if (user.getJobData().getJob() == Jobs.FARMER)
                bonus = Values.JOB_BONUS.FARMER;

            double bonusDrop = Values.BASE_CHANCE.HARVEST + bonus + Values.CHANCE_PER_LVL.HARVEST * farming;

//        Plugin.getInstance().getLogger().info("chances: " +
//                "summary=" + bonusDrop + " base=" + Base.harvest + " bonus=" + bonus + " skill=" + PerLvl.harvest * farming);

            double skillOld = user.getSkills().getSkill(Skills.FARMING);

            if (!RandomGenerator.roll(bonusDrop)) {
                event.setDropItems(false);
                user.getSkills().updSkill(Skills.FARMING, Values.MISS_EXP.HARVEST);
            } else {
                double exp = Config.getDouble(event.getBlock().getType().toString());
                user.getSkills().updSkill(Skills.FARMING, exp);
            }

            double skillNew = user.getSkills().getSkill(Skills.FARMING);

            if (LevelUp.checkLvl(skillOld, skillNew))
                LevelUp.lvlUpInfo(event.getPlayer(), skillNew, Skills.FARMING);
        }
    }
}
