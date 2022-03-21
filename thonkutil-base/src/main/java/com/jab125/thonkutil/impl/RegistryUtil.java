package com.jab125.thonkutil.impl;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
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

        if (this instanceof Entity entity) {
            return Registry.ENTITY_TYPE.getId(entity.getType());
        }

        if (this instanceof Potion potion) {
            return Registry.POTION.getId(potion);
        }

        if (this instanceof StatusEffect statusEffect) {
            return Registry.STATUS_EFFECT.getId(statusEffect);
        }
        throw new UnsupportedOperationException();
    }
}
