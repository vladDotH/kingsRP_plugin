package com.vladdoth.kings_rp_plugin;

public class ConfigFields {
    public static final String
            DB_URI = "data_base_uri",
            DB_NAME = "data_base_name",
            DB_COL_NAME = "data_base_collection_name",
            NOT_NATURAL = "META_NOT_NATURAL";

    public static class BaseChances {
        public static final String MINE = "BASE_CHANCE_MINE",
                HARVEST = "BASE_CHANCE_HARVEST",
                LUMBER = "BASE_CHANCE_LUMBER",
                ATTACK = "BASE_CHANCE_ATTACK";
    }

    public static final class ChancesPerLvl {
        public static final String MINE = "CHANCE_MINE_PER_LVL",
                HARVEST = "CHANCE_HARVEST_PER_LVL",
                LUMBER = "CHANCE_LUBMER_PER_LVL",
                ATTACK = "CHANCE_ATTACK_PER_LVL";
    }

    public static final class JobBonus {
        public static final String MINE = "MINER_BONUS",
                HARVEST = "FARMER_BONUS",
                LUMBER = "LUMBER_BONUS",
                ATTACK = "HUNTER_BONUS";
    }

    public static final String EXP_HIT = "EXP_PER_HIT",
            MAX_LVL = "MAX_LVL",
            MENU_NAME = "MENU_NAME",
            TIME_TO_CHANGE_JOB = "TIME_TO_CHANGE_JOB";

}
