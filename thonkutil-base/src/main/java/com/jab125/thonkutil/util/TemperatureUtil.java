package com.jab125.thonkutil.util;

import net.minecraft.entity.Entity;
import net.minecraft.world.biome.Biome;

public class TemperatureUtil {
    public static float getTemperature(Entity entity) {
        if (entity.world.isClient()) return 0;
        System.out.println(entity.world.getBiome(entity.getBlockPos()).value().getTemperature());
        return entity.world.getBiome(entity.getBlockPos()).value().getTemperature();
    }

    public static float getTemperatureAlt(Entity entity) {
        if (entity.getWorld().isClient()) return 0;
        if (entity.getWorld().getDimension().isUltrawarm()) return 100;
        return getEntityBiome(entity).getTemperature() * 30;
    }
    private static Biome getEntityBiome(Entity entity) {
        //i know it works on client, but eh
        if (entity.world.isClient) throw new RuntimeException("RUN ON SERVER");
        return entity.world.getBiome(entity.getBlockPos()).value();
    }
}
