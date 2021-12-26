package com.jab125.thonkutil.api;

import com.jab125.thonkutil.ThonkUtilCapes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.jab125.thonkutil.util.Util.isModInstalled;

public class CapeItem extends Item {
    private final boolean hasElytraTexture;

    public CapeItem(Settings settings) {
        this(settings, true);
    }

    public CapeItem(Settings settings, boolean hasElytraTexture) {
        super(settings.maxCount(1).group(ThonkUtilCapes.CAPES_GROUP));
        this.hasElytraTexture = hasElytraTexture;
    }

    public Identifier getCapeTexture() {
        return Identifier.tryParse(Registry.ITEM.getId(this).getNamespace() + ":textures/cape/"+ Registry.ITEM.getId(this).getPath() + ".png");
    }

    public boolean hasElytraTexture() {
        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (!hasElytraTexture && isModInstalled("trinkets"))
        tooltip.add(new TranslatableText("thonkutil.no_elytra").formatted(Formatting.GRAY));
    }

    @Override
    public String getOrCreateTranslationKey() {
        return Util.createTranslationKey("cape", Registry.ITEM.getId(this));
    }
}
