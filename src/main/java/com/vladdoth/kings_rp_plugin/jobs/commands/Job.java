package com.vladdoth.kings_rp_plugin.jobs.commands;

import com.vladdoth.kings_rp_plugin.ConfigFields;
import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.jobs.Jobs;
import com.vladdoth.kings_rp_plugin.jobs.JobsMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Date;

public class Job implements CommandExecutor {
    private static final long TIME_TO_CHANGE_JOB = Plugin.getInstance().getConfig().getLong(ConfigFields.TIME_TO_CHANGE_JOB);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage(ChatColor.YELLOW + "Чтобы открыть меню работы напишите /job join, чтобы посмотреть свои навыки /job skills");
        } else {
            if (args[0].equals("join")) {
                if (p.getGameMode() == GameMode.CREATIVE)
                    JobsMenu.openMenu(p);
                else {
                    UserData user = Plugin.getInstance().getUsers().get(p.getName());
                    long delta = new Date().getTime() - user.getSkills().getLastJobChange();
                    if (delta < TIME_TO_CHANGE_JOB) {
                        p.sendMessage(ChatColor.YELLOW + "Вы не можете так часто менять работу, подождите еще: "
                                + (int) ((TIME_TO_CHANGE_JOB - delta) / 60_000) + " минут(у/ы)");
                    } else {
                        JobsMenu.openMenu(p);
                    }
                }
            }
            if (args[0].equals("skills"))
                showSkills(p);

            if (args[0].equals("set")) {
                if (!p.hasPermission("minecraft.operator")) {
                    p.sendMessage(ChatColor.RED + "У вас недостаточно прав для выполнения этой команды");
                } else
                    setSkill(p, args[1], Double.parseDouble(args[2]));
            }
        }

        return true;
    }

    private void setSkill(Player p, String skill, double exp) {
        UserData user = Plugin.getInstance().getUsers().get(p.getName());
        switch (skill) {
            case "шахтёр":
                user.getSkills().setMining(exp);
                break;
            case "лесоруб":
                user.getSkills().setLumbering(exp);
                break;
            case "фермер":
                user.getSkills().setFarming(exp);
                break;
            case "охотник":
                user.getSkills().setHunting(exp);
                break;
            case "время":
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
