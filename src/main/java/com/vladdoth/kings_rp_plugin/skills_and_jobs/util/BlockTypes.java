package com.vladdoth.kings_rp_plugin.skills_and_jobs.util;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.material.CocoaPlant;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;

import java.util.Arrays;

public class BlockTypes {
    public static final Material[]
            ORES = {Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.GLOWING_REDSTONE_ORE,
            Material.LAPIS_ORE, Material.GOLD_ORE, Material.EMERALD_ORE, Material.DIAMOND_ORE, Material.QUARTZ_ORE},

    NON_BLOCK_PLANTS = {Material.CROPS, Material.CARROT, Material.POTATO, Material.COCOA, Material.BEETROOT_BLOCK},
            PLANTS = {Material.CROPS, Material.CARROT, Material.POTATO, Material.COCOA, Material.BEETROOT_BLOCK,
                    Material.PUMPKIN, Material.MELON_BLOCK, Material.SUGAR_CANE_BLOCK},
            WOODS = {Material.LOG, Material.LOG_2};

    public static boolean isOre(Material block) {
        return Arrays.asList(ORES).contains(block);
    }

    /*
    Растения, которые не дают новых блоков (как арбуз и тыква)
     */
    public static boolean isPlant(Material block) {
        return Arrays.asList(NON_BLOCK_PLANTS).contains(block);
    }

    /*
    Все растения
     */
    public static boolean isHarvestable(Material block) {
        return Arrays.asList(PLANTS).contains(block);
    }

    public static boolean isRipe(Block block) {
        if (block.getType() == Material.MELON_BLOCK
                || block.getType() == Material.PUMPKIN
                || block.getType() == Material.SUGAR_CANE_BLOCK)
            return true;

        MaterialData md = block.getState().getData();

        if (md instanceof Crops)
            return (((Crops) md).getState() == CropState.RIPE);

        if (md instanceof CocoaPlant)
            return ((CocoaPlant) md).getSize() == CocoaPlant.CocoaPlantSize.LARGE;

        return false;
    }

    public static boolean isWood(Material block) {
        return Arrays.asList(WOODS).contains(block);
    }
}
