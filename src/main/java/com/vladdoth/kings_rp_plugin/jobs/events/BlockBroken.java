package com.vladdoth.kings_rp_plugin.jobs.events;

import com.vladdoth.kings_rp_plugin.configs.Fields;
import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.jobs.BlockTypes;
import com.vladdoth.kings_rp_plugin.jobs.JobsManager;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBroken implements Listener {
    @EventHandler
    public void blockBroken(BlockBreakEvent event) {
        Block block = event.getBlock();

        if (block.hasMetadata(Plugin.getInstance().getConfig().getString(Fields.NOT_NATURAL))
                || event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            return;
        }

        if (BlockTypes.isPlantBlock(block.getType())) {
            JobsManager.harvested(event);
        }

        if (BlockTypes.isRockBlock(block.getType())) {
            JobsManager.brokeRock(event);
        }

        if (BlockTypes.isWoodBlock(block.getType())) {
            JobsManager.brokeLog(event);
        }
    }
}
