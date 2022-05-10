package com.jab125.thonkutil.misc.impl.v1.mixin;

import com.jab125.thonkutil.misc.api.v1.ThonkUtilBoatTypes;
import com.jab125.thonkutil.misc.impl.v1.BoatTypeExtension;
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
public class BoatTypeMixin implements BoatTypeExtension {
    /*
     * This file looks very scary and ugly, but what it basically does is add our boat into the vanilla enum found in BoatEntity.java
     *
     * While it works and should work with any other mod that does the same thing, adding to enums is not recommended.
     * For any future situations where you are required to add to an enum, look into Fabric-ASM: https://github.com/Chocohead/Fabric-ASM
     */

    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static BoatEntity.Type newType(String internalName, int internalId, Block baseBlock, String name) {
        throw new AssertionError();
    }

    @SuppressWarnings("ShadowTarget")
    @Shadow
    @Final
    @Mutable
    private static BoatEntity.Type[] field_7724;

    @Override
    public BoatEntity.Type[] getField_7724() {
        return field_7724;
    }

    @Override
    public void setField_7724(BoatEntity.Type[] types) {
        field_7724 = types;
    }


    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "<clinit>", at = @At(value = "FIELD",
            opcode = Opcodes.PUTSTATIC,
            target = "Lnet/minecraft/entity/vehicle/BoatEntity$Type;field_7724:[Lnet/minecraft/entity/vehicle/BoatEntity$Type;",
            shift = At.Shift.AFTER))
    private static void addCustomBoatType(CallbackInfo ci) {
        FabricLoader.getInstance().getEntrypoints("thonkutil:early_load", Runnable.class).forEach(Runnable::run);
        System.out.println("INITED");
        System.out.println(ThonkUtilBoatTypes.boatTypes.size());
        ThonkUtilBoatTypes.boatTypes.forEach((boatTypes -> {
            var types = new ArrayList<>(Arrays.asList(field_7724));
            var last = types.get(types.size() - 1);
            types.add(newType(boatTypes.getFieldName(), last.ordinal() + 1, Registry.BLOCK.get(Identifier.tryParse(boatTypes.getBaseBlock())), boatTypes.getName()));
            field_7724 = (types.toArray(new BoatEntity.Type[0]));
        }));
    }
}