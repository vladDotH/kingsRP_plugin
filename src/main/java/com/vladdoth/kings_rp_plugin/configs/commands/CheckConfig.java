package com.vladdoth.kings_rp_plugin.configs.commands;

import com.vladdoth.kings_rp_plugin.configs.Values;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckConfig implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage("Это серверная команда");
            return false;
        } else {
            Values.check();
            return true;
        }
    }
}
