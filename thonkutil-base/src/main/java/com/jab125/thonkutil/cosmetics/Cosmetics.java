package com.jab125.thonkutil.cosmetics;

import com.jab125.thonkutil.cosmetics.exclamationmark.ExclamationMarkModel;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class Cosmetics {
    public static final EntityModelLayer EXCLAMATION_MARK = new EntityModelLayer(cosmeticId("exclamation_mark"), "exclamation_mark");
    public static void init() {
        EntityModelLayerRegistry.registerModelLayer(EXCLAMATION_MARK, ExclamationMarkModel::createTexturedModelData);
    }

    private static Identifier cosmeticId(String id) {
        return new Identifier("thonkutil", "cosmetic_" + id);
    }
}
