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
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.Option;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.Locale;

public class EnumConfigOption<E extends Enum<E>> implements OptionConvertable, ThonkUtilBaseClass {
    private final String key, translationKey;
    private final Class<E> enumClass;
    private final E defaultValue;

    public EnumConfigOption(String key, String modid, E defaultValue) {
        ConfigOptionStorage.setEnum(key, defaultValue);
        this.key = key;
        this.translationKey = TranslationUtil.translationKeyOf("option", key, modid);
        this.enumClass = defaultValue.getDeclaringClass();
        this.defaultValue = defaultValue;
    }

    public String getKey() {
        return key;
    }

    public E getValue() {
        return ConfigOptionStorage.getEnum(key, enumClass);
    }

    public void setValue(E value) {
        ConfigOptionStorage.setEnum(key, value);
    }

    public void cycleValue() {
        ConfigOptionStorage.cycleEnum(key, enumClass);
    }

    public void cycleValue(int amount) {
        ConfigOptionStorage.cycleEnum(key, enumClass, amount);
    }

    public E getDefaultValue() {
        return defaultValue;
    }

    private static <E extends Enum<E>> Text getValueText(EnumConfigOption<E> option, E value) {
        return new TranslatableText(option.translationKey + "." + value.name().toLowerCase(Locale.ROOT));
    }

    public Text getButtonText() {
        return ScreenTexts.composeGenericOptionText(new TranslatableText(translationKey), getValueText(this, getValue()));
    }

    @Override
    @Environment(EnvType.CLIENT)
    public Option asOption() {
        return CyclingOption.create(translationKey, enumClass.getEnumConstants(), value -> getValueText(this, value), ignored -> ConfigOptionStorage.getEnum(key, enumClass), (ignored, option, value) -> ConfigOptionStorage.setEnum(key, value));
    }
}