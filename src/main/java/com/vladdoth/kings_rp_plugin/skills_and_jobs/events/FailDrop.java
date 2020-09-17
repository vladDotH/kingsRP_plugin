package com.vladdoth.kings_rp_plugin.skills_and_jobs.events;

import com.vladdoth.kings_rp_plugin.skills_and_jobs.util.RandomGenerator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class FailDrop {
    public static void failRandomDrop(World world, Location loc, List<Material> drop) {
        Material item = RandomGenerator.choice(drop);
        world.dropItem(loc, new ItemStack(item));
    }

    public static void failRandomDrop(World world, Location loc, Material[] drop) {
        Material item = RandomGenerator.choice(drop);
        world.dropItem(loc, new ItemStack(item));
    }

    public static void failDrop(World world, Location loc, Material drop) {
        world.dropItem(loc, new ItemStack(drop));
    }

    public static void failDrop(World world, Location loc, Material[] drop) {
        for (Material item : drop) {
            failDrop(world, loc, item);
        }
    }
}
