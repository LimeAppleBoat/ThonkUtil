package com.jab125.thonkutil.misc.impl.v1.mixin;

import com.jab125.thonkutil.misc.api.v1.ThonkUtilBoatTypes;
import com.jab125.thonkutil.misc.impl.v1.BoatTypeExtension;
import com.jab125.thonkutil.misc.impl.v1.ExtendableEnum;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(BoatEntity.Type.class)
public class BoatTypeMixin implements ExtendableEnum {
    @SuppressWarnings("all")
    public static BoatEntity.Type create(String internalName, Block baseBlock, String name) {
        throw new UnsupportedOperationException();
    }
}