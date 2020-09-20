package com.vladdoth.kings_rp_plugin.skills_and_jobs.listeners;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.configs.Values;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

public class EntityBreed implements Listener {
    public static final String success = ChatColor.GREEN + "Успешно размножено";
    @EventHandler
    public void entityBreed(EntityBreedEvent event) {
        if (!(event.getBreeder() instanceof Player))
            return;

        Player player = (Player) event.getBreeder();
        UserData userData = Plugin.getInstance().getUsers().get(player.getName());
        double exp = Values.BREED_EXP;
        userData.getSkills().updAndInfo(player, Skills.BREEDING, exp);

        ActionBarAPI.sendActionBar(player, success, 20);
    }
}
