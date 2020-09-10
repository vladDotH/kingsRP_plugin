package com.vladdoth.kings_rp_plugin;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.connorlinfoot.titleapi.TitleAPI;
import com.vladdoth.kings_rp_plugin.jobs.JobsMenu;
import com.vladdoth.kings_rp_plugin.jobs.commands.Job;
import com.vladdoth.kings_rp_plugin.jobs.events.BlockBroken;
import com.vladdoth.kings_rp_plugin.jobs.events.BlockPlaced;
import com.vladdoth.kings_rp_plugin.jobs.events.EntityDamaged;
import com.vladdoth.kings_rp_plugin.jobs.events.EntityDeath;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Plugin extends JavaPlugin implements Listener {
    private Map<String, UserData> users;
    private DataBase db;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        db = new DataBase();
        users = new HashMap<>();

        getServer().getPluginManager().registerEvents(db, this);
        getServer().getPluginManager().registerEvents(new BlockBroken(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaced(), this);
        getServer().getPluginManager().registerEvents(new EntityDamaged(), this);
        getServer().getPluginManager().registerEvents(new EntityDeath(), this);
        getServer().getPluginManager().registerEvents(new JobsMenu(), this);
        getServer().getPluginManager().registerEvents(this, this);

        getCommand("job").setExecutor(new Job());
    }

    @Override
    public void onDisable() {
        for (String name : users.keySet()) {
            db.uploadPlayer(Bukkit.getPlayer(name));
        }
    }

    public static Plugin getInstance() {
        return getPlugin(Plugin.class);
    }

    public Map<String, UserData> getUsers() {
        return users;
    }

    @EventHandler
    public void playerJoined(PlayerJoinEvent event) {
        TitleAPI.sendTitle(event.getPlayer(), 60, 60, 60,
                event.getPlayer().getName(),ChatColor.DARK_GREEN + "Добро пожаловать на сервер KingsRP");
    }
}
