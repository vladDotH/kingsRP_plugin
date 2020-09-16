package com.vladdoth.kings_rp_plugin.skills_and_jobs.commands;

import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Jobs;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.data.SkillsData;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.gui.SkillsMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Skills implements CommandExecutor {
    private static final String
            permsMsg = ChatColor.RED + "У вас недостаточно прав для выполнения этой команды",
            perm = "minecraft.operator";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;

        Player p = (Player) sender;

        if (args.length == 0) {
            SkillsMenu.openMenu(p);
        } else {
            if (args[0].equals("set")) {
                if (!p.hasPermission(perm)) {
                    p.sendMessage(permsMsg);
                } else
                    setSkill(p, args[1], Double.parseDouble(args[2]));
            }
        }

        return true;
    }

    private void setSkill(Player p, String skill, double val) {
        UserData user = Plugin.getInstance().getUsers().get(p.getName());
        switch (skill) {
            case "mining":
                user.getSkills().setSkill(com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills.MINING, val);
                break;
            case "lumbering":
                user.getSkills().setSkill(com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills.LUMBERING, val);
                break;
            case "farming":
                user.getSkills().setSkill(com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills.FARMING, val);
                break;
            case "attack":
                user.getSkills().setSkill(com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills.ATTACK, val);
                break;
            case "shooting":
                user.getSkills().setSkill(com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills.SHOOTING, val);
                break;
            case "breeding":
                user.getSkills().setSkill(com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills.BREEDING, val);
                break;
            case "fishing":
                user.getSkills().setSkill(com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills.FISHING, val);
                break;
        }
    }
}
