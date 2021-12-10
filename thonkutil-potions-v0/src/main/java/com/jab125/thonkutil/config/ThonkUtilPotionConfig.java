package com.jab125.thonkutil.config;

import com.jab125.thonkutil.ThonkUtil;
import com.jab125.thonkutil.config.option.BooleanConfigOption;
import com.jab125.thonkutil.config.option.OptionConvertable;
import com.jab125.thonkutil.config.option.ToolTipBooleanConfigOption;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.Option;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ThonkUtilPotionConfig {
    //public static final BooleanConfigOption TRADE_OFFER_ID = new ToolTipBooleanConfigOption("trade_offer_id", ThonkUtil.MODID, true);
    //public static final BooleanConfigOption JOIN_GAME_MODLOADER = new BooleanConfigOption("join_game_modloader", false);

    @Environment(EnvType.CLIENT)
    public static Option[] asOptions() {
        ArrayList<Option> options = new ArrayList<>();
        for (Field field : ThonkUtilPotionConfig.class.getDeclaredFields()) {
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
