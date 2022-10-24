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
package com.jab125.limeappleboat.thonkutil.enumapi.v1;

import com.jab125.limeappleboat.thonkutil.enumapi.v1.api.EnumExtender;
import com.jab125.limeappleboat.thonkutil.enumapi.v1.api.MethodName;
import net.fabricmc.loader.api.FabricLoader;

import java.util.List;

public class Helpers {
    public static String map(String clazz, boolean slash) {
        var a = FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", clazz.replaceAll("/", "."));
        //  System.out.println(WordUtils.capitalize(FabricLoader.getInstance().getMappingResolver().getCurrentRuntimeNamespace()) + " name: " + a);
        return slash ? a.replaceAll("\\.", "/") : a;
    }

    public static String mapField(String clazz, String field, String desc) {
        if (desc == null) {
            desc = "[L" + clazz.replaceAll("/", ".") + ";";
        }
        desc = desc.replaceAll("\\.", "/");
        System.out.println(clazz.replaceAll("/", ".") + ", " + field + ", " + desc);
        var a = FabricLoader.getInstance().getMappingResolver().mapFieldName("intermediary", clazz.replaceAll("/", "."), field, desc);
        //  System.out.println(WordUtils.capitalize(FabricLoader.getInstance().getMappingResolver().getCurrentRuntimeNamespace()) + " name: " + a);
        //System.out.println(a);
        return a;
    }

    public static String map(String clazz) {
        return map(clazz, false);
    }

    public static void transformClass(String enumClass, String valuesField, String surrogateClass, String desc, List<MethodName> names) {
        new EnumExtender(map(enumClass).replaceAll("/", "."), valuesField, surrogateClass, desc, names).register();
    }

    public static void transformClass(String enumClass, String valuesField, String surrogateClass, String desc) {
        new EnumExtender(map(enumClass).replaceAll("/", "."), mapField(enumClass, valuesField, null), surrogateClass, desc).register();
    }
}
