package com.vladdoth.kings_rp_plugin.configs;

public class Values {
    public static final String
            DB_URI = Config.getString(Fields.DB_URI),
            DB_NAME = Config.getString(Fields.DB_NAME),
            DB_COL_NAME = Config.getString(Fields.DB_COL_NAME),
            DB_PLAYER_ID = Config.getString(Fields.DB_PLAYER_ID),
            NOT_NATURAL = Config.getString(Fields.META_NOT_NATURAL),
            JOB_MENU_NAME = Config.getString(Fields.JOB_MENU_NAME),
            SKILLS_MENU_NAME = Config.getString(Fields.SKILLS_MENU_NAME);

    public static final double
            MAX_LVL = Config.getDouble(Fields.MAX_LVL);

    public static final long
            TIME_TO_CHANGE_JOB = Config.getLong(Fields.TIME_TO_CHANGE_JOB);

    public static class Events {
        public final double MINE, HARVEST, LUMBER, ATTACK,
                SHEAR, ANIMAL_DROP, FISH;

        public Events(double MINE, double HARVEST, double LUMBER, double ATTACK, double SHEAR, double ANIMAL_DROP, double FISH) {
            this.MINE = MINE;
            this.HARVEST = HARVEST;
            this.LUMBER = LUMBER;
            this.ATTACK = ATTACK;
            this.SHEAR = SHEAR;
            this.ANIMAL_DROP = ANIMAL_DROP;
            this.FISH = FISH;
        }
    }

    public static final Events
            BASE_CHANCE = new Events(
            Config.getDouble(Fields.BASE.MINE),
            Config.getDouble(Fields.BASE.HARVEST),
            Config.getDouble(Fields.BASE.LUMBER),
            Config.getDouble(Fields.BASE.ATTACK),
            Config.getDouble(Fields.BASE.SHEAR),
            Config.getDouble(Fields.BASE.ANIMAL_DROP),
            Config.getDouble(Fields.BASE.FISH)),

    CHANCE_PER_LVL = new Events(
            Config.getDouble(Fields.PER_LVL.MINE),
            Config.getDouble(Fields.PER_LVL.HARVEST),
            Config.getDouble(Fields.PER_LVL.LUMBER),
            Config.getDouble(Fields.PER_LVL.ATTACK),
            Config.getDouble(Fields.PER_LVL.SHEAR),
            Config.getDouble(Fields.PER_LVL.ANIMAL_DROP),
            Config.getDouble(Fields.PER_LVL.FISH)),

    MISS_EXP = new Events(
            Config.getDouble(Fields.MISS_EXP.MINE),
            Config.getDouble(Fields.MISS_EXP.HARVEST),
            Config.getDouble(Fields.MISS_EXP.LUMBER),
            Config.getDouble(Fields.MISS_EXP.ATTACK),
            Config.getDouble(Fields.MISS_EXP.SHEAR),
            Config.getDouble(Fields.MISS_EXP.ANIMAL_DROP),
            Config.getDouble(Fields.MISS_EXP.FISH));

    public static final class JOB_BONUS {
        public static final double MINER = Config.getDouble(Fields.JOB_BONUS.MINER),
                FARMER = Config.getDouble(Fields.JOB_BONUS.FARMER),
                LUMBER = Config.getDouble(Fields.JOB_BONUS.LUMBER),
                BREEDER = Config.getDouble(Fields.JOB_BONUS.BREEDER),
                FISHER = Config.getDouble(Fields.JOB_BONUS.FISHER),
                HUNTER_SHOOT = Config.getDouble(Fields.JOB_BONUS.HUNTER_SHOOT),
                HUNTER_DROP = Config.getDouble(Fields.JOB_BONUS.HUNTER_DROP),
                HUNTER_ATTACK = Config.getDouble(Fields.JOB_BONUS.HUNTER_ATTACK);
    }

    public static final double
            ATTACK_EXP = Config.getDouble(Fields.ATTACK_EXP),
            SHEAR_EXP = Config.getDouble(Fields.SHEAR_EXP),
            ANIMAL_DROP_EXP = Config.getDouble(Fields.ANIMAL_DROP_EXP),
            FISH_EXP = Config.getDouble(Fields.FISH_EXP),
            SHOOT_MISS_EXP = Config.getDouble(Fields.SHOOT_MISS_EXP),
            SHOOT_HIT_EXP = Config.getDouble(Fields.SHOOT_HIT_EXP),
            MAX_DISP = Config.getDouble(Fields.MAX_DISP),
            MIN_DISP = Config.getDouble(Fields.MIN_DISP);
}
