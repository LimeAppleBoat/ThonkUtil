package com.jab125.thonkutil.util;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class Util {

    public static class Item {

        public static Identifier getId(ItemStack itemStack) {
            return getId(itemStack.getItem());
        }

        public static Identifier getId(net.minecraft.item.Item item) {
            return Registry.ITEM.getId(item);
        }
    }

    public static boolean isModInstalled(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }

    /**
     * @deprecated use {@link Util#isModInstalled(String)}
     */
    @Deprecated
    public static boolean isOverflowInstalled() {
        return isModInstalled("overflow");
    }
    /**
     * @deprecated use {@link Util#isModInstalled(String)}
     */
    @Deprecated
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
    public static int minutesToTick(double minutes) {
        return secondsToTick(minutes*60);
    }
    public static ItemStack toItemStack(ItemStack itemStack) {
        return itemStack;
    }
    public static ItemStack toItemStack(net.minecraft.item.Item item) {
        return new ItemStack(item);
    }
    public static ItemStack toItemStack(net.minecraft.item.Item item, int count) {
        return new ItemStack(item, count);
    }

}
