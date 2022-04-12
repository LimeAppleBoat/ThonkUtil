package com.jab125.thonkutil.api.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface WillBeAvailableSoon {
    String value();
}
