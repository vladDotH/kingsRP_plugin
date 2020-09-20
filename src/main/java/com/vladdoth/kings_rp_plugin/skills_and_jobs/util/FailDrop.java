package com.vladdoth.kings_rp_plugin.skills_and_jobs.util;

import com.vladdoth.kings_rp_plugin.skills_and_jobs.util.RandomGenerator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class FailDrop {
    public static void dropMaterial(World world, Location loc, Material drop) {
        dropItemStack(world, loc, new ItemStack(drop));
    }

    public static void randDropMaterial(World world, Location loc, List<Material> drop) {
        dropMaterial(world, loc, RandomGenerator.choice(drop));
    }

    public static void randDropMaterial(World world, Location loc, Material[] drop) {
        randDropMaterial(world, loc, Arrays.asList(drop));
    }

    public static void dropMaterial(World world, Location loc, List<Material> drop) {
        for (Material item : drop) {
            dropMaterial(world, loc, item);
        }
    }

    public static void dropMaterial(World world, Location loc, Material[] drop) {
        dropMaterial(world, loc, Arrays.asList(drop));
    }


    public static void dropItemStack(World world, Location loc, ItemStack drop) {
        world.dropItem(loc, drop);
    }

    public static void randDropItemStack(World world, Location loc, List<ItemStack> drop) {
        dropItemStack(world, loc, RandomGenerator.choice(drop));
    }

    public static void randDropItemStack(World world, Location loc, ItemStack[] drop) {
        randDropItemStack(world, loc, Arrays.asList(drop));
    }

    public static void dropItemStack(World world, Location loc, List<ItemStack> drop) {
        for (ItemStack item : drop) {
            dropItemStack(world, loc, item);
        }
    }

    public static void dropItemStack(World world, Location loc, ItemStack[] drop) {
        dropItemStack(world, loc, Arrays.asList(drop));
    }
}
