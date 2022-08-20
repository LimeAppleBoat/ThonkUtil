package com.jab125.limeappleboat.thonkutil.enumapi.v1.impl.mixin.enchantmenttarget;

import com.jab125.limeappleboat.thonkutil.enumapi.v1.api.EnchantmentTargetAdder;
import com.jab125.limeappleboat.thonkutil.enumapi.v1.api.EnchantmentTargetExtension;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EnchantmentTarget.class)
public class EnchantmentTargetMixin implements EnchantmentTargetExtension {
    private EnchantmentTargetAdder.Checker checker;
    private boolean thonkutil$isAcceptableItem(Item item) /*isAcceptableItem*/ {
        return thonkutil$accept(item);
    }

    @Override
    public void thonkutil$setChecker(EnchantmentTargetAdder.Checker checker) {
        this.checker = checker;
    }

    @Override
    public boolean thonkutil$accept(Item item) {
        return this.checker.check(item);
    }
}
