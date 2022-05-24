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
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class BooleanConfigOption implements OptionConvertable, ThonkUtilBaseClass {
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
