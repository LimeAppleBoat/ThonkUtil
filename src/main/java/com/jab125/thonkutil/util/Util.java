package com.jab125.thonkutil.util;

import net.fabricmc.loader.api.FabricLoader;

public class Util {
    public static boolean isModInstalled(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }
    public static boolean isOverflowInstalled() {
        return isModInstalled("overflow");
    }
    public static boolean isEnchantmentDisplaysInstalled() {
        return isModInstalled("enchantment-displays");
    }
    public static int secondsToTick(double seconds) {
        //System.out.println((int)seconds/60 + ", " + seconds%60);
        return (int) (seconds * 20);
    }

    public static int minutesToTick(double minutes, double seconds) {
        return secondsToTick(minutes*60 + seconds);
    }
}
