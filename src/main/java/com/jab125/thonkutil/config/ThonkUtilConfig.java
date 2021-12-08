package com.jab125.thonkutil.config;

import com.google.gson.annotations.SerializedName;
import com.jab125.thonkutil.ThonkUtil;
import com.jab125.thonkutil.config.option.BooleanConfigOption;
import com.jab125.thonkutil.config.option.OptionConvertable;
import com.jab125.thonkutil.config.option.SliderConfigOption;
import com.jab125.thonkutil.config.option.ToolTipBooleanConfigOption;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Option;
import net.minecraft.server.dedicated.ServerPropertiesLoader;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;

public class ThonkUtilConfig {
    public static final BooleanConfigOption TRADE_OFFER_ID = new ToolTipBooleanConfigOption("trade_offer_id", ThonkUtil.MODID, true);
    public static final BooleanConfigOption SHOW_COORDS = new ToolTipBooleanConfigOption("show_coords", ThonkUtil.MODID, false);
    public static final SliderConfigOption MAXIMUM_RENDER_DISTANCE = new SliderConfigOption("maximum_render_distance", ThonkUtil.MODID, MinecraftClient.getInstance().is64Bit() && Runtime.getRuntime().maxMemory() >= 1000000000L ? 32.0 : 16.0, 16.0, 128.0, 1);
    //public static final BooleanConfigOption JOIN_GAME_MODLOADER = new BooleanConfigOption("join_game_modloader", false);

    @Environment(EnvType.CLIENT)
    public static Option[] asOptions() {
        ArrayList<Option> options = new ArrayList<>();
        for (Field field : ThonkUtilConfig.class.getDeclaredFields()) {
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