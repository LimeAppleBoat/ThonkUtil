package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.api.potion.SkipPotion;
import com.jab125.thonkutil.config.ThonkUtilConfig;
import com.jab125.thonkutil.impl.SkipPotionImpl;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PotionItem.class)
public class PotionItemMixin {
    PotionItem potionItem;

    @Inject(method = "appendStacks", at = @At(value = "HEAD"))
    private void appendToStacks(ItemGroup group, DefaultedList<ItemStack> stacks, CallbackInfo ci) {
        this.potionItem = (PotionItem) (Object) this;
    }
    @ModifyVariable(method = "appendStacks", at = @At(value = "STORE", target = "Lnet/minecraft/item/TippedArrowItem;appendStacks(Lnet/minecraft/item/ItemGroup;Lnet/minecraft/util/collection/DefaultedList;)V"), ordinal = 0)
    private Potion modifyPotion(Potion potion) {
        if (!ThonkUtilConfig.POTION_API.getValue()) return potion;
        if (SkipPotionImpl.contains(potion, getType(this.potionItem))) {
            return new Potion();
        }
        return potion;
    }

    private int getType(PotionItem potionItem) {
        if (potionItem instanceof SplashPotionItem) return SkipPotion.SPLASH_POTION;
        if (potionItem instanceof LingeringPotionItem) return SkipPotion.LINGERING_POTION;
        return SkipPotion.POTION;
    }
}
