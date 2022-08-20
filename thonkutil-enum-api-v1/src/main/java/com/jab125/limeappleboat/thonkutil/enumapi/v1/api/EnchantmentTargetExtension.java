package com.jab125.limeappleboat.thonkutil.enumapi.v1.api;

import net.minecraft.item.Item;

public interface EnchantmentTargetExtension {
    boolean thonkutil$accept(Item item);

    void thonkutil$setChecker(EnchantmentTargetAdder.Checker checker);
}
