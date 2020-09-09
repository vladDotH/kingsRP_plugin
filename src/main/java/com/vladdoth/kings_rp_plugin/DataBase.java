package com.vladdoth.kings_rp_plugin;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.internal.operation.UpdateOperation;
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
        FileConfiguration cfg = Plugin.getInstance().getConfig();
        String dbURI = cfg.getString(ConfigFields.DB_URI),
                dbName = cfg.getString(ConfigFields.DB_NAME),
                colName = cfg.getString(ConfigFields.DB_COL_NAME);

        mc = MongoClients.create(dbURI);
        database = mc.getDatabase(dbName);
        usersCol = database.getCollection(colName);
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
        Document doc = usersCol.find(Filters.eq("name", p.getName())).first();

        UserData userData;
        if (doc == null) {
            userData = new UserData(p.getName());
            usersCol.insertOne(new Document("name", p.getName()));
        } else
            userData = new UserData(doc);

        Plugin.getInstance().getUsers().put(p.getName(), userData);
    }

    public void uploadPlayer(Player p) {
        usersCol.findOneAndUpdate(
                Filters.eq("name", p.getName()),
                new Document("$set", Plugin.getInstance().getUsers().get(p.getName()).packData()));
        Plugin.getInstance().getUsers().remove(p.getName());
    }
}
