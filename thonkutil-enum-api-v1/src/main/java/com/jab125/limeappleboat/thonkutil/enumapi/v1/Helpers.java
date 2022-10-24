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
