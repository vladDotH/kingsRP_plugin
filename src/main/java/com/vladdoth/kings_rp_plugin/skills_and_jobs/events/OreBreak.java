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

public class OreBreak implements Listener {
    @EventHandler
    public void oreBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode().equals(GameMode.CREATIVE))
            return;

        if (BlockTypes.isOre(event.getBlock().getType())) {
            UserData user = Plugin.getInstance().getUsers().get(player.getName());

            double mining = user.getSkills().getSkill(Skills.MINING);

            double bonus = 0;
            if (user.getJobData().getJob() == Jobs.MINER)
                bonus = Values.JOB_BONUS.MINER;

            double brokeChance = Values.BASE_CHANCE.MINE + bonus + Values.CHANCE_PER_LVL.MINE * mining;

//        Plugin.getInstance().getLogger().info("chances: " +
//                "summary=" + brokeChance + " base=" + Base.mine + " bonus=" + bonus + " skill=" + PerLvl.mine * mining);

            double skillOld = user.getSkills().getSkill(Skills.MINING);

            if (!RandomGenerator.roll(brokeChance)) {
                event.setDropItems(false);
                user.getSkills().updSkill(Skills.MINING, Values.MISS_EXP.MINE);
            } else {
                double exp = Config.getDouble(event.getBlock().getType().toString());
                user.getSkills().updSkill(Skills.MINING, exp);
            }

            double skillNew = user.getSkills().getSkill(Skills.MINING);

            if (LevelUp.checkLvl(skillOld, skillNew))
                LevelUp.lvlUpInfo(event.getPlayer(), skillNew, Skills.MINING);
        }
    }
}
