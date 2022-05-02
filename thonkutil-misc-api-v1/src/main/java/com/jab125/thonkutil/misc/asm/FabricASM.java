package com.jab125.thonkutil.misc.asm;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

public class FabricASM implements Runnable {
    @Override
    public void run() {
        // we are very up to no good
        try {
            BoatPatch.patch();
            EnchantmentTargetPatch.patch();
            if (FabricLoader.getInstance().getEnvironmentType().equals(EnvType.CLIENT))
            ModBadgePatch.patch();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
