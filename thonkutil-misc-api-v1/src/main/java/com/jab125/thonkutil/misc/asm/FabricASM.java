package com.jab125.thonkutil.misc.asm;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

public class FabricASM implements Runnable {
    @Override
    public void run() {
        // we are very up to no good
        try {
            try {
                Class.forName("net.fabricmc.loader.impl.launch.knot.KnotClassLoader");
            } catch (ClassNotFoundException e) {
                Patch.loader = "net.fabricmc.loader.launch.knot.KnotClassLoader";//quilt
            }
            BoatPatch.patch();
            EnchantmentTargetPatch.patch();
            if (FabricLoader.getInstance().getEnvironmentType().equals(EnvType.CLIENT) && FabricLoader.getInstance().isModLoaded("modmenu"))
            ModBadgePatch.patch();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
