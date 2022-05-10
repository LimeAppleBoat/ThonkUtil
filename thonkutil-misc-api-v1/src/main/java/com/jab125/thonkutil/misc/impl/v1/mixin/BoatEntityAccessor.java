package com.jab125.thonkutil.misc.impl.v1.mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.vehicle.BoatEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BoatEntity.Type.class)
public interface BoatEntityAccessor {
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    public static BoatEntity.Type newType(String internalName, int internalId, Block baseBlock, String name) {
        throw new AssertionError();
    }
}
