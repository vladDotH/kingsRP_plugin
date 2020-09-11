package com.vladdoth.kings_rp_plugin.skills.util;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.material.CocoaPlant;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;

public class BlockTypes {
    public static boolean isRockBlock(Material block) {
        switch (block) {
            case COAL_ORE:
            case IRON_ORE:
            case REDSTONE_ORE:
            case LAPIS_ORE:
            case GOLD_ORE:
            case EMERALD_ORE:
            case DIAMOND_ORE:
                return true;
            default:
                return false;
        }
    }

    /*
    Растения, которые не дают новых блоков (как арбуз и тыква)
     */
    public static boolean isPlantBlock(Material block) {
        switch (block) {
            case CROPS:
            case CARROT:
            case POTATO:
            case WHEAT:
            case COCOA:
            case BEETROOT_BLOCK:
                return true;
            default:
                return false;
        }
    }

    public static boolean isHarvestable(Material block) {
        switch (block) {
            case CROPS:
            case CARROT:
            case POTATO:
            case WHEAT:
            case COCOA:
            case BEETROOT_BLOCK:
            case PUMPKIN:
            case MELON_BLOCK:
                return true;
            default:
                return false;
        }
    }

    public static boolean isRipe(Block block) {
        if (block.getType() == Material.MELON_BLOCK
                || block.getType() == Material.PUMPKIN)
            return true;

        MaterialData md = block.getState().getData();

        if (md instanceof Crops)
            return (((Crops) md).getState() == CropState.RIPE);

        if (md instanceof CocoaPlant)
            return ((CocoaPlant) md).getSize() == CocoaPlant.CocoaPlantSize.LARGE;

        return false;
    }

    public static boolean isWoodBlock(Material block) {
        return block == Material.LOG;
    }
}
