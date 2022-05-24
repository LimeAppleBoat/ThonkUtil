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
package com.jab125.thonkutil;

import com.jab125.thonkutil.gui.ThonkUtilOptionsScreen;
import com.jab125.thonkutil.gui.ThonkUtilTradesOptionsScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.text.LiteralText;

public class ThonkUtilTradesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ThonkUtilOptionsScreen.optionList.add(ThonkUtilOptionsScreen.createOption(new LiteralText("Trades"), new ThonkUtilTradesOptionsScreen(null)));
    }
}
