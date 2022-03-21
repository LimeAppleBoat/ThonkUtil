package com.jab125.thonkutil.config.option;

import com.jab125.thonkutil.ThonkUtilBaseClass;
import com.jab125.thonkutil.util.PingMap;

import java.util.Map;
import java.util.Set;

public class ConfigOptionStorage implements ThonkUtilBaseClass {
    private static final Map<String, Boolean> BOOLEAN_OPTIONS = new PingMap<String, Boolean>();
    private static final Map<String, Enum<?>> ENUM_OPTIONS = new PingMap<String, Enum<?>>();
    private static final Map<String, Double> DOUBLE_OPTIONS = new PingMap<String, Double>();
    private static final Map<String, Set<String>> STRING_SET_OPTIONS = new PingMap<String, Set<String>>();

    public static void setStringSet(String key, Set<String> value) {
        STRING_SET_OPTIONS.put(key, value);
    }

    public static Set<String> getStringSet(String key) {
        return STRING_SET_OPTIONS.get(key);
    }

    public static void setBoolean(String key, boolean value) {
        BOOLEAN_OPTIONS.put(key, value);
    }

    public static void toggleBoolean(String key) {
        setBoolean(key, !getBoolean(key));
    }

    public static boolean getBoolean(String key) {
        return BOOLEAN_OPTIONS.get(key);
    }

    public static double getDouble(String key) {
        return DOUBLE_OPTIONS.get(key);
    }

    public static boolean containsDouble(String key) {
        return DOUBLE_OPTIONS.containsKey(key);
    }

    public static void setDouble(String key, double value) {
        DOUBLE_OPTIONS.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public static <E extends Enum<E>> E getEnum(String key, Class<E> typeClass) {
        return (E) ENUM_OPTIONS.get(key);
    }

    public static Enum<?> getEnumTypeless(String key, Class<Enum<?>> typeClass) {
        return ENUM_OPTIONS.get(key);
    }

    public static <E extends Enum<E>> void setEnum(String key, E value) {
        ENUM_OPTIONS.put(key, value);
    }

    public static void setEnumTypeless(String key, Enum<?> value) {
        ENUM_OPTIONS.put(key, value);
    }

    public static <E extends Enum<E>> E cycleEnum(String key, Class<E> typeClass) {
        return cycleEnum(key, typeClass, 1);
    }


    public static <E extends Enum<E>> E cycleEnum(String key, Class<E> typeClass, int amount) {
        E[] values = typeClass.getEnumConstants();
        E currentValue = getEnum(key, typeClass);
        E newValue = values[(currentValue.ordinal() + amount) % values.length];
        setEnum(key, newValue);
        return newValue;
    }
}
