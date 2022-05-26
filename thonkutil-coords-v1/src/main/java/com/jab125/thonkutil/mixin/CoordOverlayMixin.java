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
package com.jab125.thonkutil.mixin;

import com.google.common.base.Strings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static net.minecraft.client.gui.DrawableHelper.fill;

@Mixin(InGameHud.class)
public abstract class CoordOverlayMixin {


    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    public abstract TextRenderer getTextRenderer();

    @Inject(method = "render", at = @At("TAIL"))
    private void injectCoords(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        if (this.client.options.debugEnabled) return;
        if (!false) return;
        thonkutil$renderCoords(matrices);
    }

    private void thonkutil$renderCoords(MatrixStack matrices) {
        this.client.getProfiler().push("coords");
        List<String> list = new ArrayList<>();

        //list.add("ThonkUtil " + FabricLoader.getInstance().getModContainer("thonkutil").get().getMetadata().getVersion().getFriendlyString());
        //list.add(this.client.fpsDebugString);

        if (FabricLoader.getInstance().isModLoaded("biomeinfo")) {
            assert MinecraftClient.getInstance().world != null;
            if (MinecraftClient.getInstance().world.isInBuildLimit(MinecraftClient.getInstance().getCameraEntity().getBlockPos()))
                list.add("");
        }
        if (FabricLoader.getInstance().isModLoaded("betterf3")) {
            list.add(String.format(Locale.ROOT, "§cX§aY§bZ§c: %.3f  §a%.5f  §b%.3f", this.client.getCameraEntity().getX(), this.client.getCameraEntity().getY(), this.client.getCameraEntity().getZ()));
        } else {
            list.add(String.format(Locale.ROOT, "XYZ: %.3f / %.5f / %.3f", this.client.getCameraEntity().getX(), this.client.getCameraEntity().getY(), this.client.getCameraEntity().getZ()));
        }
        //list.add(String.format("Render Distance: %s", this.client.options.viewDistance));

        for (int i = 0; i < list.size(); ++i) {
            String string = (String) list.get(i);
            if (!Strings.isNullOrEmpty(string)) {
                Objects.requireNonNull(this.getTextRenderer());
                int j = 9;
                int k = this.getTextRenderer().getWidth(string);
                int m = 2 + j * i;
                fill(matrices, 1, m - 1, 2 + k + 1, m + j - 1, -1873784752);

                if (FabricLoader.getInstance().isModLoaded("betterf3"))
                    this.getTextRenderer().drawWithShadow(matrices, string, 2.0F, (float) m, 14737632);
                else
                    this.getTextRenderer().draw(matrices, string, 2.0F, (float) m, 14737632);
            }
        }
        this.client.getProfiler().pop();
    }
}