package com.jab125.thonkutil.api;

import com.jab125.thonkutil.ThonkUtilCapes;
import com.jab125.thonkutil.impl.TextureLoader;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Wearable;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.jab125.thonkutil.util.Util.isModInstalled;

public class CapeItem extends Item implements Wearable {
    private final boolean hasElytraTexture;
    private final boolean has2WingedElytra;
    private final Item.Settings settings;
    private boolean addToCreativeInventory = true;

    private CapeItem(CapeItem cape) {
        super(cape.settings);
        this.settings = cape.settings;
        this.has2WingedElytra = cape.has2WingedElytra;
        this.hasElytraTexture = cape.hasElytraTexture;
        this.addToCreativeInventory = cape.addToCreativeInventory;
    }

    public CapeItem(Settings settings) {
        this(settings, true);
    }

    @SuppressWarnings("unused")
    public CapeItem doNotAddToCreativeInventory() {
        addToCreativeInventory = false;
        return this;
    }

    public CapeItem(Settings settings, boolean hasElytraTexture, boolean has2WingedElytra) {
        super(settings.maxCount(1));
        this.settings = settings;
        this.hasElytraTexture = hasElytraTexture;
        this.has2WingedElytra = has2WingedElytra;
    }

    public CapeItem(Settings settings, boolean hasElytraTexture) {
        this(settings, hasElytraTexture, false);
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        super.appendStacks(group, stacks);
        if (group.equals(ThonkUtilCapes.CAPES_GROUP) && !getRegistryId().equals("thonkutil:mojang_cape") && !getRegistryId().equals("thonkutil:mojang_classic_cape") && !getRegistryId().equals("thonkutil:mojang_studios_cape") && addToCreativeInventory) stacks.add(new ItemStack(this));
    }

    private String getRegistryId() {
        return Registry.ITEM.getId(this).toString();
    }

    public Identifier getRegistryIdAsIdentifier() {
        return Registry.ITEM.getId(this);
    }

    public Identifier getCapeTexture() {
        return Identifier.tryParse(Registry.ITEM.getId(this).getNamespace() + ":textures/cape/"+ Registry.ITEM.getId(this).getPath() + ".png");
    }

    public boolean hasElytraTexture() {
        return hasElytraTexture;
    }

    private boolean hasAppliedElytraTexture = false;
    public Identifier getElytraTexture() {
        if (has2WingedElytra && hasAppliedElytraTexture) {
            TextureLoader.apply2WingedElytra(this);
            hasAppliedElytraTexture = true;
        }
        return has2WingedElytra ? Identifier.tryParse(Registry.ITEM.getId(this).getNamespace() + ":textures/elytra/"+ Registry.ITEM.getId(this).getPath() + ".png") : getCapeTexture();
    }

    @Deprecated
    public Identifier getElytraTexture0() {
        return has2WingedElytra ? Identifier.tryParse(Registry.ITEM.getId(this).getNamespace() + ":textures/elytra/"+ Registry.ITEM.getId(this).getPath() + ".png") : getCapeTexture();
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
        if (this instanceof AnimatedCapeItem)
        tooltip.add(new TranslatableText("thonkutil.animated_cape").formatted(Formatting.GRAY));
        if (!hasElytraTexture && isModInstalled("trinkets"))
        tooltip.add(new TranslatableText("thonkutil.no_elytra").formatted(Formatting.GRAY));
    }

    @Override
    public String getOrCreateTranslationKey() {
        return Util.createTranslationKey("cape", Registry.ITEM.getId(this));
    }

    public boolean has2WingedElytra() {
        return has2WingedElytra;
    }
}
