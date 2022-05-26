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
package com.jab125.thonkutil;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jab125.thonkutil.api.annotations.SubscribeEvent;
import com.jab125.thonkutil.api.events.EventTaxi;
import com.jab125.thonkutil.api.events.client.EventTaxiClient;
import com.jab125.thonkutil.api.events.client.screen.TitleScreenRenderEvent;
import com.jab125.thonkutil.api.events.server.entity.TotemUseEvent;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@EnvironmentInterface(value = EnvType.CLIENT, itf = ClientModInitializer.class)
public class ThonkUtil implements ThonkUtilBaseClass, ModInitializer, ClientModInitializer {
    public static final Identifier TOTEM_PACKET = new Identifier("thonkutil:totem_particle_packet");
    public static final Logger LOGGER = LogManager.getLogger("thonkutil");
    private static final State state = State.ALPHA;
    public static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();

    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        EventTaxi.registerTaxis();
        EventTaxi.registerEventTaxiSubscriber(ThonkUtil.class);
    }

    public static State getState() {
        return state;
    }

    private static enum State {
        PRE_ALPHA("pre_alpha", Formatting.WHITE, "thonkutil.nosupport"),
        ALPHA("alpha", Formatting.WHITE),
        BETA("beta", Formatting.YELLOW),
        RELEASE("release", Formatting.values()[(int) (Math.random() * (double) Formatting.values().length)]);

        private final String id;
        private final Formatting formatting;
        private final String translationKeyDesc;

        State(String id, Formatting formatting) {
            this.id = id;
            this.formatting = formatting;
            this.translationKeyDesc = "thonkutil.warning.2";
        }

        State(String id, Formatting formatting, String translationKeyDesc) {
            this.id = id;
            this.formatting = formatting;
            this.translationKeyDesc = translationKeyDesc;
        }

        @Override
        public String toString() {
            return id;
        }
    }


    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        EventTaxiClient.registerClientTaxis();
        ClientPlayNetworking.registerGlobalReceiver(TOTEM_PACKET,
                (client, handler, buf, responseSender) -> {
                    ItemStack itemStack = buf.readItemStack();
                    assert client.world != null;
                    Entity entity = client.world.getEntityById(buf.readInt());
                    client.execute(() -> TotemUseEvent.playActivateAnimation(entity, itemStack));
                });
    }

    public static class MODID {
        public static final String POTIONS_V0_MODID = "thonkutil-potions-v0";
        public static final String COORDS_V1_MODID = "thonkutil-coords-v1";
        public static final String TRADES_V1_MODID = "thonkutil-trades-v1";
        public static final String CUSTOMIZATION_V1_MODID = "thonkutil-customization-v1";
        public static final String BASE_MODID = "thonkutil-base";
    }

    static {
        secretComment("i");
        secretComment("don't");
        secretComment("support");
        secretComment("optifine");
    }

    @SuppressWarnings("unused")
    private static void secretComment(Object object) {
        var q = object;
    }

    @SubscribeEvent
    @Environment(EnvType.CLIENT)
    public static void showMessage(TitleScreenRenderEvent event) {
        if (state.equals(State.RELEASE)) return;
        Text text = Text.literal("").append(Text.translatable("thonkutil.warning.1").formatted(Formatting.RED)).append(Text.translatable("thonkutil." + state).formatted(state.formatting)).append(Text.translatable("thonkutil.warning.1.1").formatted(Formatting.RED));
        DrawableHelper.drawCenteredText(event.matrices(), MinecraftClient.getInstance().textRenderer, text, event.screen().width / 2, 4 + (0 * (MinecraftClient.getInstance().textRenderer.fontHeight + 1)), 0xFFFFFF | event.alpha());

        text = Text.translatable(state.translationKeyDesc);
        DrawableHelper.drawCenteredText(event.matrices(), MinecraftClient.getInstance().textRenderer, text, event.screen().width / 2, 4 + (1 * (MinecraftClient.getInstance().textRenderer.fontHeight + 1)), 0xFFFFFF | event.alpha());

        text = Text.translatable("thonkutil.warning.3");
        DrawableHelper.drawCenteredText(event.matrices(), MinecraftClient.getInstance().textRenderer, text, event.screen().width / 2, 4 + (2 * (MinecraftClient.getInstance().textRenderer.fontHeight + 1)), 0xFFFFFF | event.alpha());

    }
}
