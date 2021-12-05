package com.jab125.thonkutil.config.option;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.Option;

public interface OptionConvertable {
    @Environment(EnvType.CLIENT)
    Option asOption();
}