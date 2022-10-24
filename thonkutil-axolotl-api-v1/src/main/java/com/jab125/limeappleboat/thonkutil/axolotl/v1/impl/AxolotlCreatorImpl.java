package com.jab125.limeappleboat.thonkutil.axolotl.v1.impl;

import com.jab125.limeappleboat.thonkutil.axolotl.v1.impl.mixin.VariantAccessor;
import net.minecraft.entity.passive.AxolotlEntity;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class AxolotlCreatorImpl {
    @ApiStatus.Internal
    public static AxolotlEntity.Variant create(String internalName, int ordinal, int id, String name, boolean natural) {
        var d = new ArrayList<>(Arrays.stream(AxolotlEntity.Variant.VARIANTS).toList());
        var e = createInternal(internalName, ordinal, id, name, natural);
        d.add(e);
        VariantAccessor.setVARIANTS(d.stream().sorted(Comparator.comparingInt(AxolotlEntity.Variant::getId)).toArray(AxolotlEntity.Variant[]::new));
        return e;
    }
    private static AxolotlEntity.Variant createInternal(String TYPE, int ORDINAL, int id, String name, boolean natural) {
        throw new AssertionError();
    }
}
