package com.vladdoth.kings_rp_plugin;

import com.vladdoth.kings_rp_plugin.configs.Values;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.data.JobData;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.data.SkillsData;
import org.bson.Document;

public class UserData implements Packable {
    protected String name;
    protected SkillsData skills = new SkillsData();
    protected JobData jobData = new JobData();
    protected boolean authorized = false;

    public static String skillsKey = "skills",
            jobDataKey = "jobData";

    public UserData(Document doc) {
        setData(doc);
    }

    public UserData(String name) {
        this.name = name;
    }

    public SkillsData getSkills() {
        return skills;
    }

    public JobData getJobData() {
        return jobData;
    }

    public String getName() {
        return name;
    }

    @Override
    public Document packData() {
        Document doc = new Document();
        doc.put(Values.DB_PLAYER_NAME, name);
        doc.put(skillsKey, skills.packData());
        doc.put(jobDataKey, jobData.packData());

        return doc;
    }

    @Override
    public void setData(Document doc) {
        if (doc.getString(Values.DB_PLAYER_NAME) != null)
            name = doc.getString(Values.DB_PLAYER_NAME);

        if (doc.get(skillsKey) != null)
            skills.setData((Document) doc.get(skillsKey));

        if (doc.get(jobDataKey) != null)
            jobData.setData((Document) doc.get(jobDataKey));
    }
}
