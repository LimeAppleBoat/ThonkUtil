package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.api.CapeItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorStandEntity.class)
public class ArmorStandMixin {
//    public void equipStack(EquipmentSlot slot, ItemStack stack) {
//        this.processEquippedStack(stack);
//        switch(slot.getType()) {
//            case HAND:
//                this.onEquipStack(stack);
//                this.heldItems.set(slot.getEntitySlotId(), stack);
//                break;
//            case ARMOR:
//                this.onEquipStack(stack);
//                this.armorItems.set(slot.getEntitySlotId(), stack);
//        }
//
//    }

    @Inject(method = "canEquip", at = @At("HEAD"), cancellable = true)
    public void thonkutil$canEquip(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof CapeItem) cir.setReturnValue(true);
    }

    private PlayerEntity player;
    private Hand hand;
    @Inject(method = "interactAt", at = @At("HEAD"))
    private void thonkutil$captureLocals(PlayerEntity player, Vec3d hitPos, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        this.player = player;
        this.hand = hand;
    }
    @ModifyVariable(method = "interactAt", at = @At(value = "STORE", target = "Lnet/minecraft/entity/decoration/ArmorStandEntity;interactAt(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/ActionResult;"))
    private EquipmentSlot thonkutil$modifyEquipmentSlot(EquipmentSlot value) {
        if (player.getStackInHand(hand).getItem() instanceof CapeItem) {
            return EquipmentSlot.CHEST;
        }
        return value;
    }
}
