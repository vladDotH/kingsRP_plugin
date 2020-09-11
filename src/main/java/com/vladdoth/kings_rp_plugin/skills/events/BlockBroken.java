package com.vladdoth.kings_rp_plugin.skills.events;

import com.vladdoth.kings_rp_plugin.configs.Fields;
import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.skills.util.BlockTypes;
import com.vladdoth.kings_rp_plugin.skills.SkillsFunctions;
import org.bukkit.CropState;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.material.CocoaPlant;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;

public class BlockBroken implements Listener {
    @EventHandler
    public void blockBroken(BlockBreakEvent event) {
        Block block = event.getBlock();

        if (block.hasMetadata(Plugin.getInstance().getConfig().getString(Fields.NOT_NATURAL))
                || event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            return;
        }

        if (BlockTypes.isHarvestable(block.getType())
                && BlockTypes.isRipe(block)) {
            SkillsFunctions.harvested(event);
        }

        if (BlockTypes.isRockBlock(block.getType())) {
            SkillsFunctions.brokeRock(event);
        }

        if (BlockTypes.isWoodBlock(block.getType())) {
            SkillsFunctions.brokeLog(event);
        }
    }
}
