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
package com.jab125.thonkutil.modchecker.v1.config;

import com.jab125.thonkutil.config.option.OptionConvertable;
import com.jab125.thonkutil.config.option.StringSetConfigOption;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.Option;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;

public class ThonkUtilModCheckerConfig {
    public static final StringSetConfigOption BLACKLISTED_MODS = new StringSetConfigOption("blacklisted_universal_mods", "thonkutil-modchecker-v1", new HashSet<>());

    @Environment(EnvType.CLIENT)
    public static Option[] asOptions() {
        ArrayList<Option> options = new ArrayList<>();
        for (Field field : ThonkUtilModCheckerConfig.class.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()) && OptionConvertable.class.isAssignableFrom(field.getType())) {
                try {
                    options.add(((OptionConvertable) field.get(null)).asOption());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return options.stream().toArray(Option[]::new);
    }
}
