package com.jab125.thonkutil.additionalcapes.v1;

import net.fabricmc.api.*;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.jab125.thonkutil.additionalcapes.v1.item.AdditionalCapeItems.registerCapes;

@EnvironmentInterface(value = EnvType.CLIENT, itf = ClientModInitializer.class)
public class ThonkUtilAdditionalCapes implements ModInitializer, ClientModInitializer {
    public static ItemGroup ADDITIONAL_CAPES_GROUP = FabricItemGroupBuilder.create(Identifier.tryParse("thonkutil:additional_capes")).icon(() -> new ItemStack(Registry.ITEM.get(new Identifier("thonkutil:minecon_2011_cape")))).build();

    @Environment(EnvType.CLIENT)
    @Override
    public void onInitializeClient() {
    }

    @Override
    public void onInitialize() {
        registerCapes();
    }
}
