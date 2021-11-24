package com.jab125.thonkutil;

import com.google.gson.JsonElement;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
import com.jab125.thonkutil.trades.TradeManager;
import com.jab125.thonkutil.trades.type.BasicTrade;
import com.jab125.thonkutil.trades.type.SkeletonTrade;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Random;

public class ThonkUtil implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("thonkutil");

	@Override
	public void onInitialize() {
		TradeManager.instance().registerTypeSerializer(BasicTrade.SERIALIZER);
		TradeManager.instance().registerTypeSerializer(SkeletonTrade.SERIALIZER);
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		TradeOffers.PROFESSION_TO_LEVELED_TRADE.forEach((villagerProfession, int2ObjectMap) -> {
			int2ObjectMap.forEach(((integer, factories) -> {
				for (var i : factories) {
					try {
						LOGGER.info(toString(SkeletonTrade.SERIALIZER.fromTradeOffer(i.create(null, new Random())).serialize()));
					} catch (Exception ignored) {}
				}
			}));
		});
	}
	private String toString(JsonElement jsonElement) {
		try {
			StringWriter stringWriter = new StringWriter();
			JsonWriter jsonWriter = new JsonWriter(stringWriter);
			jsonWriter.setLenient(true);
			jsonWriter.setIndent(" ");
			Streams.write(jsonElement, jsonWriter);
			return stringWriter.toString();
		} catch (IOException e) {
			throw new AssertionError(e);
		}
	}
}
