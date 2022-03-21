package com.jab125.thonkutil;

import com.jab125.thonkutil.config.ThonkUtilCustomizationConfigManager;
import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ThonkUtilCustomization implements ModInitializer {
    public static final Enchantment VOID_SAVER = Registry.register(Registry.ENCHANTMENT, new Identifier("thonkutil", "void_saver"), new Enchantment(Enchantment.Rarity.RARE, EnchantmentTarget.TRIDENT, EquipmentSlot.values()) {
        @Override
        public int getMinLevel() {
            return 1;
        }

        @Override
        public int getMaxLevel() {
            return 3;
        }
    });

    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        ThonkUtilCustomizationConfigManager.initializeConfig();
    }
}
