package com.vladdoth.kings_rp_plugin;

import com.vladdoth.kings_rp_plugin.configs.Values;
import com.vladdoth.kings_rp_plugin.skills.SkillsData;
import org.bson.Document;

public class UserData implements Packable {
    protected String name;
    protected SkillsData skills = new SkillsData();

    public UserData(Document doc) {
        setData(doc);
    }

    public UserData(String name) {
        this.name = name;
    }

    public UserData(String name, SkillsData skills) {
        this.name = name;
        this.skills = skills;
    }

    public SkillsData getSkills() {
        return skills;
    }

    public String getName() {
        return name;
    }

    @Override
    public Document packData() {
        Document doc = new Document();
        doc.put(Values.DB_PLAYER_ID, name);
        doc.put("skills", skills.packData());
        return doc;
    }

    @Override
    public void setData(Document doc) {
        name = doc.getString(Values.DB_PLAYER_ID);
        skills.setData((Document) doc.get("skills"));
    }
}
