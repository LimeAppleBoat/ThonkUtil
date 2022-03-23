package com.jab125.thonkutil.api;

import com.jab125.thonkutil.api.item.*;
import com.jab125.thonkutil.impl.Potionable;
import com.jab125.thonkutil.impl.SkipPotionImpl;
import net.minecraft.item.Item;
import net.minecraft.item.LingeringPotionItem;
import net.minecraft.item.PotionItem;
import net.minecraft.item.SplashPotionItem;
import net.minecraft.potion.Potion;

@SuppressWarnings("unused")
public class SkipPotion {
    public static final int POTION = 0;
    public static final int SPLASH_POTION = 1;
    public static final int LINGERING_POTION = 2;
    public static final int TIPPED_ARROW = 3;

    public static final int POTIONABLE_AXE = 4;
    public static final int POTIONABLE_HOE = 5;
    public static final int POTIONABLE_ITEM = 6;
    public static final int POTIONABLE_PICKAXE = 7;
    public static final int POTIONABLE_SHOVEL = 8;
    public static final int POTIONABLE_SWORD = 9;
    public static final int POTIONABLE = 10;


    public static void skipPotion(Potion potion, int skip) {
        SkipPotionImpl.skipPotion(potion, skip);
    }

    public static int getType(Item item) {
        if (item instanceof SplashPotionItem) return SkipPotion.SPLASH_POTION;
        if (item instanceof LingeringPotionItem) return SkipPotion.LINGERING_POTION;
        if (item instanceof PotionableAxe) return SkipPotion.POTIONABLE_AXE;
        if (item instanceof PotionableHoe) return SkipPotion.POTIONABLE_HOE;
        if (item instanceof PotionableSword) return SkipPotion.POTIONABLE_SWORD;
        if (item instanceof PotionableShovel) return SkipPotion.POTIONABLE_SHOVEL;
        if (item instanceof PotionablePickaxe) return SkipPotion.POTIONABLE_PICKAXE;
        if (item instanceof PotionableItem) return SkipPotion.POTIONABLE_ITEM;
        if (item instanceof Potionable) return SkipPotion.POTIONABLE;

        return SkipPotion.POTION;
    }
}
