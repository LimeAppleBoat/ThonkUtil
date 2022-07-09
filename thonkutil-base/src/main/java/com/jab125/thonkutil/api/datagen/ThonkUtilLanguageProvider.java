/*
 * Copyright (c) 2021, 2022 Jab125 & LimeAppleBoat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jab125.thonkutil.api.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.DataCache;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.GameRules;
import org.apache.commons.lang3.text.translate.JavaUnicodeEscaper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.TreeMap;

public abstract class ThonkUtilLanguageProvider implements DataProvider {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private final FabricDataGenerator fabricDataGenerator;
    private final String locale;
    private final TreeMap<String, String> data = new TreeMap<>();

    public ThonkUtilLanguageProvider(FabricDataGenerator fabricDataGenerator, String locale) {
        this.fabricDataGenerator = fabricDataGenerator;
        this.locale = locale;
    }

    protected String getLocale() {
        return locale;
    }

    protected String getModId() {
        return fabricDataGenerator.getModId();
    }

    @Override
    public void run(DataWriter writer) throws IOException {
        addTranslations();
        @SuppressWarnings("deprecation")
        String rawJson = JavaUnicodeEscaper.outsideOf(0, 0x7f).translate(GSON.toJson(data));
        Path path = this.fabricDataGenerator.getOutput().resolve("assets/" + this.fabricDataGenerator.getModId() + "/lang/" + locale + ".json");
        var debug = false;
        if (debug) {
            System.out.println("JSON: " + rawJson);
            System.out.println("Path: " + path);
        }
        DataProvider.writeToPath(writer, GSON.toJsonTree(data), path);
    }

    @Override
    public String getName() {
        return null;
    }

    protected abstract void addTranslations();

    public void add(Block key, String name) {
        add(key.getTranslationKey(), name);
    }

    public void add(Item key, String name) {
        add(key.getTranslationKey(), name);
    }

    public void add(ItemStack key, String name) {
        add(key.getTranslationKey(), name);
    }

    public void add(Enchantment key, String name) {
        add(key.getTranslationKey(), name);
    }

    public void add(StatusEffect key, String name) {
        add(key.getTranslationKey(), name);
    }

    public void add(EntityType<?> key, String name) {
        add(key.getTranslationKey(), name);
    }

    public void add(String key, String value) {
        if (data.put(key, value) != null)
            throw new IllegalStateException("Duplicate translation key " + key);
    }

    public void add(GameRules.Key<?> gameruleId, String translation) {
        add(gameruleId.getTranslationKey(), translation);
    }

    // For English Only
    public void addPotionSet(Potion potion, String translation) {
        add("item.minecraft.potion.effect." + getPotion0(potion), "Potion of " + translation);
        add("item.minecraft.splash_potion.effect." + getPotion0(potion), "Splash Potion of " + translation);
        add("item.minecraft.tipped_arrow.effect." + Registry.POTION.getId(potion).getPath(), "Arrow of " + translation);
    }

    public void addAutoConfigTitle(String translation) {
        addMisc("title", translation, "text.autoconfig");
    }

    public void addAutoConfigCategory(String categoryId, String translation) {
        String part1 = categoryId.split(":").length == 2 ? categoryId.split(":")[0] : fabricDataGenerator.getModId();
        String part2 = categoryId.split(":").length == 2 ? categoryId.split(":")[1] : categoryId;
        add("text.autoconfig." + part1 + ".category." + part2, translation);
    }

    public void addAutoConfigOption(String optionId, String translation) {
        String part1 = optionId.split(":").length == 2 ? optionId.split(":")[0] : fabricDataGenerator.getModId();
        String part2 = optionId.split(":").length == 2 ? optionId.split(":")[1] : optionId;
        add("text.autoconfig." + part1 + ".option." + part2, translation);
    }

    public void addRequiemTooltip(String tooltipId, String translation) {
        String part1 = tooltipId.split(":").length == 2 ? tooltipId.split(":")[0] : fabricDataGenerator.getModId();
        String part2 = tooltipId.split(":").length == 2 ? tooltipId.split(":")[1] : tooltipId;
        add(part1 + ":tooltip." + part2, translation);
    }

    private void addMisc(String id, String translation, String miscId) {
        if (id.split(":").length == 2) {
            add(miscId + "." + id.split(":")[0] + "." + id.split(":")[1], translation);
            return;
        }
        add(miscId + "." + fabricDataGenerator.getModId() + "." + id, translation);
    }

    private String getPotion0(Potion potion) {
        return (potion.baseName == null ? Registry.POTION.getId(potion).getPath() : potion.baseName);
    }
}
