/*
 * Copyright (c) 2021, 2022 Jab125 & LimeAppleBoat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jab125.thonkutil.config.option;

import com.jab125.thonkutil.ThonkUtilBaseClass;
import com.jab125.thonkutil.util.translation.TranslationUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.DoubleOption;
import net.minecraft.client.option.Option;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class SliderConfigOption implements OptionConvertable, ThonkUtilBaseClass {

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
        if (((float) (int) option.step) - option.step == 0)
            return new TranslatableText(option.translationKey, (int) value);
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
