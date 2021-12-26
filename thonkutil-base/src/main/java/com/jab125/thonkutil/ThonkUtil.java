package com.jab125.thonkutil;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThonkUtil implements ThonkUtilBaseClass {
    public static final Logger LOGGER = LogManager.getLogger("goblintraders");
    public static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    public static class MODID {
        public static final String POTIONS_V0_MODID = "thonkutil-potions-v0";
        public static final String COORDS_V1_MODID = "thonkutil-coords-v1";
        public static final String TRADES_V1_MODID = "thonkutil-trades-v1";
        public static final String CUSTOMIZATION_V1_MODID = "thonkutil-customization-v1";
        public static final String BASE_MODID = "thonkutil-base";
    }
}
