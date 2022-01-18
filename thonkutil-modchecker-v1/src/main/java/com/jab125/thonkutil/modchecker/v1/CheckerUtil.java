package com.jab125.thonkutil.modchecker.v1;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

public class CheckerUtil {
    private CheckerUtil(){}
    protected static boolean isClient() {
        return FabricLoader.getInstance().getEnvironmentType().equals(EnvType.CLIENT);
    }
}
