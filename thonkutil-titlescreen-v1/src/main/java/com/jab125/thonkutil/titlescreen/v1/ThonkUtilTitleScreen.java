package com.jab125.thonkutil.titlescreen.v1;

import com.jab125.thonkutil.api.events.EventTaxi;
import com.jab125.thonkutil.api.events.EventTaxiSubscriber;
import com.jab125.thonkutil.api.events.TitleScreenEvent;
import com.jab125.thonkutil.titlescreen.v1.mixin.TitleScreenAccessor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;

public class ThonkUtilTitleScreen implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        getModCount();
        FabricLoader.getInstance();
        EventTaxi.registerEventTaxi(ThonkUtilTitleScreen.class);
//        ScreenEvents.AFTER_INIT.register(((client, screen, scaledWidth, scaledHeight) -> {
//            if (screen instanceof TitleScreen titleScreen) {
//                ScreenEvents.afterRender(screen).register(((screen1, matrices, mouseX, mouseY, tickDelta) -> {
//                    float f = ((TitleScreenAccessor)titleScreen).isDoBackgroundFade() ? (float)(Util.getMeasuringTimeMs() - ((TitleScreenAccessor) titleScreen).getBackgroundFadeStart()) / 1000.0F : 1.0F;
//                    float g = ((TitleScreenAccessor)titleScreen).isDoBackgroundFade() ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
//                    int l = MathHelper.ceil(g * 255.0F) << 24;
//                    if ((l & -67108864) != 0) {
//                        for (int i = 0; i < 4; i++) {
//                            titleScreen.drawStringWithShadow(matrices, MinecraftClient.getInstance().textRenderer, getText(i), 2, titleScreen.height - 10 - (MinecraftClient.getInstance().textRenderer.fontHeight * i), 16777215 | l);
//                        }
//                    }
//                }));
//            }
//        }));
    }

    @EventTaxiSubscriber
    public static void injectToTitleScreen(TitleScreenEvent event) {
        Screen titleScreen = event.getScreen();
        ScreenEvents.afterRender(titleScreen).register(((screen1, matrices, mouseX, mouseY, tickDelta) -> {
            float f = ((TitleScreenAccessor)titleScreen).isDoBackgroundFade() ? (float)(Util.getMeasuringTimeMs() - ((TitleScreenAccessor) titleScreen).getBackgroundFadeStart()) / 1000.0F : 1.0F;
            float g = ((TitleScreenAccessor)titleScreen).isDoBackgroundFade() ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
            int l = MathHelper.ceil(g * 255.0F) << 24;
            if ((l & -67108864) != 0) {
                for (int i = 0; i < 4; i++) {
                    DrawableHelper.drawStringWithShadow(matrices, MinecraftClient.getInstance().textRenderer, getText(i), 2, titleScreen.height - 10 - (MinecraftClient.getInstance().textRenderer.fontHeight * i), 16777215 | l);
                }
            }
        }));
    }

    private static String getText(int i) {
        switch (i) {
            case 3:
                return "Fabric " + FabricLoader.getInstance().getModContainer("fabricloader").get().getMetadata().getVersion().getFriendlyString();
            case 2:
                return "Minecraft " + SharedConstants.getGameVersion().getName();
            case 1:
                return "Fabric API " + FabricLoader.getInstance().getModContainer("fabric").get().getMetadata().getVersion().getFriendlyString().split("\\+")[0];
            case 0:
                return getModCount() + " mods loaded";
            default:
                throw new IllegalStateException("Unexpected value: " + i);
        }
    }

    private static String getModCount() {
        try {
            return (String)(Class.forName("com.terraformersmc.modmenu.ModMenu").getMethod("getDisplayedModCount").invoke(new Object[]{}));
//            for (Method method : Class.forName("com.terraformersmc.modmenu.ModMenu").getMethods()) {
//                System.out.println(method.getName() + ", Annotations" + Arrays.toString(method.getDeclaredAnnotations()) + ", Static: " + Modifier.isStatic(method.getModifiers()));
//            }
        } catch (Exception exception) {
            return String.valueOf(FabricLoader.getInstance().getAllMods().size());
        }
    }
    private String getTextNoFAPI(int i) {
        switch (i) {
            case 2:
                return "Fabric " + FabricLoader.getInstance().getModContainer("fabricloader").get().getMetadata().getVersion().getFriendlyString();
            case 1:
                return "Minecraft " + SharedConstants.getGameVersion().getName();
            case 0:
                return FabricLoader.getInstance().getAllMods().size() + " mods loaded";
            default:
                throw new IllegalStateException("Unexpected value: " + i);
        }
    }
}
