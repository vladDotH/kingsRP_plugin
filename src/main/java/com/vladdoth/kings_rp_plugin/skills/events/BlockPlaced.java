package com.vladdoth.kings_rp_plugin.skills.events;

import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.configs.Values;
import com.vladdoth.kings_rp_plugin.skills.util.BlockTypes;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class BlockPlaced implements Listener {
    @EventHandler
    public void blockPlaced(BlockPlaceEvent event) {
        if (event.getPlayer() != null
                && event.getPlayer().getGameMode() != GameMode.CREATIVE
                && !BlockTypes.isPlantBlock(event.getBlock().getType()))
            event.getBlock().setMetadata(Values.NOT_NATURAL, new FixedMetadataValue(Plugin.getInstance(), true));
    }
}
