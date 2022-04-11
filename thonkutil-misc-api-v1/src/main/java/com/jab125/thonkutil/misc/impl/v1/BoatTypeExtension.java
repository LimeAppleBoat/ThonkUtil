package com.jab125.thonkutil.misc.impl.v1;

import net.minecraft.entity.vehicle.BoatEntity;
import org.spongepowered.asm.mixin.gen.Accessor;

public interface BoatTypeExtension {

    BoatEntity.Type[] getField_7724();

    void setField_7724(BoatEntity.Type[] types);
}
