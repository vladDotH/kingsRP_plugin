package com.vladdoth.kings_rp_plugin.skills_and_jobs.events;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.connorlinfoot.titleapi.TitleAPI;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LevelUp {
    private static final String msg1 = "Навык ",
            msg2 = " повышен до уровня ";

    public static void lvlUpInfo(Player p, double skillValue, Skills skill) {
        int lvl = (int) Math.floor(skillValue);

        TitleAPI.sendTitle(p, 30, 30, 30, "",
                msg1 + skill.convert() + ChatColor.WHITE + msg2 + ChatColor.YELLOW + lvl);
    }

    private static final int step = 5;

    public static boolean checkLvl(double skillOld, double skillNew) {
        int lvlOld = (int) Math.floor(skillOld),
                lvlNew = (int) Math.floor(skillNew);

        return lvlNew % step == 0 && lvlNew > lvlOld;
    }
}
