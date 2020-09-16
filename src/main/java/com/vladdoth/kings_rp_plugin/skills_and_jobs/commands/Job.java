package com.vladdoth.kings_rp_plugin.skills_and_jobs.commands;

import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.configs.Values;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.gui.JobsMenu;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;

public class Job implements CommandExecutor {
    private static final long TIME_TO_CHANGE_JOB = Values.TIME_TO_CHANGE_JOB;

    private static final String
            jobMsg = ChatColor.YELLOW + "Чтобы устроиться на работу или посмотреть текущую работу напишите /job menu " +
            "Учтите, что менять работу можно только раз в " + Values.TIME_TO_CHANGE_JOB / 3600_000 + " час(а/ов)";



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;

        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage(jobMsg);
            return false;
        }

        if (args[0].equals("menu")) {
            JobsMenu.openMenu(p);
        }

        return true;
    }

}
