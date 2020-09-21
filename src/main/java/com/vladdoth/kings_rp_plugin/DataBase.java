package com.vladdoth.kings_rp_plugin;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.vladdoth.kings_rp_plugin.configs.Values;
import org.bson.Document;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class DataBase {
    private MongoClient mc;
    private MongoDatabase database;
    private MongoCollection<Document> usersCol;

    public DataBase() {
        mc = MongoClients.create(Values.DB_URI);
        database = mc.getDatabase(Values.DB_NAME);
        usersCol = database.getCollection(Values.DB_COL_NAME);
    }

    public boolean isUserRegistered(String name) {
        Document doc = usersCol.find(Filters.eq(Values.DB_PLAYER_NAME, name)).first();
        return doc != null;
    }

    public void loadPlayer(Player p) {
        Document doc = usersCol.find(Filters.eq(Values.DB_PLAYER_NAME, p.getName())).first();

        UserData userData;
        if (doc == null) {
            Plugin.getInstance().getLogger().warning("Player is not in base");
        } else {
            userData = new UserData(doc);
            Plugin.getInstance().getUsers().put(p.getName(), userData);
        }
    }

    public void uploadPlayer(Player p) {
        usersCol.findOneAndUpdate(
                Filters.eq(Values.DB_PLAYER_NAME, p.getName()),
                new Document("$set", Plugin.getInstance().getUsers().get(p.getName()).packData()));
    }
}
