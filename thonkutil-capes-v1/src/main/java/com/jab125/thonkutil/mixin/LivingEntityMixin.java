package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.api.CapeItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.jab125.thonkutil.util.Util.isModInstalled;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "getPreferredEquipmentSlot", at = @At(value = "HEAD"), cancellable = true)
    private static void getPreferredEquipmentSlot(ItemStack stack, CallbackInfoReturnable<EquipmentSlot> cir) {
        if (isModInstalled("trinkets")) return; // TODO: Trinkets compat
        Item item = stack.getItem();

        if (item instanceof CapeItem) {
            cir.setReturnValue(EquipmentSlot.CHEST);
        }

    }
}
