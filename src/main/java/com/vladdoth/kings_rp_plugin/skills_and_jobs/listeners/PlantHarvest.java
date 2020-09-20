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
import org.bukkit.inventory.ItemStack;

public class PlantHarvest implements Listener {
    private static final String succes = ChatColor.GREEN + "Удачно собрано",
            fail = ChatColor.RED + "Неудача";

    @EventHandler
    public void plantHarvest(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode() == GameMode.CREATIVE)
            return;

        if (event.getBlock().hasMetadata(Values.NOT_NATURAL))
            return;

        if (BlockTypes.isHarvestable(event.getBlock().getType()) && BlockTypes.isRipe(event.getBlock())) {
            UserData userData = Plugin.getInstance().getUsers().get(event.getPlayer().getName());

            double farming = userData.getSkills().getSkill(Skills.FARMING);
            double bonus = 0;
            if (userData.getJobData().getJob() == Jobs.FARMER)
                bonus = Values.JOB_BONUS.FARMER;

            double dropChance = Values.BASE.HARVEST + bonus + Values.PER_LVL.HARVEST * farming;

            double exp;
            if (RandomGenerator.roll(dropChance)) {
                ActionBarAPI.sendActionBar(player, succes, 20);
                exp = Config.getDouble(event.getBlock().getType().toString());
            } else {
                ActionBarAPI.sendActionBar(player, fail, 20);
                event.setDropItems(false);
                FailDrop.dropItemStack(event.getPlayer().getWorld(), event.getBlock().getLocation(), getFailDrop(event.getBlock()));
                exp = Values.MISS_EXP.HARVEST;
            }

            userData.getSkills().updAndInfo(player, Skills.FARMING, exp);
        }
    }

    private ItemStack[] getFailDrop(Block block) {
        switch (block.getType()) {
            case CROPS:
                return new ItemStack[]{new ItemStack(Material.SEEDS)};
            case CARROT:
                return new ItemStack[]{new ItemStack(Material.CARROT_ITEM)};
            case POTATO:
                return new ItemStack[]{new ItemStack(Material.POISONOUS_POTATO)};
            case COCOA:
                ItemStack cocoa = new ItemStack(Material.INK_SACK, 1, (short) 0, (byte) 3);
                return new ItemStack[]{cocoa};
            case BEETROOT_BLOCK:
                return new ItemStack[]{new ItemStack(Material.BEETROOT_SEEDS)};
            case PUMPKIN:
                return new ItemStack[]{new ItemStack(Material.PUMPKIN_SEEDS)};
            case MELON_BLOCK:
                return new ItemStack[]{new ItemStack(Material.MELON)};
            case SUGAR_CANE_BLOCK:
                return new ItemStack[]{new ItemStack(Material.SUGAR)};
            default:
                return null;
        }
    }
}
