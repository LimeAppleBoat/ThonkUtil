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
package com.jab125.thonkutil.api.events;

import com.jab125.thonkutil.api.annotations.SubscribeEvent;
import com.jab125.thonkutil.api.events.server.RegisterCommandEvent;
import com.jab125.thonkutil.api.events.server.ServerStartEvent;
import com.jab125.thonkutil.api.events.server.ServerStopEvent;
import com.jab125.thonkutil.api.events.server.ServerTickEvent;
import com.jab125.thonkutil.api.events.server.player.OnPlayerFatalDamageEvent;
import com.jab125.thonkutil.api.events.server.world.ServerWorldLoadEvent;
import com.jab125.thonkutil.api.events.server.world.ServerWorldUnloadEvent;
import com.jab125.thonkutil.api.events.world.WorldTickEvent;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static com.jab125.thonkutil.api.Tick.Phase.END;
import static com.jab125.thonkutil.api.Tick.Phase.START;

public class EventTaxi {
    private static final ArrayList<Class<?>> registeredEventClazzes = new ArrayList<>();

    public static void registerTaxis() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            EventTaxi.executeEventTaxi(new RegisterCommandEvent(dispatcher, registryAccess, environment));
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

    public static void registerEventTaxiSubscriber(Class<?> clazz) {
        registeredEventClazzes.add(clazz);
    }

    @SuppressWarnings("unused")
    public static void unregisterEventTaxiSubscriber(Class<?> clazz) {
        registeredEventClazzes.remove(clazz);
    }


    /**
     * keep in mind that it will only execute currently registered classes' methods.
     *
     * @param object the {@link EventTaxiEvent} to fire.
     */
    public static Object executeEventTaxi(EventTaxiEvent object) {
        return executeEventTaxi(object, null);
    }

    /**
     * keep in mind that it will only execute currently registered classes' methods.
     *
     * @param event  the {@link EventTaxiEvent} to fire.
     * @param target will restrict events to only firing if their target is {@code target}, but if this target is null, then target checking will be deactivated.
     */
    public static Object executeEventTaxi(EventTaxiEvent event, String target) {
        if (event == null) return null;
        for (SubscribeEvent.Priority priority : new SubscribeEvent.Priority[]{SubscribeEvent.Priority.HIGH, SubscribeEvent.Priority.DEFAULT, SubscribeEvent.Priority.LOW}) {
            outerLoop:
            for (Class<?> clazz : registeredEventClazzes) {
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (method.getParameterCount() != 1) continue;
                    if (!method.getParameters()[0].getType().equals(event.getClass())) continue;
                    for (Annotation declaredAnnotation : method.getDeclaredAnnotations()) {
                        if (declaredAnnotation instanceof SubscribeEvent subscribeEvent) {
                            if (target != null && !subscribeEvent.target().equals(target)) break;
                            if (!subscribeEvent.priority().equals(priority)) break;
                            try {
                                if (event.isCancelled()) break outerLoop;
                                method.invoke(null, event);
                                if (event instanceof EventTaxiReturnableEvent) {
                                    //System.out.println(event.getBoolean());
                                }
                                break;
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("failed to execute event taxi");
                            }
                        }
                    }
                }
            }
        }
        if (event instanceof EventTaxiReturnableEvent eventReturnable) {
            return eventReturnable.getResult();
        }
        return null;
    }
}
