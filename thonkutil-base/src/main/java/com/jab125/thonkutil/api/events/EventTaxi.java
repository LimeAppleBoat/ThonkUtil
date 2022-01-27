package com.jab125.thonkutil.api.events;

import com.jab125.thonkutil.api.annotations.SubscribeEvent;
import com.jab125.thonkutil.api.events.client.screen.ScreenEvent;
import com.jab125.thonkutil.api.events.client.screen.TitleScreenEvent;
import com.jab125.thonkutil.api.events.client.screen.TitleScreenRenderEvent;
import com.jab125.thonkutil.api.events.server.RegisterCommandEvent;
import com.jab125.thonkutil.api.events.server.ServerStartEvent;
import com.jab125.thonkutil.api.events.server.ServerStopEvent;
import com.jab125.thonkutil.api.events.server.ServerTickEvent;
import com.jab125.thonkutil.api.events.server.player.OnPlayerFatalDamageEvent;
import com.jab125.thonkutil.api.events.server.world.ServerWorldLoadEvent;
import com.jab125.thonkutil.api.events.server.world.ServerWorldUnloadEvent;
import com.jab125.thonkutil.api.events.world.WorldTickEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static com.jab125.thonkutil.api.Tick.Phase.END;
import static com.jab125.thonkutil.api.Tick.Phase.START;

public class EventTaxi {
    private static final ArrayList<Class<?>> registeredEventClazzes = new ArrayList<>();

    public static void registerTaxis() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            EventTaxi.executeEventTaxi(new RegisterCommandEvent(dispatcher, dedicated));
        });
        ServerPlayerEvents.ALLOW_DEATH.register((player, damageSource, damageAmount) -> {
            return (boolean) EventTaxi.executeEventTaxi(new OnPlayerFatalDamageEvent(player, damageSource, damageAmount));
        });
        ServerWorldEvents.LOAD.register((minecraftServer, world) -> {
            EventTaxi.executeEventTaxi(new ServerWorldLoadEvent(minecraftServer, world));
        });
        ServerWorldEvents.UNLOAD.register((server, world) -> {
            EventTaxi.executeEventTaxi(new ServerWorldUnloadEvent(server, world));
        });
        ServerLifecycleEvents.SERVER_STARTED.register((server -> {
            EventTaxi.executeEventTaxi(new ServerStartEvent(server));
        }));
        ServerLifecycleEvents.SERVER_STOPPED.register((server -> {
            EventTaxi.executeEventTaxi(new ServerStopEvent(server));
        }));
        ServerTickEvents.START_SERVER_TICK.register((server -> {
            EventTaxi.executeEventTaxi(new ServerTickEvent(server, START));
        }));

        ServerTickEvents.END_SERVER_TICK.register((server -> {
            EventTaxi.executeEventTaxi(new ServerTickEvent(server, END));
        }));
        ServerTickEvents.START_WORLD_TICK.register((world -> {
            EventTaxi.executeEventTaxi(new WorldTickEvent(world, START));
        }));

        ServerTickEvents.END_WORLD_TICK.register((world -> {
            EventTaxi.executeEventTaxi(new WorldTickEvent(world, END));
        }));
    }

    @Environment(EnvType.CLIENT)
    public static void registerClientTaxis() {
        ScreenEvents.AFTER_INIT.register(((client, screen, scaledWidth, scaledHeight) -> {
            EventTaxi.executeEventTaxi(new ScreenEvent(screen, client, scaledWidth, scaledHeight));
            if (screen instanceof TitleScreen titleScreen) {
                EventTaxi.executeEventTaxi(new TitleScreenEvent(screen, client, scaledWidth, scaledHeight));
                ScreenEvents.afterRender(screen).register(((screen1, matrices, mouseX, mouseY, tickDelta) -> {
                    float f = titleScreen.doBackgroundFade ? (float)(Util.getMeasuringTimeMs() - titleScreen.backgroundFadeStart) / 1000.0F : 1.0F;
                    float g = titleScreen.doBackgroundFade ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
                    int l = MathHelper.ceil(g * 255.0F) << 24;
                    if ((l & -67108864) != 0) {
                        for (int i = 0; i < 4; i++) {
                            EventTaxi.executeEventTaxi(new TitleScreenRenderEvent(screen, matrices, mouseX, mouseY, tickDelta, l));
                        }
                    }
                }));
            }
        }));
    }

    public static void registerEventTaxiSubscriber(Class<?> clazz) {
        registeredEventClazzes.add(clazz);
    }

    @SuppressWarnings("unused")
    public static void unregisterEventTaxiSubscriber(Class<?> clazz) {
        registeredEventClazzes.remove(clazz);
    }


    /**
     * keep in mind that it will only execute currently registered classes' methods.
     * @param object
     */
    public static Object executeEventTaxi(Object object) {
        if (!(object instanceof EventTaxiEvent)) return null;
        for (Class<?> clazz : registeredEventClazzes) {
            for (Method method : clazz.getMethods()) {
                if (method.getParameterCount() != 1) continue;
                if (!method.getParameters()[0].getType().equals(object.getClass())) continue;
                for (Annotation declaredAnnotation : method.getDeclaredAnnotations()) {
                    if (declaredAnnotation instanceof SubscribeEvent) {
                        try {
                            method.invoke(null, object);
                            if (object instanceof EventTaxiBooleanReturnableEvent event) {
                                System.out.println(event.getBoolean());
                            }
                        } catch (Exception e){
                            System.out.println("failed to execute event taxi");
                        }
                    }
                }
            }
        }
        if (object instanceof EventTaxiBooleanReturnableEvent event) {
            return event.getBoolean();
        }
        return null;
    }

}
