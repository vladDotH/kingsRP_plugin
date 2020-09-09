package com.vladdoth.kings_rp_plugin.jobs.events;

import com.vladdoth.kings_rp_plugin.ConfigFields;
import com.vladdoth.kings_rp_plugin.Plugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

public class BlockPlaced implements Listener {
    @EventHandler
    public void blockPlaced(BlockPlaceEvent event) {
        event.getBlock().setMetadata(
                ConfigFields.NOT_NATURAL, new FixedMetadataValue(Plugin.getInstance(), true)
        );
    }
}
