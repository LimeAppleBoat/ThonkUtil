package com.jab125.thonkutil;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jab125.thonkutil.api.annotations.SubscribeEvent;
import com.jab125.thonkutil.api.events.*;
import com.jab125.thonkutil.api.events.client.EventTaxiClient;
import com.jab125.thonkutil.api.events.client.screen.TitleScreenRenderEvent;
import com.jab125.thonkutil.api.events.server.entity.TotemUseEvent;
import com.jab125.thonkutil.api.item.PotionableItem;
import com.jab125.thonkutil.util.AccessUtil;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

import static com.jab125.thonkutil.api.events.EventTaxi.registerEventTaxiSubscriber;

@EnvironmentInterface(value = EnvType.CLIENT, itf = ClientModInitializer.class)
public class ThonkUtil implements ThonkUtilBaseClass, ModInitializer, ClientModInitializer {
    public static final Identifier TOTEM_PACKET = new Identifier("thonkutil:totem_particle_packet");
    public static final Logger LOGGER = LogManager.getLogger("thonkutil");
    private static final State state = State.RELEASE;
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
        Text text = new LiteralText("").append(new TranslatableText("thonkutil.warning.1").formatted(Formatting.RED)).append(new TranslatableText("thonkutil." + state).formatted(state.formatting)).append(new TranslatableText("thonkutil.warning.1.1").formatted(Formatting.RED));
        DrawableHelper.drawCenteredText(event.matrices(), MinecraftClient.getInstance().textRenderer, text, event.screen().width / 2, 4 + (0 * (MinecraftClient.getInstance().textRenderer.fontHeight + 1)), 0xFFFFFF | event.alpha());

        text = new TranslatableText(state.translationKeyDesc);
        DrawableHelper.drawCenteredText(event.matrices(), MinecraftClient.getInstance().textRenderer, text, event.screen().width / 2, 4 + (1 * (MinecraftClient.getInstance().textRenderer.fontHeight + 1)), 0xFFFFFF | event.alpha());

        text = new TranslatableText("thonkutil.warning.3");
        DrawableHelper.drawCenteredText(event.matrices(), MinecraftClient.getInstance().textRenderer, text, event.screen().width / 2, 4 + (2 * (MinecraftClient.getInstance().textRenderer.fontHeight + 1)), 0xFFFFFF | event.alpha());

    }
}
