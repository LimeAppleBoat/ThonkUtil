package com.jab125.thonkutil.impl;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public interface RegistryUtil {
    default Identifier thonkutil$getId() {
        if (this instanceof Block block) {
            return Registry.BLOCK.getId(block);
        }

        if (this instanceof Item item) {
            return Registry.ITEM.getId(item);
        }
        throw new UnsupportedOperationException();
    }
}
