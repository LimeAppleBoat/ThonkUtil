package com.jab125.limeappleboat.thonkutil.axolotl.v1.impl;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.HashMap;

public class ThonkUtilAxolotlApi implements ModInitializer {
    private static HashMap<String, Block> registry = new HashMap<>();
    @Override
    public void onInitialize() {
        var s = new String[]{"a", "b", "c"};
        for (String s1 : s) {
            register(s1, Registry.register(Registry.BLOCK, s1, new Block(AbstractBlock.Settings.of(Material.METAL))));
        }
    }

    private void register(String d, Block block) {
        registry.put(d, block);
    }
    public static Block getBlock(String s) {
        return registry.get(s);
    }
}
