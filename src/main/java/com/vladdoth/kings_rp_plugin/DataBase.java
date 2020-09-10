package com.vladdoth.kings_rp_plugin;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.vladdoth.kings_rp_plugin.configs.Fields;
import com.vladdoth.kings_rp_plugin.configs.Values;
import org.bson.Document;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DataBase implements Listener {
    private MongoClient mc;
    private MongoDatabase database;
    private MongoCollection<Document> usersCol;

    public DataBase() {
        mc = MongoClients.create(Values.DB_URI);
        database = mc.getDatabase(Values.DB_NAME);
        usersCol = database.getCollection(Values.DB_COL_NAME);
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        loadPlayer(event.getPlayer());
    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent event) {
        uploadPlayer(event.getPlayer());
    }

    public void loadPlayer(Player p) {
        Document doc = usersCol.find(Filters.eq(Values.DB_PLAYER_ID, p.getName())).first();

        UserData userData;
        if (doc == null) {
            userData = new UserData(p.getName());
            usersCol.insertOne(new Document(Values.DB_PLAYER_ID, p.getName()));
        } else
            userData = new UserData(doc);

        Plugin.getInstance().getUsers().put(p.getName(), userData);
    }

    public void uploadPlayer(Player p) {
        usersCol.findOneAndUpdate(
                Filters.eq(Values.DB_PLAYER_ID, p.getName()),
                new Document("$set", Plugin.getInstance().getUsers().get(p.getName()).packData()));
        Plugin.getInstance().getUsers().remove(p.getName());
    }
}
