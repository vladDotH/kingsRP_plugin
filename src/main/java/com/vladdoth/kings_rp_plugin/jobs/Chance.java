package com.vladdoth.kings_rp_plugin.jobs;

import java.util.Random;

public class Chance {
    static Random rand = new Random();

    public static boolean roll(double chance) {
        double generated = (double) rand.nextInt(100_000) / 1000;
        return generated <= chance;
    }
}
