package com.vladdoth.kings_rp_plugin.skills_and_jobs;

import com.vladdoth.kings_rp_plugin.configs.Config;
import org.bukkit.ChatColor;

public enum Skills {
    MINING,
    FARMING,
    LUMBERING,
    ATTACK,
    SHOOTING,
    BREEDING,
    FISHING;

    public String convert() {
        String res = "";
        switch (this) {
            case MINING:
                res = ChatColor.BLUE + "Горное Дело";
                break;
            case FARMING:
                res = ChatColor.GREEN + "Фермерство";
                break;
            case LUMBERING:
                res = ChatColor.DARK_RED + "Лесозаготовка";
                break;
            case ATTACK:
                res = ChatColor.RED + "Атака";
                break;
            case SHOOTING:
                res = ChatColor.RED + "Стрельба";
                break;
            case BREEDING:
                res = ChatColor.GOLD + "Животноводство";
                break;
            case FISHING:
                res = ChatColor.AQUA + "Рыболовство";
                break;
        }

        return res;
    }

    public static int size() {
        return Skills.values().length;
    }
}
