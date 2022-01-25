package com.jab125.thonkutil.api.events;

import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.client.gui.screen.TitleScreen;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class EventTaxi {
    private static final ArrayList<Class<?>> registeredEventClazzes = new ArrayList<>();

    public static void registerTaxis() {
        ScreenEvents.AFTER_INIT.register(((client, screen, scaledWidth, scaledHeight) -> {
            EventTaxi.executeEventTaxi(new ScreenEvent(screen, client, scaledWidth, scaledHeight));
            if (screen instanceof TitleScreen)
                EventTaxi.executeEventTaxi(new TitleScreenEvent(screen, client, scaledWidth, scaledHeight));
        }));
        CommandRegistrationCallback.EVENT.register(((dispatcher, dedicated) -> {
            EventTaxi.executeEventTaxi(new RegisterCommandEvent(dispatcher, dedicated));
        }));
        ServerPlayerEvents.ALLOW_DEATH.register(((player, damageSource, damageAmount) -> {
            boolean no = (boolean) EventTaxi.executeEventTaxi(new OnPlayerFatalDamageEvent(player, damageSource, damageAmount));
            //System.out.println("Result: " + no);
            return no;
        }));
    }

    public static void registerEventTaxi(Class<?> clazz) {
        registeredEventClazzes.add(clazz);
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
                    if (declaredAnnotation instanceof EventTaxiSubscriber) {
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
