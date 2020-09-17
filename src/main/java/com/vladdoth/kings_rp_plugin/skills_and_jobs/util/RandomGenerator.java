package com.vladdoth.kings_rp_plugin.skills_and_jobs.util;

import org.bukkit.util.Vector;

import java.util.List;
import java.util.Random;

public class RandomGenerator {
    static Random rand = new Random();

    public static boolean roll(double chance) {
        double generated = (double) rand.nextInt(100_000) / 1000;
        return generated <= chance;
    }

    private static int precision = 1000;

    /*
        x, y, z : [-R, R]
     */
    public static Vector dispersion(double R) {
        double x = (double) (new Random().nextInt((int) (2 * R * precision))) / precision - R,
                y = (double) (new Random().nextInt((int) (2 * R * precision))) / precision - R,
                z = (double) (new Random().nextInt((int) (2 * R * precision))) / precision - R;

        return new Vector(x, y, z);
    }


    public static <T> T choice(List<T> l) {
        int i = rand.nextInt(l.size());
        return l.get(i);
    }

    public static <T> T choice(T[] l) {
        int i = rand.nextInt(l.length);
        return l[i];
    }
}
