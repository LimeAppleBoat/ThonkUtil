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
package com.jab125.thonkutil.config;

import com.jab125.thonkutil.ThonkUtil;
import com.jab125.thonkutil.config.option.BooleanConfigOption;
import com.jab125.thonkutil.config.option.OptionConvertable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.Option;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ThonkUtilCustomizationConfig {
    //public static final BooleanConfigOption TRADE_OFFER_ID = new ToolTipBooleanConfigOption("trade_offer_id", ThonkUtil.MODID, true);
    //public static final BooleanConfigOption JOIN_GAME_MODLOADER = new BooleanConfigOption("join_game_modloader", false);
    public static final BooleanConfigOption LEAD_VILLAGERS = new BooleanConfigOption("lead_villagers", ThonkUtil.MODID.CUSTOMIZATION_V1_MODID, false);
    public static final BooleanConfigOption LEAD_WANDERING_TRADERS = new BooleanConfigOption("lead_wandering_traders", ThonkUtil.MODID.CUSTOMIZATION_V1_MODID, false);

    @Environment(EnvType.CLIENT)
    public static Option[] asOptions() {
        ArrayList<Option> options = new ArrayList<>();
        for (Field field : ThonkUtilCustomizationConfig.class.getDeclaredFields()) {
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
