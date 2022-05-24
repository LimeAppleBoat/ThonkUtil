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
package com.jab125.thonkutil.api;

import com.jab125.thonkutil.ThonkUtilCapesClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AnimatedCapeItem extends CapeItem {
    private final int frames;
    private final int delay;
    private long nextFrameTime;
    private boolean loadedT = false;
    private int currentFrame = 0;

    public AnimatedCapeItem(Settings settings, int frames, int delay) {
        this(settings, frames, delay, true, false);
    }

    public AnimatedCapeItem(Settings settings, int frames, int delay, boolean hasElytraTexture, boolean has2WingedElytra) {
        super(settings, hasElytraTexture, has2WingedElytra);
        this.frames = frames;
        this.delay = delay;
        this.setNextFrameTime(delay);
    }

    @Override
    public AnimatedCapeItem doNotAddToCreativeInventory() {
        super.doNotAddToCreativeInventory();
        return this;
    }

    public AnimatedCapeItem(Settings settings, int frames, int delay, boolean hasElytraTexture) {
        this(settings, frames, delay, hasElytraTexture, false);
    }

    @Deprecated
    @Environment(EnvType.CLIENT)
    public Identifier getCapeTexture0() {
        return Identifier.tryParse(Registry.ITEM.getId(this).getNamespace() + ":textures/cape/" + Registry.ITEM.getId(this).getPath() + ".png");
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Identifier getCapeTexture() {
        if (FabricLoader.getInstance().getEnvironmentType().equals(EnvType.CLIENT) && !loadedT) {
            ThonkUtilCapesClient.loadCapes(this);
            loadedT = true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis > nextFrameTime) {
            currentFrame = currentFrame == frames - 1 ? 0 : currentFrame + 1;
            setNextFrameTime(delay);
        }
        return new Identifier("thonkutil", String.format("capes/%s/%d", this.getRegistryIdAsIdentifier().toUnderscoreSeparatedString(), currentFrame));
        //return Identifier.tryParse(Registry.ITEM.getId(this).getNamespace() + ":textures/cape/"+ Registry.ITEM.getId(this).getPath() + "/" + currentFrame + ".png");
    }

    @Override
    @Environment(EnvType.CLIENT)
    public Identifier getElytraTexture() {
        return has2WingedElytra() ? Identifier.tryParse(Registry.ITEM.getId(this).getNamespace() + ":textures/elytra/" + Registry.ITEM.getId(this).getPath() + "/" + currentFrame + ".png") : getCapeTexture();
    }

    private void setNextFrameTime(long nextFrameTime) {
        this.nextFrameTime = nextFrameTime + System.currentTimeMillis();
    }
}
