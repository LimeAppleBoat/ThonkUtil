package com.jab125.thonkutil.misc.api.v1;

import net.minecraft.block.Block;
import net.minecraft.entity.vehicle.BoatEntity;

public class CustomBoatType {
    /**
     *
     * @param internalName the internal name of the Type
     * @param baseBlock the block the boat is based off
     * @param name the name of the boat
     * @return a boat type
     */
    public static BoatEntity.Type create(String internalName, Block baseBlock, String name) {
        throw new IllegalStateException("This shouldn't happen!");
    }
}
