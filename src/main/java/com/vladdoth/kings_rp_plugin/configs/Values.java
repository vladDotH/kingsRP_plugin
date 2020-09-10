package com.vladdoth.kings_rp_plugin.configs;

public class Values {
    public static final String
            DB_URI = Config.getString(Fields.DB_URI),
            DB_NAME = Config.getString(Fields.DB_NAME),
            DB_COL_NAME = Config.getString(Fields.DB_COL_NAME),
            DB_PLAYER_ID = Config.getString(Fields.DB_PLAYER_ID),
            NOT_NATURAL = Config.getString(Fields.NOT_NATURAL),
            JOB_MENU_NAME = Config.getString(Fields.JOB_MENU_NAME);

    public static final double EXP_HIT = Config.getDouble(Fields.EXP_HIT),
            MAX_LVL = Config.getDouble(Fields.MAX_LVL);

    public static final long TIME_TO_CHANGE_JOB = Config.getLong(Fields.TIME_TO_CHANGE_JOB);

    public static final String MENU_NAME = Config.getString(Fields.JOB_MENU_NAME);

    public static class Skills {
        public final double MINE, HARVEST, LUMBER, ATTACK;

        public Skills(double MINE, double HARVEST, double LUMBER, double ATTACK) {
            this.MINE = MINE;
            this.HARVEST = HARVEST;
            this.LUMBER = LUMBER;
            this.ATTACK = ATTACK;
        }
    }

    public static final Skills
            BASE_CHANCE = new Skills(
            Config.getDouble(Fields.BASE_CHANCE.MINE),
            Config.getDouble(Fields.BASE_CHANCE.HARVEST),
            Config.getDouble(Fields.BASE_CHANCE.LUMBER),
            Config.getDouble(Fields.BASE_CHANCE.ATTACK)),

    CHANCE_PER_LVL = new Skills(
            Config.getDouble(Fields.CHANCE_PER_LVL.MINE),
            Config.getDouble(Fields.CHANCE_PER_LVL.HARVEST),
            Config.getDouble(Fields.CHANCE_PER_LVL.LUMBER),
            Config.getDouble(Fields.CHANCE_PER_LVL.ATTACK)),

    JOB_BONUS = new Skills(
            Config.getDouble(Fields.JOB_BONUS.MINE),
            Config.getDouble(Fields.JOB_BONUS.HARVEST),
            Config.getDouble(Fields.JOB_BONUS.LUMBER),
            Config.getDouble(Fields.JOB_BONUS.ATTACK)),

    MISS_EXP = new Skills(
            Config.getDouble(Fields.MISS_EXP.MINE),
            Config.getDouble(Fields.MISS_EXP.HARVEST),
            Config.getDouble(Fields.MISS_EXP.LUMBER),
            Config.getDouble(Fields.MISS_EXP.ATTACK));
}
