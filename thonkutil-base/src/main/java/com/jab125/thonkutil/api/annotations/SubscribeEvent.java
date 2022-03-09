package com.jab125.thonkutil.api.annotations;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Mark <b>CLIENT-ONLY</b> methods with {@code @}{@link Environment}{@code (}{@link EnvType}{@code .CLIENT)}!<br>
 * Mark <b>SERVER-ONLY</b> methods with {@code @}{@link Environment}{@code (}{@link EnvType}{@code .SERVER)}!
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@SuppressWarnings("unused")
/* this is the best name for it */
public @interface SubscribeEvent {
    String target() default "";
    Priority priority() default Priority.DEFAULT;

    enum Priority {
        LOW,
        DEFAULT,
        HIGH
    }
}
