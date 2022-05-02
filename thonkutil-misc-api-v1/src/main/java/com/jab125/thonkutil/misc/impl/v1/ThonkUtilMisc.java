package com.jab125.thonkutil.misc.impl.v1;

import com.jab125.thonkutil.misc.api.v1.CustomBoatType;
import com.jab125.thonkutil.misc.api.v1.CustomEnchantmentTarget;
import com.jab125.thonkutil.misc.api.v1.CustomModBadge;
import com.jab125.thonkutil.misc.api.v1.ModMenuInitialization;
import com.terraformersmc.modmenu.util.mod.Mod;
import net.fabricmc.api.*;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Objects;


@EnvironmentInterfaces(value = {@EnvironmentInterface(value = EnvType.CLIENT, itf = ClientModInitializer.class), @EnvironmentInterface(value = EnvType.CLIENT, itf = ModMenuInitialization.class)})
public class ThonkUtilMisc implements ModInitializer, ClientModInitializer, ModMenuInitialization {
    @Environment(EnvType.CLIENT)
    public static final Mod.Badge ASM_BADGE = CustomModBadge.create("THONKUTIL:ASM", "thonkutil.badge.asm", 0xff6f6c6a, 0xff31302f, "thonkutil:asm", "thonkutil.searchTerms.asm");
    //public static final Mod.Badge ASM_BADGE = Mod.Badge.CLIENT;
    @Environment(EnvType.CLIENT)
    //public static final Mod.Badge UNSTABLE_BADGE = Mod.Badge.CLIENT;
    public static final Mod.Badge UNSTABLE_BADGE = CustomModBadge.create("THONKUTIL:UNSTABLE", "thonkutil.badge.unstable", 0xff841426, 0xff530C17, "thonkutil:unstable", "thonkutil.searchTerms.unstable");

    @Override
    public void onInitialize() {
        try {
            var target = FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", "net.minecraft.class_1690$class_1692");
            var d = Class.forName(target).getClassLoader().getResourceAsStream(target.replace(".", "/") + ".class");
            var e = Class.forName(target).getClassLoader().getResource(target.replace(".", "/") + ".class");
            System.out.println(target);
            System.out.println(d);
            System.out.println(e);
            //throw new RuntimeException(Objects.toString(d) + ", " + Objects.toString(e) + ", " + Class.forName(FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", "net.minecraft.class_1690$class_1692")).getClassLoader().getClass().toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        CustomBoatType.create("CRAZY_HAT", Blocks.GRASS_BLOCK, "crazy_hat");
        var type = CustomEnchantmentTarget.create("SWING", item -> item.equals(Items.TOTEM_OF_UNDYING));
        System.out.println(Arrays.toString(BoatEntity.Type.values()));
        System.out.println(Arrays.toString(EnchantmentTarget.values()));
        Registry.register(Registry.ENCHANTMENT, "swing", new Enchantment(Enchantment.Rarity.VERY_RARE, type, new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}) {

        });
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void onInitializeClient() {
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void afterModMenuInitialized() {
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void beforeModMenuInitialized() {
    }
}
