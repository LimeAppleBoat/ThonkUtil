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
import com.jab125.limeappleboat.thonkutil.enumapi.v1.api.EnumExtender;
import com.jab125.limeappleboat.thonkutil.enumapi.v1.api.MethodName;
import net.fabricmc.loader.api.FabricLoader;

import java.util.List;

public class Builtins implements Runnable {
    @Override
    public void run() {
        transformClass("net.minecraft.class_1690$class_1692", "field_7724", "com.jab125.limeappleboat.thonkutil.enumapi.v1.api.BoatTypeAdder", "(Ljava/lang/String;IL" + map("net/minecraft/class_2248", true) + ";Ljava/lang/String;)V");
        transformClass("net.minecraft.class_1799$class_5422", "field_25776", "com.jab125.limeappleboat.thonkutil.enumapi.v1.api.TooltipSectionAdder", "(Ljava/lang/String;I)V");
        List<MethodName> names = ImmutableList.of(new MethodName("method_8177", "(Lnet/minecraft/class_1792;)Z", "thonkutil$isAcceptableItem"));
        transformClass("net.minecraft.class_1886", "field_9077", "com.jab125.limeappleboat.thonkutil.enumapi.v1.api.EnchantmentTargetAdder", "(Ljava/lang/String;I)V", names);
        transformClass("net.minecraft.class_1267", "field_5804", "com.jab125.limeappleboat.thonkutil.enumapi.v1.api.DifficultyCreator", "(Ljava/lang/String;IILjava/lang/String;)V");
        transformClass("com.terraformersmc.modmenu.util.mod.Mod$Badge", "$VALUES", "com.jab125.limeappleboat.thonkutil.enumapi.v1.api.ModBadgeCreator", "(Ljava/lang/String;ILjava/lang/String;IILjava/lang/String;)V");
        transformClass("net.minecraft.class_1814", "field_8905", "com.jab125.limeappleboat.thonkutil.enumapi.v1.api.RarityCreator", "(Ljava/lang/String;IL" + map("net/minecraft/class_124", true) + ";)V");
    }

    private static String map(String clazz) {
        return map(clazz, false);
    }

    private static String map(String clazz, boolean slash) {
        var a = FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", clazz.replaceAll("/", "."));
        //  System.out.println(WordUtils.capitalize(FabricLoader.getInstance().getMappingResolver().getCurrentRuntimeNamespace()) + " name: " + a);
        return slash ? a.replaceAll("\\.", "/") : a;
    }

    private void transformClass(String enumClass, String valuesField, String surrogateClass, String desc, List<MethodName> names) {
        new EnumExtender(map(enumClass).replaceAll("/", "."), valuesField, surrogateClass, desc, names).register();
    }

    private void transformClass(String enumClass, String valuesField, String surrogateClass, String desc) {
        new EnumExtender(map(enumClass).replaceAll("/", "."), valuesField, surrogateClass, desc).register();
    }
}
