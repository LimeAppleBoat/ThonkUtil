package com.jab125.thonkutil.config.option;

import com.jab125.thonkutil.ThonkUtilBaseClass;
import com.jab125.thonkutil.util.translation.TranslationUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.CyclingOption;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class ToolTipBooleanConfigOption extends BooleanConfigOption implements ThonkUtilBaseClass {
    private final Text enabledText;
    private final Text disabledText;
    private final String translationKey, key;

    public ToolTipBooleanConfigOption(String key, String modid, boolean defaultValue, String enabledKey, String disabledKey) {
        super(key, modid, defaultValue, enabledKey, disabledKey);
        this.key = key;
        this.translationKey = TranslationUtil.translationKeyOf("option", key, modid);
        this.enabledText = new TranslatableText(translationKey + "." + enabledKey);
        this.disabledText = new TranslatableText(translationKey + "." + disabledKey);
    }

    public ToolTipBooleanConfigOption(String key, String modid, boolean defaultValue) {
        this(key, modid, defaultValue, "true", "false");
    }

    public Text getToolTip() {
        return new TranslatableText(translationKey + ".tooltip");
    }

    @Override
    @Environment(EnvType.CLIENT)
    public CyclingOption<Boolean> asOption() {
        if (enabledText != null && disabledText != null) {
            CyclingOption<Boolean> cyclingOption = CyclingOption.create(translationKey, enabledText, disabledText, ignored -> ConfigOptionStorage.getBoolean(key), (ignored, option, value) -> ConfigOptionStorage.setBoolean(key, value));
            return cyclingOption.tooltip((client -> aBoolean -> {
                return client.textRenderer.wrapLines(getToolTip(), 200);
            }));
        }
        return CyclingOption.create(translationKey, getToolTip(), ignored -> ConfigOptionStorage.getBoolean(key), (ignored, option, value) -> ConfigOptionStorage.setBoolean(key, value));
    }
}
