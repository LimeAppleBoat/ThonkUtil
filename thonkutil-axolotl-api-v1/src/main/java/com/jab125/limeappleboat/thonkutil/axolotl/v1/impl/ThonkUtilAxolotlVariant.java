package com.jab125.limeappleboat.thonkutil.axolotl.v1.impl;

import com.jab125.limeappleboat.thonkutil.axolotl.v1.impl.mixin.AxolotlTypeMixin;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.util.Identifier;

public class ThonkUtilAxolotlVariant {
    public static boolean p = false;
    private boolean custom = false;
    private Identifier id;
    private AxolotlEntity.Variant type;
    public static ThonkUtilAxolotlVariant make(AxolotlEntity.Variant type) {
        ThonkUtilAxolotlVariant thonkUtilAxolotlVariant = new ThonkUtilAxolotlVariant();
        thonkUtilAxolotlVariant.type = type;
        return thonkUtilAxolotlVariant;
    }

    public void custom() {
        custom = true;
    }

    public void setId(Identifier id) {
        this.id = id;
    }

    public boolean isCustom() {
        return custom;
    }

    public Identifier getId() {
        return !custom ? new Identifier(type.getName()) : id;
    }
}
