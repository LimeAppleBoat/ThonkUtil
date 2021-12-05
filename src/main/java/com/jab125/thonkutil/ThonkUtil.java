package com.jab125.thonkutil;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jab125.thonkutil.api.tradeoffer.IdentifiableTrade;
import com.jab125.thonkutil.config.ThonkUtilConfig;
import com.jab125.thonkutil.config.ThonkUtilConfigManager;
import com.jab125.thonkutil.impl.RemovePotionRecipeImpl;
import com.mojang.authlib.exceptions.MinecraftClientHttpException;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.Potions;
import net.minecraft.village.TradeOffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThonkUtil implements ModInitializer {
	public static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MODID = "thonkutil";
	public static final Logger LOGGER = LogManager.getLogger("thonkutil");

	@Override
	public void onInitialize() {
		ThonkUtilConfigManager.initializeConfig();
		System.out.println("POTION API CONFIG:" + ThonkUtilConfig.POTION_API.getValue());
		System.out.println("TRADE OFFER CONFIG:" + ThonkUtilConfig.TRADE_OFFER_ID.getValue());
		System.out.println(FabricLoader.getInstance().getModContainer(MODID).get().getMetadata().getName() + " Initialized");
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
	}
}
