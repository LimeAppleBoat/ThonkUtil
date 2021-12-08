package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.api.potion.SkipPotion;
import com.jab125.thonkutil.config.ThonkUtilConfig;
import com.jab125.thonkutil.impl.SkipPotionImpl;
import net.minecraft.item.TippedArrowItem;
import net.minecraft.potion.Potion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(TippedArrowItem.class)
public class TippedArrowMixin {
    @ModifyVariable(method = "appendStacks", at = @At(value = "STORE", target = "Lnet/minecraft/item/TippedArrowItem;appendStacks(Lnet/minecraft/item/ItemGroup;Lnet/minecraft/util/collection/DefaultedList;)V"), ordinal = 0)
    private Potion modifyPotion(Potion potion) {
        if (SkipPotionImpl.contains(potion, SkipPotion.TIPPED_ARROW)) {
            return new Potion();
        }
        return potion;
    }
}
