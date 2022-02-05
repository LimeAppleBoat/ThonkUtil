package com.jab125.thonkutil.mixin;

import com.jab125.thonkutil.impl.RegistryUtil;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Block.class)
public class BlockMixin implements RegistryUtil {
}
