package com.jab125.limeappleboat.thonkutil.enumapi.v1.impl;

import com.jab125.limeappleboat.thonkutil.enumapi.v1.api.*;
import com.terraformersmc.modmenu.ModMenu;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.Arrays;

public class ThonkUtilEnumApi implements ModInitializer {
    static {
        BoatEntity.Type.values();
    }
    @Override
    public void onInitialize() {
        BoatTypeAdder.create(new Identifier("thonkutil", "dog"), Blocks.BEDROCK);
        System.out.println(Arrays.toString(BoatEntity.Type.values()));
        TooltipSectionAdder.create(new Identifier("thonkutil", "elephant"));
        System.out.println(Arrays.toString(ItemStack.TooltipSection.values()));
        var a =  EnchantmentTarget.ARMOR_FEET.isAcceptableItem(Items.ACACIA_LOG);
        System.out.println(a);


        var b = EnchantmentTargetAdder.create(new Identifier("ohno", "logchant"));
        ((EnchantmentTargetExtension)(Object)b).thonkutil$setChecker((item -> item.equals(Items.ACACIA_LOG)));


        System.out.println("MATCHES: " + b.isAcceptableItem(Items.ACACIA_LOG) + ", " + b.isAcceptableItem(Items.DIAMOND));

        System.out.println(Arrays.toString(EnchantmentTargetAdder.class.getDeclaredMethods()));
        DifficultyCreator.create(new Identifier("thonkutil", "asian"), 4);
                //System.exit(0);
//        new EnchantmentTargetAdder();
//        new TooltipSectionAdder();
        //System.exit(0);
    }

    // rickroll
}
