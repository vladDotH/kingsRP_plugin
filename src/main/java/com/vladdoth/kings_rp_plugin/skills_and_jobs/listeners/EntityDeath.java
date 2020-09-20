package com.vladdoth.kings_rp_plugin.skills_and_jobs.listeners;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.configs.Config;
import com.vladdoth.kings_rp_plugin.configs.Values;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Jobs;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.util.FailDrop;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.util.RandomGenerator;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDeath implements Listener {
    private static final String success = ChatColor.GREEN + "Добыча собрана",
            fail = ChatColor.RED + "Неудачная добыча";

    private static final Material[] failDrop = {Material.BONE, Material.ROTTEN_FLESH, Material.LEATHER};

    @EventHandler
    public void entityDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() == null)
            return;

        Player player = event.getEntity().getKiller();

        if (player.getGameMode() == GameMode.CREATIVE)
            return;

        double exp = Config.getDouble(event.getEntity().getType().toString());
        UserData userData = Plugin.getInstance().getUsers().get(event.getEntity().getKiller().getName());

        if (event.getEntity().getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
            userData.getSkills().updAndInfo(player, Skills.ATTACK, exp);
        } else if (event.getEntity().getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
            userData.getSkills().updAndInfo(player, Skills.SHOOTING, exp);
        }

        if (event.getEntity() instanceof Animals) {
            double breeding = userData.getSkills().getSkill(Skills.BREEDING);

            double bonus = 0;
            if (userData.getJobData().getJob() == Jobs.BREEDER)
                bonus += Values.JOB_BONUS.BREEDER;
            if (userData.getJobData().getJob() == Jobs.HUNTER)
                bonus += Values.JOB_BONUS.HUNTER_DROP;

            double dropChance = Values.BASE.ANIMAL_DROP + Values.PER_LVL.ANIMAL_DROP * breeding + bonus;

            if (RandomGenerator.roll(dropChance)) {
                ActionBarAPI.sendActionBar(player, success, 20);
            } else {
                ActionBarAPI.sendActionBar(player, fail, 20);
                for (ItemStack i : event.getDrops()) {
                    i.setAmount(0);
                }
                FailDrop.randDropMaterial(event.getEntity().getWorld(), event.getEntity().getLocation(), failDrop);
                exp = Values.MISS_EXP.ANIMAL_DROP;
            }

            userData.getSkills().updAndInfo(player, Skills.BREEDING, exp);
        }

    }
}
