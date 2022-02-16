package com.jab125.thonkutil.titlescreen.v1.config;

import com.jab125.thonkutil.config.option.BooleanConfigOption;
import com.jab125.thonkutil.config.option.OptionConvertable;
import com.jab125.thonkutil.config.option.ToolTipBooleanConfigOption;
import com.jab125.thonkutil.titlescreen.v1.ThonkUtilTitleScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.Option;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ThonkUtilTitleScreenConfig {
    public static final BooleanConfigOption MODIFY_TITLE_SCREEN = new ToolTipBooleanConfigOption("modify_title_screen", ThonkUtilTitleScreen.modId(), false);

    @Environment(EnvType.CLIENT)
    public static Option[] asOptions() {
        ArrayList<Option> options = new ArrayList<>();
        for (Field field : ThonkUtilTitleScreenConfig.class.getDeclaredFields()) {
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
