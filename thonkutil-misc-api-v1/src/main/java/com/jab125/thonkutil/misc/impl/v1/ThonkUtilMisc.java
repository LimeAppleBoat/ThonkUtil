package com.jab125.thonkutil.misc.impl.v1;

import com.jab125.thonkutil.misc.api.v1.CustomBoatType;
import com.jab125.thonkutil.misc.api.v1.CustomEnchantmentTarget;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;



public class ThonkUtilMisc implements ModInitializer {
    @Override
    public void onInitialize() {
        CustomBoatType.create("CRAZY_HAT", Blocks.GRASS_BLOCK, "crazy_hat");
        var type = CustomEnchantmentTarget.create("SWING", item -> item.equals(Items.TOTEM_OF_UNDYING));
        System.out.println(Arrays.toString(BoatEntity.Type.values()));
        System.out.println(Arrays.toString(EnchantmentTarget.values()));
        Registry.register(Registry.ENCHANTMENT, "swing", new Enchantment(Enchantment.Rarity.VERY_RARE, type, new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}) {

        });
    }
}
