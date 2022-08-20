package com.jab125.limeappleboat.thonkutil.enumapi.v1.api;

import net.minecraft.block.Block;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;

public class BoatTypeAdder {
    public static BoatEntity.Type create(Identifier identifier, Block block) {
        var ordinal = BoatEntity.Type.values()[BoatEntity.Type.values().length-1].ordinal()+1;
        return createInternal(identifier.toString(), ordinal, block, identifier.getNamespace() + "/" + identifier.getPath());
    }

    private static BoatEntity.Type createInternal(String var0, int var1, Block var2, String var3) {
        throw new AssertionError();
    }
}
