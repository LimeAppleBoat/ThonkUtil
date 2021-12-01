package com.jab125.thonkutil;

import com.jab125.thonkutil.api.RemoveTippedPotionRecipe;
import com.jab125.thonkutil.api.SkipPotion;
import net.fabricmc.api.ModInitializer;
import net.minecraft.potion.Potions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThonkUtil implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("thonkutil");

	@Override
	public void onInitialize() {
		SkipPotion.skipPotion(Potions.HARMING, SkipPotion.POTION);
		SkipPotion.skipPotion(Potions.FIRE_RESISTANCE, SkipPotion.TIPPED_ARROW);
		RemoveTippedPotionRecipe.remove(Potions.HEALING);
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
	}
}
