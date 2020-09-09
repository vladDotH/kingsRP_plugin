package com.vladdoth.kings_rp_plugin.jobs;

import com.vladdoth.kings_rp_plugin.Packable;
import com.vladdoth.kings_rp_plugin.jobs.commands.Job;
import org.bson.Document;

import java.util.Date;

public class SkillsData implements Packable {
    private double mining = 0,
            farming = 0,
            hunting = 0,
            lumbering = 0;
    private long lastJobChange = 0;

    private Jobs job = Jobs.NONE;

    public SkillsData(double mining, double farming, double hunting, double lumbering, Jobs job) {
        this.mining = mining;
        this.farming = farming;
        this.hunting = hunting;
        this.lumbering = lumbering;
        this.job = job;
    }

    public SkillsData() {
    }

    public void updMining(double exp) {
        mining += exp;
    }

    public void updFarming(double exp) {
        farming += exp;
    }

    public void updHunting(double exp) {
        hunting += exp;
    }

    public void updLumbering(double exp) {
        lumbering += exp;
    }

    public double getMining() {
        return mining;
    }

    public double getFarming() {
        return farming;
    }

    public double getHunting() {
        return hunting;
    }

    public double getLumbering() {
        return lumbering;
    }

    public void setMining(double mining) {
        this.mining = mining;
    }

    public void setFarming(double farming) {
        this.farming = farming;
    }

    public void setHunting(double hunting) {
        this.hunting = hunting;
    }

    public void setLumbering(double lumbering) {
        this.lumbering = lumbering;
    }

    public Jobs getJob() {
        return job;
    }

    public void setJob(Jobs job) {
        lastJobChange = new Date().getTime();
        this.job = job;
    }

    public long getLastJobChange() {
        return lastJobChange;
    }

    public void setLastJobChange(long lastJobChange) {
        this.lastJobChange = lastJobChange;
    }

    @Override
    public Document packData() {
        Document doc = new Document();
        doc.put("mining", mining);
        doc.put("farming", farming);
        doc.put("hunting", hunting);
        doc.put("lumbering", lumbering);
        doc.put("job", job.ordinal());
        doc.put("lastChange", lastJobChange);

        return doc;
    }

    @Override
    public void setData(Document doc) {
        mining = doc.getDouble("mining");
        farming = doc.getDouble("farming");
        hunting = doc.getDouble("hunting");
        lumbering = doc.getDouble("lumbering");
        job = Jobs.values()[doc.getInteger("job")];
        lastJobChange = doc.getLong("lastChange");
    }
}
