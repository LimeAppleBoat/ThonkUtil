package com.jab125.thonkutil.misc.api.v1;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface ModMenuInitialization {
    void beforeModMenuInitialized();
    void afterModMenuInitialized();
}
