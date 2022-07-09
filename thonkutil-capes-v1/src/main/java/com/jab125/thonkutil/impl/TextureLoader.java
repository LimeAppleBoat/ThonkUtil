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
package com.jab125.thonkutil.impl;

import com.jab125.thonkutil.api.AnimatedCapeItem;
import com.jab125.thonkutil.api.CapeItem;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.io.InputStream;

@Environment(EnvType.CLIENT)
public class TextureLoader {
    public static void apply2WingedElytra(CapeItem cape) {
        try {
            InputStream texture = MinecraftClient.getInstance().getResourceManager().getResource(cape.getElytraTexture0()).orElseThrow().getInputStream();
            NativeImage elytra = NativeImage.read(texture);
            Int2ObjectMap<NativeImage> elytraTextures = new Int2ObjectOpenHashMap<>();
            int imageHeight = elytra.getHeight() / (elytra.getWidth() / 2);
            imageHeight = 2;

            for (var currentFrame = 0; currentFrame < imageHeight; ++currentFrame) {
                NativeImage frame = new NativeImage(elytra.getWidth(), elytra.getWidth() / 2, true);

                for (var x = 0; x < frame.getWidth(); ++x) {
                    for (int y = 0; y < frame.getHeight(); ++y) {
                        frame.setColor(x, y, elytra.getColor(x, y + currentFrame * (elytra.getWidth() / 2)));
                    }
                }

                elytraTextures.put(currentFrame, frame);
            }

            TextureLoader.loadElytraFramesToResource(elytraTextures, cape.getRegistryIdAsIdentifier().toUnderscoreSeparatedString());

        } catch (Exception ignored) {
        }
    }

    public static void applyAnimatedCape(AnimatedCapeItem cape) {
        try {
            InputStream texture = MinecraftClient.getInstance().getResourceManager().getResource(cape.getCapeTexture0()).orElseThrow().getInputStream();
            NativeImage capeImage = NativeImage.read(texture);
            int imageHeight;
            int currentFrame;
            int x;
            Int2ObjectMap<NativeImage> animatedCapeFrames = new Int2ObjectOpenHashMap<>();
            imageHeight = capeImage.getHeight() / (capeImage.getWidth() / 2);

            for (currentFrame = 0; currentFrame < imageHeight; ++currentFrame) {
                NativeImage frame = new NativeImage(capeImage.getWidth(), capeImage.getWidth() / 2, true);

                for (x = 0; x < frame.getWidth(); ++x) {
                    for (int y = 0; y < frame.getHeight(); ++y) {
                        frame.setColor(x, y, capeImage.getColor(x, y + currentFrame * (capeImage.getWidth() / 2)));
                    }
                }

                animatedCapeFrames.put(currentFrame, frame);
            }

            TextureLoader.loadFramesToResource(animatedCapeFrames, cape.getRegistryIdAsIdentifier().toUnderscoreSeparatedString());
        } catch (IOException exception) {
            System.out.println("ERROR");
        }
    }

    private static void loadFramesToResource(Int2ObjectMap<NativeImage> cape, String itemId) {
        cape.forEach((integer, nativeImage) -> {
            Identifier identifier = new Identifier("thonkutil", String.format("capes/%s/%d", itemId, integer));
            //System.out.println(identifier);
            TextureLoader.applyTexture(identifier, nativeImage);
        });
    }

    private static void loadElytraFramesToResource(Int2ObjectMap<NativeImage> cape, String itemId) {
        cape.forEach((integer, nativeImage) -> {
            Identifier identifier = new Identifier("thonkutil", String.format("elytra/%s/%d", itemId, integer));
            //System.out.println(identifier);
            TextureLoader.applyTexture(identifier, nativeImage);
        });
    }

    private static void applyTexture(Identifier identifier, NativeImage image) {
        MinecraftClient.getInstance().execute(() -> {
            MinecraftClient.getInstance().getTextureManager().registerTexture(identifier, new NativeImageBackedTexture(image));
        });
    }
}
