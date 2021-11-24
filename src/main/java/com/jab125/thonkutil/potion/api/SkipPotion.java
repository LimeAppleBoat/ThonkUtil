package com.jab125.thonkutil.potion.api;

import com.jab125.thonkutil.potion.impl.SkipPotionImpl;
import net.minecraft.potion.Potion;

@SuppressWarnings("unused")
public class SkipPotion {
    public static final int POTION = 0;
    public static final int SPLASH_POTION = 1;
    public static final int LINGERING_POTION = 2;
    public static final int TIPPED_ARROW = 3;


    public static void skipPotion(Potion potion, int skip) {
        SkipPotionImpl.skipPotion(potion, skip);
    }
}
