package dev.tr7zw.waveycapes.support;

import dev.tr7zw.waveycapes.CapeRenderer;

public interface ModSupport {
    public boolean shouldBeUsed(net.minecraft.client.network.AbstractClientPlayerEntity player);

    public CapeRenderer getRenderer();

    public boolean blockFeatureRenderer(Object feature);
}
