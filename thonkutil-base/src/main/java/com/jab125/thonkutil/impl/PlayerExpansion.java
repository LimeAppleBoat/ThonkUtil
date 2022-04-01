package com.jab125.thonkutil.impl;

public interface PlayerExpansion {
    void thonkutil$setCosmetic(String string);

    String thonkutil$getCosmetic();

    boolean thonkutil$ownsCosmetic(String string);
    boolean thonkutil$cosmeticEnchanted();
    boolean thonkutil$cosmeticEnchantable(String cosmetic);

    void thonkutil$setEnchanted(boolean enchant);
}
