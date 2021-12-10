package com.jab125.thonkutil.config.option;

import com.jab125.thonkutil.util.translation.TranslationUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.DoubleOption;
import net.minecraft.client.option.Option;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class SliderConfigOption implements OptionConvertable {

    private final String key;
    private final String translationKey;
    //private final Object enumClass;
    private final double defaultValue;
    private final double min;
    private final double max;
    private final float step;


    public SliderConfigOption(String key, String modid, Double defaultValue, double min, double max, float step) {
        this.key = key;
        this.translationKey = TranslationUtil.translationKeyOf("option", key, modid);
        this.defaultValue = defaultValue;
        this.min = min;
        this.max = max;
        this.step = step;
        ConfigOptionStorage.setDouble(key, this.defaultValue);
    }

    protected static Text getValueText(SliderConfigOption option, double value) {
        if (((float)(int) option.step) - option.step == 0) return new TranslatableText(option.translationKey, (int)value);
        return new TranslatableText(option.translationKey, value);
    }


    public String getKey() {
        return key;
    }

    public double getValue() {
        return ConfigOptionStorage.getDouble(key);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Option asOption() {
        return new DoubleOption(translationKey, min, max, step, ignored -> ConfigOptionStorage.getDouble(key), ((ignored, value) -> ConfigOptionStorage.setDouble(key, value)), ((gameOptions, doubleOption) -> getValueText(this, ConfigOptionStorage.getDouble(key))));
    }
}
