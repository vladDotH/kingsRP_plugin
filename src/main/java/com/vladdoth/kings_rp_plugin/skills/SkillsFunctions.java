package com.vladdoth.kings_rp_plugin.skills;

import com.vladdoth.kings_rp_plugin.configs.Config;
import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.configs.Values;
import com.vladdoth.kings_rp_plugin.skills.util.Chance;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class SkillsFunctions {

    static class Chances {
        public final double hit, mine, lumber, harvest;

        Chances(double mine, double harvest, double lumber, double hit) {
            this.mine = mine;
            this.harvest = harvest;
            this.lumber = lumber;
            this.hit = hit;
        }
    }

    static Chances Base = new Chances(
            Values.BASE_CHANCE.MINE,
            Values.BASE_CHANCE.HARVEST,
            Values.BASE_CHANCE.LUMBER,
            Values.BASE_CHANCE.ATTACK);

    static Chances Bonus = new Chances(
            Values.JOB_BONUS.MINE,
            Values.JOB_BONUS.HARVEST,
            Values.JOB_BONUS.LUMBER,
            Values.JOB_BONUS.ATTACK
    );

    static Chances PerLvl = new Chances(
            Values.CHANCE_PER_LVL.MINE,
            Values.CHANCE_PER_LVL.HARVEST,
            Values.CHANCE_PER_LVL.LUMBER,
            Values.CHANCE_PER_LVL.ATTACK
    );

    public static double reduceExp(double exp, double skill) {
        return exp * ((Values.MAX_LVL - skill) / Values.MAX_LVL);
    }

    public static void killedEntity(EntityDeathEvent event) {
        double exp = Config.getDouble(event.getEntity().getType().toString());
        UserData user = Plugin.getInstance().getUsers()
                .get(event.getEntity().getKiller().getName());
        double hunting = user.getSkills().getHunting();
        user.getSkills().updHunting(reduceExp(exp, hunting));
    }

    public static void attack(EntityDamageByEntityEvent event) {
        UserData user = Plugin.getInstance().getUsers()
                .get(event.getDamager().getName());

        double hunting = user.getSkills().getHunting();
        double bonus = 0;
        if (user.getSkills().getJob() == Jobs.HUNTER)
            bonus = Bonus.hit;

        double hitChance = Base.hit + bonus + PerLvl.hit * hunting;

        user.getSkills().updHunting(reduceExp(Values.MISS_EXP.ATTACK, hunting));

//        Plugin.getInstance().getLogger().info("chances: " +
//                "summary=" + hitChance + " base=" + Base.hit + " bonus=" + bonus + " skill=" + PerLvl.hit * hunting);

        if (!Chance.roll(hitChance))
            event.setCancelled(true);
    }

    public static void brokeRock(BlockBreakEvent event) {
        UserData user = Plugin.getInstance().getUsers()
                .get(event.getPlayer().getName());

        double mining = user.getSkills().getMining();

        double bonus = 0;
        if (user.getSkills().getJob() == Jobs.MINER)
            bonus = Bonus.mine;

        double brokeChance = Base.mine + bonus + PerLvl.mine * mining;

//        Plugin.getInstance().getLogger().info("chances: " +
//                "summary=" + brokeChance + " base=" + Base.mine + " bonus=" + bonus + " skill=" + PerLvl.mine * mining);

        if (!Chance.roll(brokeChance)) {
            event.setCancelled(true);
            user.getSkills().updMining(reduceExp(Values.MISS_EXP.MINE, mining));
        } else {
            double exp = Config.getDouble(event.getBlock().getType().toString());
            user.getSkills().updMining(reduceExp(exp, mining));
        }
    }

    public static void harvested(BlockBreakEvent event) {
        UserData user = Plugin.getInstance().getUsers()
                .get(event.getPlayer().getName());

        double farming = user.getSkills().getFarming();
        double bonus = 0;
        if (user.getSkills().getJob() == Jobs.FARMER)
            bonus = Bonus.harvest;

        double bonusDrop = Base.harvest + bonus + PerLvl.harvest * farming;

//        Plugin.getInstance().getLogger().info("chances: " +
//                "summary=" + bonusDrop + " base=" + Base.harvest + " bonus=" + bonus + " skill=" + PerLvl.harvest * farming);

        if (!Chance.roll(bonusDrop)) {
            event.setCancelled(true);
            user.getSkills().updFarming(reduceExp(Values.MISS_EXP.HARVEST, farming));
        } else {
            double exp = Config.getDouble(event.getBlock().getType().toString());
            user.getSkills().updFarming(reduceExp(exp, farming));

            event.getPlayer().getWorld().dropItem(event.getBlock().getLocation(),
                    new ItemStack(event.getBlock().getType()));

            if (event.getBlock().getType() == Material.WHEAT)
                event.getPlayer().getWorld().dropItem(event.getBlock().getLocation(),
                        new ItemStack(Material.SEEDS));
        }
    }

    public static void brokeLog(BlockBreakEvent event) {
        UserData user = Plugin.getInstance().getUsers()
                .get(event.getPlayer().getName());

        double lumbering = user.getSkills().getLumbering();
        double bonus = 0;
        if (user.getSkills().getJob() == Jobs.LUMBER)
            bonus = Bonus.lumber;

        double brokeChance = Base.lumber + bonus + PerLvl.lumber * lumbering;

//        Plugin.getInstance().getLogger().info("chances: " +
//                "summary=" + brokeChance + " base=" + Base.lumber + " bonus=" + bonus + " skill=" + PerLvl.lumber * lumbering);

        if (!Chance.roll(brokeChance)) {
            event.setCancelled(true);
            user.getSkills().updLumbering(reduceExp(Values.MISS_EXP.LUMBER, lumbering));
        }
        else {
            double exp = Config.getDouble(event.getBlock().getType().toString());
            user.getSkills().updLumbering(reduceExp(exp, lumbering));
        }
    }
}
