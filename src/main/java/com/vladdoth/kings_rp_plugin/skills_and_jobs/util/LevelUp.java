package com.vladdoth.kings_rp_plugin.skills_and_jobs.util;

import com.connorlinfoot.titleapi.TitleAPI;
import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LevelUp {
    private static final String msg1 = "Навык ",
            msg2 = " повышен до уровня ";

    public static void checkAndInfo(Player player, Skills skill, double skillOld) {
        double skillNew = Plugin.getInstance().getUsers().get(player.getName()).getSkills().getSkill(skill);

        if (checkLvlStep(skillOld, skillNew))
            StepLvlUpInfo(player, skill, skillNew);
        else if (checkLvlUp(skillOld, skillNew))
            lvlUpInfo(player, skill, skillNew);
    }

    private static final int step = 5;

    public static void StepLvlUpInfo(Player p, Skills skill, double skillValue) {
        int lvl = (int) Math.floor(skillValue);

        TitleAPI.sendTitle(p, 30, 30, 30,
                msg1 + skill.convert() + ChatColor.WHITE + msg2 + ChatColor.YELLOW + lvl, "");
    }

    public static boolean checkLvlStep(double skillOld, double skillNew) {
        int lvlNew = (int) Math.floor(skillNew);
        return lvlNew % step == 0 && checkLvlUp(skillOld, skillNew);
    }

    public static void lvlUpInfo(Player p, Skills skill, double skillValue) {
        int lvl = (int) Math.floor(skillValue);

        TitleAPI.sendTitle(p, 20, 20, 20,
                "", msg1 + skill.convert() + ChatColor.WHITE + msg2 + ChatColor.YELLOW + lvl);
    }

    public static boolean checkLvlUp(double skillOld, double skillNew) {
        int lvlOld = (int) Math.floor(skillOld),
                lvlNew = (int) Math.floor(skillNew);

        return lvlNew > lvlOld;
    }
}
