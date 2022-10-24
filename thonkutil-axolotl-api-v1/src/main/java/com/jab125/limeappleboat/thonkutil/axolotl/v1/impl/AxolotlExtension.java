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
package com.jab125.limeappleboat.thonkutil.axolotl.v1.impl;

import com.jab125.limeappleboat.thonkutil.enumapi.v1.Helpers;
import net.minecraft.entity.passive.AxolotlEntity;

public class    AxolotlExtension implements Runnable {
    @Override
    public void run() {
      //  AxolotlEntity.Variant
        Helpers.transformClass("net.minecraft.class_5762$class_5767", "field_28350", "com.jab125.limeappleboat.thonkutil.axolotl.v1.impl.AxolotlCreatorImpl", "(Ljava/lang/String;IILjava/lang/String;Z)V");
      //  Helpers.transformClass("net.minecraft.class_1799$class_5422", "field_25776", "com.jab125.limeappleboat.thonkutil.enumapi.v1.api.TooltipSectionAdder", "(Ljava/lang/String;I)V");
    }
}
