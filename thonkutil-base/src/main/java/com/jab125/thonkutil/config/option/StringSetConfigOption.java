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
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.Set;

public class StringSetConfigOption implements ThonkUtilBaseClass {
    private final String key, translationKey;
    private final Set<String> defaultValue;

    public StringSetConfigOption(String key, String modid, Set<String> defaultValue) {
        super();
        ConfigOptionStorage.setStringSet(key, defaultValue);
        this.key = key;
        this.translationKey = TranslationUtil.translationKeyOf("option", key, modid);
        this.defaultValue = defaultValue;
    }

    public String getKey() {
        return key;
    }

    public Set<String> getValue() {
        return ConfigOptionStorage.getStringSet(key);
    }

    public void setValue(Set<String> value) {
        ConfigOptionStorage.setStringSet(key, value);
    }

    public Text getMessage() {
        return new TranslatableText(translationKey);
    }

    public Set<String> getDefaultValue() {
        return defaultValue;
    }
}