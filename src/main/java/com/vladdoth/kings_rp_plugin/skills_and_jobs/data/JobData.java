package com.vladdoth.kings_rp_plugin.skills_and_jobs.data;

import com.vladdoth.kings_rp_plugin.Packable;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Jobs;
import org.bson.Document;

import java.util.Date;

public class JobData implements Packable {
    private long lastJobChange = 0;
    private Jobs job = Jobs.NONE;

    public static String jobKey = "job",
            lastChangeKey = "lastChange";

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
        doc.put(jobKey, job.ordinal());
        doc.put(lastChangeKey, lastJobChange);

        return doc;
    }

    @Override
    public void setData(Document doc) {
        if (doc.getInteger(jobKey) != null)
            job = Jobs.values()[doc.getInteger(jobKey)];

        if (doc.getLong(lastChangeKey) != null)
            lastJobChange = doc.getLong(lastChangeKey);
    }
}
