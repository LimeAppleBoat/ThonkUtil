package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.impl.RemovePotionRecipeImpl;
import com.jab125.thonkutil.util.Util;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Mixin(BrewingStandBlockEntity.class)
public class PotionRecipeMixin {
    private static DefaultedList<ItemStack> stacks;

    @Inject(method = "canCraft", at = @At("HEAD"))
    private static void getList(DefaultedList<ItemStack> slots, CallbackInfoReturnable<Boolean> cir) {
        stacks = slots;
    }
    @ModifyVariable(method = "canCraft", at = @At("STORE"))
    private static boolean canCraftInject(boolean b) {
        AtomicInteger atomicInteger = new AtomicInteger(stacks.size());
        stacks.iterator().forEachRemaining((itemStack) -> {
            if (RemovePotionRecipeImpl.RemovePotionRecipeImpl2.contains(PotionUtil.getPotion(itemStack))) {
                atomicInteger.set(atomicInteger.get() - 1);
            }
        });
        return atomicInteger.get() > 0;
    }
}
