package com.jab125.thonkutil.titlescreen.v1;

import com.jab125.thonkutil.api.events.EventTaxi;
import com.jab125.thonkutil.api.annotations.SubscribeEvent;
import com.jab125.thonkutil.api.events.client.screen.TitleScreenRenderEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;

public class ThonkUtilTitleScreen implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        getModCount();
        FabricLoader.getInstance();
        EventTaxi.registerEventTaxiSubscriber(ThonkUtilTitleScreen.class);
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

//    @SubscribeEvent
//    public static void injectToTitleScreen(TitleScreenEvent event) {
//        Screen titleScreen = event.getScreen();
//        ScreenEvents.afterRender(titleScreen).register(((screen1, matrices, mouseX, mouseY, tickDelta) -> {
//            float f = ((TitleScreenAccessor)titleScreen).isDoBackgroundFade() ? (float)(Util.getMeasuringTimeMs() - ((TitleScreenAccessor) titleScreen).getBackgroundFadeStart()) / 1000.0F : 1.0F;
//            float g = ((TitleScreenAccessor)titleScreen).isDoBackgroundFade() ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
//            int l = MathHelper.ceil(g * 255.0F) << 24;
//            if ((l & -67108864) != 0) {
//                for (int i = 0; i < 4; i++) {
//                    DrawableHelper.drawStringWithShadow(matrices, MinecraftClient.getInstance().textRenderer, getText(i), 2, titleScreen.height - 10 - (MinecraftClient.getInstance().textRenderer.fontHeight * i), 16777215 | l);
//                }
//            }
//        }));
//    }

    @SubscribeEvent
    public static void onTitleScreenRender(TitleScreenRenderEvent event) {
        for (int i = 0; i < 4; i++) {
            DrawableHelper.drawStringWithShadow(event.matrices(), MinecraftClient.getInstance().textRenderer, getText(i), 2, event.screen().height - 10 - (MinecraftClient.getInstance().textRenderer.fontHeight * i), 16777215 | event.alpha());
        }
    }

    private static String getText(int i) {
        return switch (i) {
            case 3 -> "Fabric " + FabricLoader.getInstance().getModContainer("fabricloader").get().getMetadata().getVersion().getFriendlyString();
            case 2 -> "Minecraft " + SharedConstants.getGameVersion().getName();
            case 1 -> "Fabric API " + FabricLoader.getInstance().getModContainer("fabric").get().getMetadata().getVersion().getFriendlyString().split("\\+")[0];
            case 0 -> getModCount() + " mods loaded";
            default -> throw new IllegalStateException("Unexpected value: " + i);
        };
    }

    private static String getModCount() {
        try {
            return (String)(Class.forName("com.terraformersmc.modmenu.ModMenu").getMethod("getDisplayedModCount").invoke(null, new Object[]{}));
//            for (Method method : Class.forName("com.terraformersmc.modmenu.ModMenu").getMethods()) {
//                System.out.println(method.getName() + ", Annotations" + Arrays.toString(method.getDeclaredAnnotations()) + ", Static: " + Modifier.isStatic(method.getModifiers()));
//            }
        } catch (Exception exception) {
            return String.valueOf(FabricLoader.getInstance().getAllMods().size());
        }
    }
    private String getTextNoFAPI(int i) {
        return switch (i) {
            case 2 -> "Fabric " + FabricLoader.getInstance().getModContainer("fabricloader").get().getMetadata().getVersion().getFriendlyString();
            case 1 -> "Minecraft " + SharedConstants.getGameVersion().getName();
            case 0 -> FabricLoader.getInstance().getAllMods().size() + " mods loaded";
            default -> throw new IllegalStateException("Unexpected value: " + i);
        };
    }

    public static String modId() {
        return "thonkutil-titlescreen-v1";
    }
}
