package com.vladdoth.kings_rp_plugin.skills_and_jobs.data;

import com.vladdoth.kings_rp_plugin.Packable;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Jobs;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.util.ExpReduce;
import org.bson.Document;

import java.util.*;

public class SkillsData implements Packable {
    private double[] skills = new double[Skills.size()];

    public void updSkill(Skills skill, double exp) {
        double skillValue = skills[skill.ordinal()];
        skills[skill.ordinal()] += ExpReduce.reduce(exp, skillValue);
    }

    public double getSkill(Skills skill) {
        return skills[skill.ordinal()];
    }

    public void setSkill(Skills skill, double val) {
        skills[skill.ordinal()] = val;
    }

    @Override
    public Document packData() {
        Document doc = new Document();
        for (int i = 0; i < skills.length; i++) {
            doc.put(Skills.values()[i].name(), skills[i]);
        }

        return doc;
    }

    @Override
    public void setData(Document doc) {
        for (int i = 0; i < skills.length; i++) {
            Double value = doc.getDouble(Skills.values()[i].name());
            if (value != null)
                skills[i] = value;
        }
    }
}
