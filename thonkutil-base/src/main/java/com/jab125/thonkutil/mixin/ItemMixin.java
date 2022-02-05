package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.impl.RegistryUtil;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Item.class)
public class ItemMixin implements RegistryUtil {
}
