package com.vladdoth.kings_rp_plugin.configs;

public class Fields {
    public static final String
            DB_URI = "data_base_uri",
            DB_NAME = "data_base_name",
            DB_COL_NAME = "data_base_collection_name",
            DB_PLAYER_ID = "data_base_id",
            NOT_NATURAL = "META_NOT_NATURAL";

    public static final String EXP_HIT = "EXP_PER_HIT",
            MAX_LVL = "MAX_LVL",
            JOB_MENU_NAME = "JOB_MENU_NAME",
            TIME_TO_CHANGE_JOB = "TIME_TO_CHANGE_JOB";

    public static class Skills {
        public final String MINE, HARVEST, LUMBER, ATTACK;

        public Skills(String MINE, String HARVEST, String LUMBER, String ATTACK) {
            this.MINE = MINE;
            this.HARVEST = HARVEST;
            this.LUMBER = LUMBER;
            this.ATTACK = ATTACK;
        }
    }

    public static final Skills
            BASE_CHANCE = new Skills(
            "BASE_CHANCE_MINE",
            "BASE_CHANCE_HARVEST",
            "BASE_CHANCE_LUMBER",
            "BASE_CHANCE_ATTACK"),

    CHANCE_PER_LVL = new Skills(
            "BASE_CHANCE_MINE",
            "BASE_CHANCE_HARVEST",
            "BASE_CHANCE_LUMBER",
            "BASE_CHANCE_ATTACK"),

    JOB_BONUS = new Skills(
            "MINER_BONUS",
            "FARMER_BONUS",
            "LUMBER_BONUS",
            "HUNTER_BONUS"),

    MISS_EXP = new Skills(
            "MINER_MISS_EXP",
            "FARMER_MISS_EXP",
            "LUMBER_MISS_EXP",
            "HUNTER_MISS_EXP"
    );
}
