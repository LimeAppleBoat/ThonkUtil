package com.jab125.thonkutil.util;

import com.jab125.thonkutil.ThonkUtilBaseClass;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public class Util implements ThonkUtilBaseClass {

    public static class Item {

        public static Identifier getId(ItemStack itemStack) {
            return getId(itemStack.getItem());
        }

        public static Identifier getId(net.minecraft.item.Item item) {
            return Registry.ITEM.getId(item);
        }
    }

    public static boolean isModInstalled(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }

    /**
     * @deprecated use {@link Util#isModInstalled(String)}
     */
    @Deprecated
    public static boolean isOverflowInstalled() {
        return isModInstalled("overflow");
    }
    /**
     * @deprecated use {@link Util#isModInstalled(String)}
     */
    @Deprecated
    public static boolean isEnchantmentDisplaysInstalled() {
        return isModInstalled("enchantment-displays");
    }
    public static int secondsToTick(double seconds) {
        //System.out.println((int)seconds/60 + ", " + seconds%60);
        return (int) (seconds * 20);
    }

    public static int minutesToTick(double minutes, double seconds) {
        return secondsToTick(minutes*60 + seconds);
    }
    public static int minutesToTick(double minutes) {
        return secondsToTick(minutes*60);
    }
    public static ItemStack toItemStack(ItemStack itemStack) {
        return itemStack;
    }
    public static ItemStack toItemStack(net.minecraft.item.Item item) {
        return new ItemStack(item);
    }
    public static ItemStack toItemStack(net.minecraft.item.Item item, int count) {
        return new ItemStack(item, count);
    }

    public static net.minecraft.item.Item getItem(Identifier id) {
        return Registry.ITEM.get(id);
    }

    @Nullable
    public static net.minecraft.item.Item getItemNullable(Identifier id) {
        return Registry.ITEM.getOrEmpty(id).orElse(null);
    }

    public static Block getBlock(Identifier id) {
        return Registry.BLOCK.get(id);
    }

    @Nullable
    public static Block getBlockNullable(Identifier id) {
        return Registry.BLOCK.getOrEmpty(id).orElse(null);
    }

    public static JointBlockItem registerBlockWithItem(Identifier identifier, Block block, net.minecraft.item.Item.Settings settings) {
        return JointBlockItem.register(identifier, block, settings);
    }

    public static class JointBlockItem {
        private final net.minecraft.item.Item item;
        private final Block block;
        private JointBlockItem(net.minecraft.item.Item item, Block block) {
            this.item = item;
            this.block = block;
        }
        private static JointBlockItem register(Identifier identifier, Block block, net.minecraft.item.Item.Settings settings) {
            return new JointBlockItem(Registry.register(Registry.ITEM, identifier, new BlockItem(block, settings)), Registry.register(Registry.BLOCK, identifier, block));
        }

        public Block getBlock() {
            return block;
        }

        public net.minecraft.item.Item getItem() {
            return item;
        }
    }

    public static net.minecraft.item.Item quickRegisterItem(Identifier id, net.minecraft.item.Item item) {
        return Registry.register(Registry.ITEM, id, item);
    }

    public static Block quickRegisterBlock(Identifier id, Block block) {
        return Registry.register(Registry.BLOCK, id, block);
    }
}
