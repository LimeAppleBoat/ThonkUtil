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
package com.jab125.limeappleboat.thonkutil.enumapi.v1.impl;

import com.google.common.collect.ImmutableList;
import com.jab125.limeappleboat.thonkutil.enumapi.v1.Helpers;
import com.jab125.limeappleboat.thonkutil.enumapi.v1.api.MethodName;

import java.util.List;

public class Builtins implements Runnable {
    @Override
    public void run() {
        Helpers.transformClass("net.minecraft.class_1690$class_1692", "field_7724", "com.jab125.limeappleboat.thonkutil.enumapi.v1.api.creator.BoatTypeCreator", "(Ljava/lang/String;IL" + Helpers.map("net/minecraft/class_2248", true) + ";Ljava/lang/String;)V");
        Helpers.transformClass("net.minecraft.class_1799$class_5422", "field_25776", "com.jab125.limeappleboat.thonkutil.enumapi.v1.api.creator.TooltipSectionCreator", "(Ljava/lang/String;I)V");
        List<MethodName> names = ImmutableList.of(new MethodName("method_8177", "(Lnet/minecraft/class_1792;)Z", "thonkutil$isAcceptableItem"));
        Helpers.transformClass("net.minecraft.class_1886", "field_9077", "com.jab125.limeappleboat.thonkutil.enumapi.v1.api.creator.EnchantmentTargetCreator", "(Ljava/lang/String;I)V", names);
        Helpers.transformClass("net.minecraft.class_1267", "field_5804", "com.jab125.limeappleboat.thonkutil.enumapi.v1.api.creator.DifficultyCreator", "(Ljava/lang/String;IILjava/lang/String;)V");
        Helpers.transformClass("com.terraformersmc.modmenu.util.mod.Mod$Badge", "$VALUES", "com.jab125.limeappleboat.thonkutil.enumapi.v1.api.creator.ModBadgeCreator", "(Ljava/lang/String;ILjava/lang/String;IILjava/lang/String;)V");
        Helpers.transformClass("net.minecraft.class_1814", "field_8905", "com.jab125.limeappleboat.thonkutil.enumapi.v1.api.RarityCreator", "(Ljava/lang/String;IL" + Helpers.map("net/minecraft/class_124", true) + ";)V");
    }

}
