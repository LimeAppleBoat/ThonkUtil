package com.jab125.thonkutil.impl;

public interface Potionable {
    default float potionLengthMultiplier() {
        return 1.0F;
    }

    default boolean addPotionsToCreativeInventory() {
        return false;
    }
}
