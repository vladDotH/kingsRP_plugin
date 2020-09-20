package com.vladdoth.kings_rp_plugin.configs;

import java.lang.reflect.Field;

public class Fields {
    public static final String
            DB_URI = "DB_URI",
            DB_NAME = "DB_NAME",
            DB_COL_NAME = "DB_COL_NAME",
            DB_PLAYER_NAME = "DB_PLAYER_NAME",
            META_NOT_NATURAL = "META_NOT_NATURAL";

    public static final String
            MAX_LVL = "MAX_LVL",
            JOB_MENU_NAME = "JOB_MENU_NAME",
            SKILLS_MENU_NAME = "SKILLS_MENU_NAME",
            TIME_TO_CHANGE_JOB = "TIME_TO_CHANGE_JOB";

    public static class Events {
        public final String MINE, HARVEST, LUMBER, ATTACK,
                SHEAR, ANIMAL_DROP, FISH;

        public Events(String MINE, String HARVEST, String LUMBER, String ATTACK, String SHEAR, String ANIMAL_DROP, String FISH) {
            this.MINE = MINE;
            this.HARVEST = HARVEST;
            this.LUMBER = LUMBER;
            this.ATTACK = ATTACK;
            this.SHEAR = SHEAR;
            this.ANIMAL_DROP = ANIMAL_DROP;
            this.FISH = FISH;
        }

        @Override
        public String toString() {
            return "Events{\n" +
                    "MINE='" + MINE + '\'' +
                    ",\n HARVEST='" + HARVEST + '\'' +
                    ",\n LUMBER='" + LUMBER + '\'' +
                    ",\n ATTACK='" + ATTACK + '\'' +
                    ",\n SHEAR='" + SHEAR + '\'' +
                    ",\n ANIMAL_DROP='" + ANIMAL_DROP + '\'' +
                    ",\n FISH='" + FISH + '\'' +
                    '}';
        }
    }

    public static final Events
            BASE = new Events(
            "BASE_MINE",
            "BASE_HARVEST",
            "BASE_LUMBER",
            "BASE_ATTACK",
            "BASE_SHEAR",
            "BASE_ANIMAL_DROP",
            "BASE_FISH"),

    PER_LVL = new Events(
            "MINE_PER_LVL",
            "HARVEST_PER_LVL",
            "LUMBER_PER_LVL",
            "ATTACK_PER_LVL",
            "SHEAR_PER_LVL",
            "ANIMAL_DROP_PER_LVL",
            "FISH_PER_LVL"),

    MISS_EXP = new Events(
            "MINE_MISS",
            "HARVEST_MISS",
            "LUMBER_MISS",
            "ATTACK_MISS",
            "SHEAR_MISS",
            "ANIMAL_DROP_MISS",
            "FISH_MISS"
    );

    public static final class JOB_BONUS {
        public static final String MINER = "MINER_BONUS",
                FARMER = "FARMER_BONUS",
                LUMBER = "LUMBER_BONUS",
                BREEDER = "BREEDER_BONUS",
                FISHER = "FISHER_BONUS",

        HUNTER_SHOOT = "HUNTER_BONUS_SHOOT",
                HUNTER_DROP = "HUNTER_BONUS_DROP",
                HUNTER_ATTACK = "HUNTER_BONUS_ATTACK";
    }

    public static final String
            ATTACK_EXP = "ATTACK_EXP",
            SHEAR_EXP = "SHEAR_EXP",
            ANIMAL_DROP_EXP = "ANIMAL_DROP_EXP",
            FISH_EXP = "FISH_EXP",
            SHOOT_MISS_EXP = "SHOOT_MISS_EXP",
            SHOOT_HIT_EXP = "SHOOT_HIT_EXP",
            MAX_DISP = "MAX_DISP",
            MIN_DISP = "MIN_DISP",
            BREED_EXP = "BREED_EXP";
}
