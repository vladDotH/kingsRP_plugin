package com.vladdoth.kings_rp_plugin.jobs;

import com.vladdoth.kings_rp_plugin.ConfigFields;
import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class JobsManager {

    static class Chances {
        public final double hit, mine, lumber, harvest;

        Chances(double hit, double mine, double lumber, double harvest) {
            this.hit = hit;
            this.mine = mine;
            this.lumber = lumber;
            this.harvest = harvest;
        }
    }

    static Chances Base = new Chances(
            Plugin.getInstance().getConfig().getDouble(ConfigFields.BaseChances.ATTACK),
            Plugin.getInstance().getConfig().getDouble(ConfigFields.BaseChances.MINE),
            Plugin.getInstance().getConfig().getDouble(ConfigFields.BaseChances.LUMBER),
            Plugin.getInstance().getConfig().getDouble(ConfigFields.BaseChances.HARVEST));

    static Chances Bonus = new Chances(
            Plugin.getInstance().getConfig().getDouble(ConfigFields.JobBonus.ATTACK),
            Plugin.getInstance().getConfig().getDouble(ConfigFields.JobBonus.MINE),
            Plugin.getInstance().getConfig().getDouble(ConfigFields.JobBonus.LUMBER),
            Plugin.getInstance().getConfig().getDouble(ConfigFields.JobBonus.HARVEST)
    );

    static Chances PerLvl = new Chances(
            Plugin.getInstance().getConfig().getDouble(ConfigFields.ChancesPerLvl.ATTACK),
            Plugin.getInstance().getConfig().getDouble(ConfigFields.ChancesPerLvl.MINE),
            Plugin.getInstance().getConfig().getDouble(ConfigFields.ChancesPerLvl.LUMBER),
            Plugin.getInstance().getConfig().getDouble(ConfigFields.ChancesPerLvl.HARVEST)
    );

    static double maxLvl = Plugin.getInstance().getConfig().getDouble(ConfigFields.MAX_LVL),
            expHit = Plugin.getInstance().getConfig().getDouble(ConfigFields.EXP_HIT);

    public static double reduceExp(double exp, double skill) {
        return exp * ((maxLvl - skill) / maxLvl);
    }

    public static void killedEntity(EntityDeathEvent event) {
        double exp = Plugin.getInstance().getConfig().getDouble(event.getEntity().getType().toString());
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

        user.getSkills().updHunting(reduceExp(expHit, hunting));

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

        if (!Chance.roll(brokeChance))
            event.setCancelled(true);
        else {
            double exp = Plugin.getInstance().getConfig().getDouble(event.getBlock().getType().toString());
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

        double harvestChance = Base.hit + bonus + PerLvl.harvest * farming;

//        Plugin.getInstance().getLogger().info("chances: " +
//                "summary=" + harvestChance + " base=" + Base.harvest + " bonus=" + bonus + " skill=" + PerLvl.harvest * farming);

        if (!Chance.roll(harvestChance))
            event.setCancelled(true);
        else {
            double exp = Plugin.getInstance().getConfig().getDouble(event.getBlock().getType().toString());
            user.getSkills().updFarming(reduceExp(exp, farming));
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

        if (!Chance.roll(brokeChance))
            event.setCancelled(true);
        else {
            double exp = Plugin.getInstance().getConfig().getDouble(event.getBlock().getType().toString());
            user.getSkills().updLumbering(reduceExp(exp, lumbering));
        }
    }
}
