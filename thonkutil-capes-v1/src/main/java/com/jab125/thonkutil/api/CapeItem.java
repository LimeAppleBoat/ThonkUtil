package com.jab125.thonkutil.api;

import com.jab125.thonkutil.ThonkUtilCapes;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;

import static com.jab125.thonkutil.util.Util.isModInstalled;

public class CapeItem extends Item {
    private static boolean generatedGroup = false;
    private final boolean hasElytraTexture;

    public CapeItem(Settings settings) {
        this(settings, true);
    }

    public CapeItem(Settings settings, boolean hasElytraTexture) {
        super(settings.maxCount(1));
        this.hasElytraTexture = hasElytraTexture;
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        super.appendStacks(group, stacks);
        if (group.equals(ThonkUtilCapes.CAPES_GROUP) && !getRegistryId().equals("thonkutil:mojang_cape") && !getRegistryId().equals("thonkutil:mojang_classic_cape") && !getRegistryId().equals("thonkutil:mojang_studios_cape")) stacks.add(new ItemStack(this));
    }

    private String getRegistryId() {
        return Registry.ITEM.getId(this).toString();
    }

    public Identifier getCapeTexture() {
        return Identifier.tryParse(Registry.ITEM.getId(this).getNamespace() + ":textures/cape/"+ Registry.ITEM.getId(this).getPath() + ".png");
    }

    public boolean hasElytraTexture() {
        return hasElytraTexture;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (I18n.hasTranslation(this.getOrCreateTranslationKey() + ".tooltip"))
            tooltip.add(new TranslatableText(this.getOrCreateTranslationKey() + ".tooltip").formatted(Formatting.GOLD));
        if (I18n.hasTranslation(this.getOrCreateTranslationKey() + ".tooltip.2"))
            tooltip.add(new TranslatableText(this.getOrCreateTranslationKey() + ".tooltip.2").formatted(Formatting.GOLD));
        if (I18n.hasTranslation(this.getOrCreateTranslationKey() + ".tooltip.3"))
            tooltip.add(new TranslatableText(this.getOrCreateTranslationKey() + ".tooltip.3").formatted(Formatting.GOLD));
        if (!hasElytraTexture && isModInstalled("trinkets"))
        tooltip.add(new TranslatableText("thonkutil.no_elytra").formatted(Formatting.GRAY));
    }

    @Override
    public String getOrCreateTranslationKey() {
        return Util.createTranslationKey("cape", Registry.ITEM.getId(this));
    }
}