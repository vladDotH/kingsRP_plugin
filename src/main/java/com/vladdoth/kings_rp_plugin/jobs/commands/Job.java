package com.vladdoth.kings_rp_plugin.jobs.commands;

import com.vladdoth.kings_rp_plugin.configs.Config;
import com.vladdoth.kings_rp_plugin.configs.Fields;
import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.configs.Values;
import com.vladdoth.kings_rp_plugin.jobs.Jobs;
import com.vladdoth.kings_rp_plugin.jobs.JobsMenu;
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
            jobMsg = ChatColor.YELLOW + "Чтобы открыть меню работы напишите /job join, чтобы посмотреть свои навыки /job skills",
            warnMsg = ChatColor.YELLOW + "Вы не можете так часто менять работу, подождите еще: ",
            permsMsg = ChatColor.RED + "У вас недостаточно прав для выполнения этой команды",
            perm = "minecraft.operator";


    @Override

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;

        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage(jobMsg);
            return false;
        }

        if (args[0].equals("join")) {
            if (p.getGameMode() == GameMode.CREATIVE)
                JobsMenu.openMenu(p);
            else {
                UserData user = Plugin.getInstance().getUsers().get(p.getName());
                long delta = new Date().getTime() - user.getSkills().getLastJobChange();
                if (delta < TIME_TO_CHANGE_JOB) {
                    p.sendMessage(warnMsg + (int) ((TIME_TO_CHANGE_JOB - delta) / 60_000) + " минут(у/ы)");
                } else {
                    JobsMenu.openMenu(p);
                }
            }
        }

        if (args[0].equals("skills"))
            showSkills(p);

        if (args[0].equals("set")) {
            if (!p.hasPermission(perm)) {
                p.sendMessage(permsMsg);
            } else
                setSkill(p, args[1], Double.parseDouble(args[2]));
        }

        return true;
    }

    private void setSkill(Player p, String skill, double exp) {
        UserData user = Plugin.getInstance().getUsers().get(p.getName());
        switch (skill) {
            case "miner":
                user.getSkills().setMining(exp);
                break;
            case "lumber":
                user.getSkills().setLumbering(exp);
                break;
            case "farmer":
                user.getSkills().setFarming(exp);
                break;
            case "hunter":
                user.getSkills().setHunting(exp);
                break;
            case "time":
                user.getSkills().setLastJobChange((long) exp);
        }
    }


    private void showSkills(Player p) {
        UserData userData = Plugin.getInstance().getUsers().get(p.getName());
        p.sendMessage(ChatColor.YELLOW + "Ваши навыки:");
        p.sendMessage(ChatColor.YELLOW + Jobs.MINER.convert() + ": " + String.format("%.3f", userData.getSkills().getMining()));
        p.sendMessage(ChatColor.YELLOW + Jobs.LUMBER.convert() + ": " + String.format("%.3f", userData.getSkills().getLumbering()));
        p.sendMessage(ChatColor.YELLOW + Jobs.FARMER.convert() + ": " + String.format("%.3f", userData.getSkills().getFarming()));
        p.sendMessage(ChatColor.YELLOW + Jobs.HUNTER.convert() + ": " + String.format("%.3f", userData.getSkills().getHunting()));
        p.sendMessage(ChatColor.YELLOW + "Ваша работа: " + userData.getSkills().getJob().convert());
    }
}
