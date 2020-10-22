package com.vladdoth.kings_rp_plugin;

import com.connorlinfoot.titleapi.TitleAPI;
import com.vladdoth.kings_rp_plugin.authorization.Join;
import com.vladdoth.kings_rp_plugin.authorization.PreLogin;
import com.vladdoth.kings_rp_plugin.authorization.Quit;
import com.vladdoth.kings_rp_plugin.configs.commands.CheckConfig;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.commands.Job;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.commands.Skills;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.listeners.*;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
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
        getServer().getPluginManager().registerEvents(new EntityBreed(), this);
        getServer().getPluginManager().registerEvents(new EntityDeath(), this);
        getServer().getPluginManager().registerEvents(new Fishing(), this);
        getServer().getPluginManager().registerEvents(new LogBreak(), this);
        getServer().getPluginManager().registerEvents(new OreBreak(), this);
        getServer().getPluginManager().registerEvents(new PlantHarvest(), this);
        getServer().getPluginManager().registerEvents(new SheepShear(), this);

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new MenuFunctionListener(), this);

        //Не сделана авторизация
        getServer().getPluginManager().registerEvents(new PreLogin(), this);
        getServer().getPluginManager().registerEvents(new Join(), this);
        getServer().getPluginManager().registerEvents(new Quit(), this);

        getCommand("job").setExecutor(new Job());
        getCommand("skills").setExecutor(new Skills());
        getCommand("checkConfig").setExecutor(new CheckConfig());

        LuckPerms lp = LuckPermsProvider.get();
        System.out.println(lp);

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

    public DataBase getDb() {
        return db;
    }

    private static final String greetingMsg = ChatColor.DARK_GREEN + "Добро пожаловать на сервер KingsRP,";

    @EventHandler
    public void greeting(PlayerJoinEvent event) {
        TitleAPI.sendTitle(event.getPlayer(), 60, 60, 60,
                greetingMsg, event.getPlayer().getName());
    }
}
