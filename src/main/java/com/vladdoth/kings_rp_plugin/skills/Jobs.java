package com.vladdoth.kings_rp_plugin.skills;

import org.bukkit.ChatColor;

public enum Jobs {
    NONE,
    MINER,
    FARMER,
    LUMBER,
    HUNTER;

    public String convert() {
        String res = "";
        switch (this){
            case NONE:
                res = ChatColor.DARK_PURPLE + "Безработный";
                break;
            case MINER:
                res = ChatColor.BLUE + "Шахтёр";
                break;
            case FARMER:
                res = ChatColor.GREEN + "Фермер";
                break;
            case LUMBER:
                res = ChatColor.DARK_RED + "Лесоруб";
                break;
            case HUNTER:
                res = ChatColor.RED + "Охотник";
                break;
        }

        return res;
    }
};