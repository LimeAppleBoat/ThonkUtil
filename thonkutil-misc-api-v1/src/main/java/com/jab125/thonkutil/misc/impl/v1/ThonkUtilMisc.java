package com.jab125.thonkutil.misc.impl.v1;

import com.jab125.thonkutil.misc.api.v1.ThonkUtilBoatTypes;
import com.jab125.thonkutil.misc.impl.v1.mixin.BoatEntityAccessor;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Arrays;

public class ThonkUtilMisc implements Runnable, ModInitializer {
    @Override
    public void run() {
        ThonkUtilBoatTypes.createType("CRAZY_HAT", "minecraft:grass_block", "crazy_hat");
    }

    @Override
    public void onInitialize() {
        BoatEntity.Type.values();
        System.out.println(Arrays.toString(BoatEntity.Type.values()));
    }
}
