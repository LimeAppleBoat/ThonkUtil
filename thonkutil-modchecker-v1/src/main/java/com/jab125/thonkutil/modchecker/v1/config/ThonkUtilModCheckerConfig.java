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
