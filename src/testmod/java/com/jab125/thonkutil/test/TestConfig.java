package com.jab125.thonkutil.test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.jab125.thonkutil.config.option.*;
import net.minecraft.client.option.Option;
import net.minecraft.entity.Entity;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;

public class TestConfig {
    public static final BooleanConfigOption A_BOOLEAN = new ToolTipBooleanConfigOption("boolean", Constants.MODID, true);
    public static final SliderConfigOption A_SLIDER = new SliderConfigOption("slider", Constants.MODID, 50D, 0, 100, 1);
    public static final EnumConfigOption<Entity.RemovalReason> ENUM = new EnumConfigOption<>("enum", Constants.MODID, Entity.RemovalReason.DISCARDED);
    public static final StringSetConfigOption STRING_SET = new StringSetConfigOption("set", Constants.MODID, new HashSet<>());

    public static Option[] asOptions() {
        ArrayList<Option> options = new ArrayList<>();
        for (Field field : TestConfig.class.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()) && OptionConvertable.class.isAssignableFrom(field.getType()) && !field.getName().equals("HIDE_CONFIG_BUTTONS") && !field.getName().equals("MODIFY_TITLE_SCREEN") && !field.getName().equals("MODIFY_GAME_MENU")) {
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