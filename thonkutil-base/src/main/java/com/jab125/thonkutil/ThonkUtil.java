package com.jab125.thonkutil;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ThonkUtil {
    public static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    public static class MODID {
        public static final String POTION_API_V0_MODID = "thonkutil-potions-v0";
        public static final String COORDS_V1_MODID = "thonkutil-coords-v1";
        public static final String TRADES_V1_MODID = "thonkutil-trades-v1";
    }
}
