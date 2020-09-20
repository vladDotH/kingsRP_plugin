package com.vladdoth.kings_rp_plugin.skills_and_jobs.listeners;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.configs.Config;
import com.vladdoth.kings_rp_plugin.configs.Values;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Jobs;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.util.BlockTypes;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.util.FailDrop;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.util.RandomGenerator;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OreBreak implements Listener {
    private static final String success = ChatColor.GREEN + "Удачно добыто",
            fail = ChatColor.RED + "Неудача";

    @EventHandler
    public void oreBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode().equals(GameMode.CREATIVE))
            return;

        if (event.getBlock().hasMetadata(Values.NOT_NATURAL))
            return;

        if (BlockTypes.isOre(event.getBlock().getType())) {
            UserData user = Plugin.getInstance().getUsers().get(player.getName());

            double mining = user.getSkills().getSkill(Skills.MINING);

            double bonus = 0;
            if (user.getJobData().getJob() == Jobs.MINER)
                bonus = Values.JOB_BONUS.MINER;

            double brokeChance = Values.BASE.MINE + bonus + Values.PER_LVL.MINE * mining;

            double exp;
            if (RandomGenerator.roll(brokeChance)) {
                ActionBarAPI.sendActionBar(player, success, 20);
                exp = Config.getDouble(event.getBlock().getType().toString());
            } else {
                ActionBarAPI.sendActionBar(player, fail, 20);
                event.setDropItems(false);
                FailDrop.dropMaterial(event.getPlayer().getWorld(), event.getBlock().getLocation(), getFailDrop(event.getBlock()));
                exp = Values.MISS_EXP.MINE;
            }

            user.getSkills().updAndInfo(player, Skills.MINING, exp);
        }
    }

    private Material[] getFailDrop(Block block) {
        switch (block.getType()){
            case IRON_ORE:
                return new Material[]{Material.IRON_NUGGET, Material.COBBLESTONE};
            case GOLD_ORE:
                return new Material[]{Material.GOLD_NUGGET, Material.COBBLESTONE};
            default:
                return new Material[] {Material.COBBLESTONE};
        }
    }
}
