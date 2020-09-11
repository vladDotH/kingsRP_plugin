package com.vladdoth.kings_rp_plugin.skills.commands;

import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.skills.Jobs;
import com.vladdoth.kings_rp_plugin.skills.SkillsData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Skills implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;

        Player p = (Player) sender;

        UserData userData = Plugin.getInstance().getUsers().get(p.getName());
        SkillsData skills = userData.getSkills();

        p.sendMessage(ChatColor.YELLOW + "Ваши навыки:");
        p.sendMessage(ChatColor.YELLOW + Jobs.MINER.convert() + ": " + parseSkill(skills.getMining()));
        p.sendMessage(ChatColor.YELLOW + Jobs.LUMBER.convert() + ": " + parseSkill(skills.getLumbering()));
        p.sendMessage(ChatColor.YELLOW + Jobs.FARMER.convert() + ": " + parseSkill(skills.getFarming()));
        p.sendMessage(ChatColor.YELLOW + Jobs.HUNTER.convert() + ": " + parseSkill(skills.getHunting()));
        p.sendMessage(ChatColor.YELLOW + "Ваша работа: " + userData.getSkills().getJob().convert());

        return true;

    }

    private static String parseSkill(double skill) {
        int lvl = (int) Math.floor(skill);
        int exp = (int) ((skill - lvl) * 1000);
        return lvl + " уровень, " + exp + " опыта";
    }

}
