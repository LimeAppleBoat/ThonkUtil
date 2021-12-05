package com.jab125.thonkutil.config.option;

import com.jab125.thonkutil.util.translation.TranslationUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.Option;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class BooleanConfigOption implements OptionConvertable {
    private final String key, translationKey;
    private final boolean defaultValue;
    private final Text enabledText;
    private final Text disabledText;

    public BooleanConfigOption(String key, String modid, boolean defaultValue, String enabledKey, String disabledKey) {
        ConfigOptionStorage.setBoolean(key, defaultValue);
        this.key = key;
        this.translationKey = TranslationUtil.translationKeyOf("option", modid, key);
        this.defaultValue = defaultValue;
        this.enabledText = new TranslatableText(translationKey + "." + enabledKey);
        this.disabledText = new TranslatableText(translationKey + "." + disabledKey);
    }

    public BooleanConfigOption(String key, String modid, boolean defaultValue) {
        this(key, modid, defaultValue, "true", "false");
    }

    public String getKey() {
        return key;
    }

    public boolean getValue() {
        return ConfigOptionStorage.getBoolean(key);
    }

    public void setValue(boolean value) {
        ConfigOptionStorage.setBoolean(key, value);
    }

    public void toggleValue() {
        ConfigOptionStorage.toggleBoolean(key);
    }

    public boolean getDefaultValue() {
        return defaultValue;
    }

    public Text getButtonText() {
        return ScreenTexts.composeGenericOptionText(new TranslatableText(translationKey), getValue() ? enabledText : disabledText);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public CyclingOption<Boolean> asOption() {
        if (enabledText != null && disabledText != null) {
            return CyclingOption.create(translationKey, enabledText, disabledText, ignored -> ConfigOptionStorage.getBoolean(key), (ignored, option, value) -> ConfigOptionStorage.setBoolean(key, value));
        }
        return CyclingOption.create(translationKey, ignored -> ConfigOptionStorage.getBoolean(key), (ignored, option, value) -> ConfigOptionStorage.setBoolean(key, value));
    }
}
