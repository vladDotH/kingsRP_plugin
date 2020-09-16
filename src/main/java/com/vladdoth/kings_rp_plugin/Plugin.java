package com.vladdoth.kings_rp_plugin;

import com.connorlinfoot.titleapi.TitleAPI;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.commands.Job;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.commands.Skills;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.events.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.ipvp.canvas.MenuFunctionListener;

import java.util.HashMap;
import java.util.Map;

/*
TODO
    уведомления уровня
    работа + животоновод
    + навык рыболовства
    + работа рыбака
    + рыбак не может вытащить книги, луки и тд
    + навык стрельбы
    неудача дерева - палки
    неудача руды - булыжник
    неудача растения - семена
    c опред lvl бонус дропа
    в будущем: навык кузнечного дела и работа
*/

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

        getServer().getPluginManager().registerEvents(new Attack(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaced(), this);
        getServer().getPluginManager().registerEvents(new BowShoot(), this);
        getServer().getPluginManager().registerEvents(new EntityDeath(), this);
        getServer().getPluginManager().registerEvents(new Fishing(), this);
        getServer().getPluginManager().registerEvents(new LogBreak(), this);
        getServer().getPluginManager().registerEvents(new OreBreak(), this);
        getServer().getPluginManager().registerEvents(new PlantHarvest(), this);
        getServer().getPluginManager().registerEvents(new SheepShear(), this);

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new MenuFunctionListener(), this);

        getCommand("job").setExecutor(new Job());
        getCommand("skills").setExecutor(new Skills());
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
                event.getPlayer().getName(), ChatColor.DARK_GREEN + "Добро пожаловать на сервер KingsRP");
    }
}
