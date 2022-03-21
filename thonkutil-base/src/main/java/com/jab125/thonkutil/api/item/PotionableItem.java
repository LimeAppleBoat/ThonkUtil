package com.jab125.thonkutil.api.item;

import com.jab125.thonkutil.impl.Potionable;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;

public class PotionableItem extends Item implements Potionable {
    public PotionableItem(Settings settings) {
        super(settings);
    }

    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        if (!this.addPotionsToCreativeInventory()) super.appendStacks(group, stacks);
        if (this.isIn(group)) {

            for (Potion potion : Registry.POTION) {
                if (!potion.getEffects().isEmpty()) {
                    stacks.add(PotionUtil.setPotion(new ItemStack(this), potion));
                }
            }
        }

    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        PotionUtil.buildTooltip(stack, tooltip, 1.0F);
    }
}
