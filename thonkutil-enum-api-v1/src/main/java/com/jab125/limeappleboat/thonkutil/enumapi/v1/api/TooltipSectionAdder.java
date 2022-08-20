package com.jab125.limeappleboat.thonkutil.enumapi.v1.api;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class TooltipSectionAdder {
    public static ItemStack.TooltipSection create(Identifier identifier) {
        var ordinal = ItemStack.TooltipSection.values()[ItemStack.TooltipSection.values().length-1].ordinal()+1;
        return createInternal(identifier.toString(), ordinal);
    }

    private static ItemStack.TooltipSection createInternal(String var0, int var1) {
        throw new AssertionError();
    }
}
