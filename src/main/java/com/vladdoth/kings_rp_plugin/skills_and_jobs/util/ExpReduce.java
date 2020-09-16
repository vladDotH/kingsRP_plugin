package com.vladdoth.kings_rp_plugin.skills_and_jobs.util;

import com.vladdoth.kings_rp_plugin.configs.Values;

public class ExpReduce {
    public static double reduce(double exp, double skill) {
        return exp * ((Values.MAX_LVL - skill) / Values.MAX_LVL);
    }
}
